package projetointegrador.cciar.projetointegradorcciar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projetointegrador.cciar.projetointegradorcciar.dto.EnderecoDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Endereco;
import projetointegrador.cciar.projetointegradorcciar.repository.EnderecoRepository;
import projetointegrador.cciar.projetointegradorcciar.service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findByIDPath (@PathVariable("id") final Long id){
        final Endereco endereco = this.enderecoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Endereco>> listaCompleta() {
        return ResponseEntity.ok(this.enderecoRepository.findAll());

    }

    @PostMapping
    public ResponseEntity<String> cadastrar (@Validated @RequestBody final EnderecoDTO enderecoDTO) {
        try {
            enderecoService.validaEndereco(enderecoDTO);
            return ResponseEntity.ok("Endereco cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") final Long id, @Validated @RequestBody final Endereco endereco) {
        try {
//            pessoaService.editarPessoa(id, pessoa);
            return ResponseEntity.ok("Endereco atualizada com Sucesso");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletaEndereco (@PathVariable ("id") final Long id){
        try {
//            this.pessoaService.deletarPessoa(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        }
        catch (Exception e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }
}
