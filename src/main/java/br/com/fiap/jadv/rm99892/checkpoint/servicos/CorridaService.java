package br.com.fiap.jadv.rm99892.checkpoint.servicos;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.jadv.rm99892.checkpoint.dto.CorridaDto;
import br.com.fiap.jadv.rm99892.checkpoint.dto.CriarCorridaDto;
import br.com.fiap.jadv.rm99892.checkpoint.entidades.Corrida;
import br.com.fiap.jadv.rm99892.checkpoint.enums.SituacaoCorrida;
import br.com.fiap.jadv.rm99892.checkpoint.repositorios.CorridaRepository;
import br.com.fiap.jadv.rm99892.exception.CorridaException;

@Service
public class CorridaService {

	@Autowired
	private CorridaRepository corridaRepository;

	@Transactional
	public CorridaDto criarCorrida(CriarCorridaDto criarCorridaDto) {
		if (criarCorridaDto.latitudeOrigem() == criarCorridaDto.latitudeDestino()
				&& criarCorridaDto.longitudeOrigem() == criarCorridaDto.longitudeDestino()) {
			throw new CorridaException("A latitude e longitude de origem não podem ser iguais à de destino");
		}
		if (corridaRepository.findByCpfClienteAndSituacaoCorridaNot(criarCorridaDto.cpfCliente(),
				SituacaoCorrida.EM_ANDAMENTO) != null) {
			throw new CorridaException("Cliente já possui uma corrida em andamento");
		}

		Corrida corrida = new Corrida(null, criarCorridaDto.nomeCliente(), criarCorridaDto.cpfCliente(),
				criarCorridaDto.nomeMotorista(), criarCorridaDto.veiculoPlaca(), criarCorridaDto.veiculoCor(),
				criarCorridaDto.veiculoDs(), criarCorridaDto.dtSolicitacao(), criarCorridaDto.dtFinalizacao(),
				criarCorridaDto.latitudeOrigem(), criarCorridaDto.longitudeOrigem(), criarCorridaDto.latitudeDestino(),
				criarCorridaDto.longitudeDestino(), criarCorridaDto.latitudeAtual(), criarCorridaDto.longitudeAtual(),
				criarCorridaDto.situacaoCorrida());

		this.corridaRepository.save(corrida);
		return new CorridaDto(corrida);
	}

	@Transactional
	public Corrida atualizarSituacaoCorrida(Long id, SituacaoCorrida situacaoCorrida) {

		Corrida corrida = corridaRepository.findById(id)
				.orElseThrow(() -> new CorridaException("Corrida com ID " + id + " não encontrada"));

		SituacaoCorrida situacaoAtual = SituacaoCorrida.getCorridaPorCodigo(corrida.getSituacaoCorrida().getCodigo());
		switch (situacaoAtual) {
		case AGUARDANDO:
			if (situacaoCorrida == SituacaoCorrida.EM_ANDAMENTO || situacaoCorrida == SituacaoCorrida.CANCELADA) {
				if (corrida.getLatitudeAtual() != corrida.getLatitudeOrigem()
						|| corrida.getLongitudeAtual() != corrida.getLongitudeOrigem()) {
					throw new CorridaException(
							"A corrida so pode estar em andamento se o carro estiver em posicao igual a de origem");
				}
				corrida.setSituacaoCorrida(situacaoCorrida);

				if (situacaoCorrida == SituacaoCorrida.CANCELADA) {
					corrida.setDtFinalizacao(new Timestamp(System.currentTimeMillis()));
				}
				break;
			}
			throw new CorridaException(
					"Não é possível atualizar a corrida de 'Aguardando' para " + situacaoCorrida.name());

		case EM_ANDAMENTO:
			if (situacaoCorrida == SituacaoCorrida.CONCLUIDA) {

				corrida.setSituacaoCorrida(situacaoCorrida);

				corrida.setDtFinalizacao(new Timestamp(System.currentTimeMillis()));
				break;
			}

			throw new CorridaException(
					"Não é possível atualizar a corrida de 'Em andamento' para " + situacaoCorrida.name());
		case CONCLUIDA:
		case CANCELADA:
			throw new CorridaException("Não é possível atualizar a corrida de " + situacaoAtual.name() + " para "
					+ situacaoCorrida.name());
		}

		return corridaRepository.save(corrida);
	}

	@Transactional
	public void atualizarPosicaoAtual(Long id, double latitude, double longitude) {

		Corrida corrida = corridaRepository.findById(id)
				.orElseThrow(() -> new CorridaException("Corrida com ID " + id + " não encontrada"));

		corrida.setLatitudeAtual(latitude);
		corrida.setLongitudeAtual(longitude);

		corridaRepository.save(corrida);
	}
}
