package projetointegrador.cciar.projetointegradorcciar.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projetointegrador.cciar.projetointegradorcciar.dto.PessoaDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.repository.PessoaRepository;
import projetointegrador.cciar.projetointegradorcciar.service.PessoaService;

import java.util.List;

@Controller
@RequestMapping(value = "/api/pessoa")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findByIDPath (@PathVariable("id") final Long id){
        final Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/lista")
    public ResponseEntity<List <Pessoa>> listaCompleta() {
        return ResponseEntity.ok(this.pessoaRepository.findAll());

    }

    @GetMapping("/api/pessoa/nome")
    public List<Pessoa> pesquisarPorNome(@RequestParam String nome) {
        return pessoaRepository.findByNome(nome);
    }

    @PostMapping
    public ResponseEntity<String> cadastrar (@Valid @RequestBody final PessoaDTO pessoa) {
        try {
            pessoaService.validaPessoa(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") final Long id, @Validated @RequestBody final PessoaDTO pessoaDTO) {
        try {
            pessoaService.editarPessoa(id, pessoaDTO);
            return ResponseEntity.ok("Pessoa atualizada com Sucesso");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletaPessoa (@PathVariable ("id") final Long id){
        try {
            this.pessoaService.deletarPessoa(id);
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
