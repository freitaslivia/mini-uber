package br.com.fiap.jadv.rm99892.checkpoint.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.jadv.rm99892.checkpoint.dto.CorridaDto;
import br.com.fiap.jadv.rm99892.checkpoint.dto.CriarCorridaDto;
import br.com.fiap.jadv.rm99892.checkpoint.entidades.Corrida;
import br.com.fiap.jadv.rm99892.checkpoint.repositorios.CorridaRepository;
import br.com.fiap.jadv.rm99892.checkpoint.servicos.CorridaService;
import br.com.fiap.jadv.rm99892.exception.CorridaException;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("rm99892")
//Livia Freitas Ferreira RM99892
public class CorridaApi {
    @Autowired
    private CorridaRepository corridaRepository;
    
    @Autowired
    private CorridaService corridaService;

    
  @PostMapping("/corridas")
  	public CorridaDto criarCorrida(@RequestBody @Valid CriarCorridaDto criarCorridaDto) {
	  return this.corridaService.criarCorrida(criarCorridaDto);
  	}
        
    @PutMapping("/corridas/{id}/situacao") 
	@Transactional
	public void atualizarSituacaoCorrida(@PathVariable("id") Long id, @RequestBody CriarCorridaDto CorridaDtoAtualizada) {
		Corrida corridaExistente = this.corridaRepository.findById(id).orElse(null);
		
		if (corridaExistente == null) {
			throw new CorridaException("ID nao encontrado");
		}
		
		var situacaoCorridaAtual = CorridaDtoAtualizada.situacaoCorrida();
		
		this.corridaService.atualizarSituacaoCorrida(id, situacaoCorridaAtual);
	}
   
    @PutMapping("/corridas/{id}/posicao-atual")
    public void atualizarPosicaoAtual(@PathVariable("id") Long id, @RequestBody  CriarCorridaDto CorridaDtoAtualizada ) {
		Corrida corridaExistente = this.corridaRepository.findById(id).orElse(null);
		
		if (corridaExistente == null) {
			throw new CorridaException("ID nao encontrado");
		}
	
		this.corridaService.atualizarPosicaoAtual(id, CorridaDtoAtualizada.latitudeAtual(), CorridaDtoAtualizada.longitudeAtual());
        
    }
}
