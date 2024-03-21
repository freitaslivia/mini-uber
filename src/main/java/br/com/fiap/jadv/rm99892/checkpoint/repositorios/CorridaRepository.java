package br.com.fiap.jadv.rm99892.checkpoint.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.jadv.rm99892.checkpoint.entidades.Corrida;
import br.com.fiap.jadv.rm99892.checkpoint.enums.SituacaoCorrida;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {
	

	Corrida findByCpfClienteAndSituacaoCorridaNot(String cpf, SituacaoCorrida situacao);
}
