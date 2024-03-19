package br.com.fiap.jadv.rm99892.checkpoint.entidades;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_corrida_99892")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Corrida {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, 
			generator = "sequence_corridas")
	@SequenceGenerator(
			name = "sequence_corridas", 
			sequenceName = "sq_tb_corrida_99892", 
			allocationSize = 1)
	private Long id;
	
	@Column(name = "ds_nome_cliente")
	private String nomeCliente;
	
	@Column(name = "nr_cpf_cliente")
	private String cpfCliente;
	
	
	@Column(name = "ds_nome_motorista")
	private String nomeMotorista;
	
	@Column(name = "nr_placa_veiculo")
	private String veiculoPlaca;
	
	@Column(name = "ds_cor_veiculo")
	private String veiculoCor;
	
	
	@Column(name = "dt_solicitacao")
	private Timestamp dtSolicitacao;
	
	@Column(name = "dt_finalizacao")
	private Timestamp dtFinalizacao;
	
	
	@Column(name = "nr_latitude_origem")
	private float latitudeOrigem;
	
	@Column(name = "nr_longitude_origem")
	private float longitudeOrigem;
	
	@Column(name = "nr_latitude_destino")
	private float latitudeDestino;
	
	@Column(name = "nr_longitude_destino")
	private float longitudeDestino;
	
	@Column(name = "nr_latitude_atual")
	private float latitudeAtual;
	
	@Column(name = "nr_longitude_atual")
	private float longitudeAtual;


}
