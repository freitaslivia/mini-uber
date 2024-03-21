package br.com.fiap.jadv.rm99892.checkpoint.dto;

import java.sql.Timestamp;

import br.com.fiap.jadv.rm99892.checkpoint.entidades.Corrida;
import br.com.fiap.jadv.rm99892.checkpoint.enums.SituacaoCorrida;

public record CorridaDto(Long id, String nomeCliente, String veiculoDs, String cpfCliente, String veiculoPlaca, String veiculoCor, Timestamp dtSolicitacao,
		Timestamp dtFinalizacao,  double latitudeOrigem, double longitudeOrigem, double latitudeDestino,
		double longitudeDestino, double latitudeAtual, double longitudeAtual,  SituacaoCorrida situacaoCorrida) {

	public CorridaDto(Corrida corrida) {
		this(corrida.getId(), corrida.getNomeCliente(), corrida.getVeiculoDs(), corrida.getCpfCliente(), corrida.getVeiculoPlaca(), corrida.getVeiculoCor(),
				corrida.getDtSolicitacao(),  corrida.getDtFinalizacao(), corrida.getLatitudeOrigem(),
				corrida.getLongitudeOrigem(), corrida.getLatitudeDestino(), corrida.getLongitudeDestino(),
				corrida.getLatitudeAtual(), corrida.getLongitudeAtual(), corrida.getSituacaoCorrida());
	}
}
