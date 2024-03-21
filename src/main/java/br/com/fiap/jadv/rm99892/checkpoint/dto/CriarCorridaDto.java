package br.com.fiap.jadv.rm99892.checkpoint.dto;

import java.sql.Timestamp;

import br.com.fiap.jadv.rm99892.checkpoint.enums.SituacaoCorrida;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder.Default;

public record CriarCorridaDto(
		@NotBlank(message = "O nome do cliente deve ser informado.") String nomeCliente,
    @NotBlank(message = "O cpf deve ser informado.") String cpfCliente, 
    @NotBlank(message = "A placa deve ser informada.") String veiculoPlaca, 
    @NotBlank(message = "A cor deve ser informada.") String veiculoCor,
    @NotBlank(message = "A descricao deve ser informada.") String veiculoDs,
    @NotBlank(message = "O o nome do motorista deve ser informada.") String nomeMotorista,
    @NotNull(message = "A data de solitação deve ser informada.") Timestamp dtSolicitacao,
    Timestamp dtFinalizacao,
		@NotNull(message = "A latidude de origem deve ser informada.") 
		Double latitudeOrigem,
		@NotNull(message = "A longitude de origem deve ser informada.") 
		Double longitudeOrigem,
		@NotNull(message = "A latitude de destino deve ser informada.") 
		Double latitudeDestino,
		@NotNull(message = "A longitude de destino deve ser informada.") 
		Double longitudeDestino,
		@NotNull(message = "A latitude de atual deve ser informada.") 
		Double latitudeAtual,
		@NotNull(message = "A longitude de atual deve ser informada.") 
		Double longitudeAtual,
		@NotNull(message = "A situacao da corrida deve ser informada.")
		SituacaoCorrida situacaoCorrida
		)
        {

}
