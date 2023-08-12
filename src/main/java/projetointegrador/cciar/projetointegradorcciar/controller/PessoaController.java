package projetointegrador.cciar.projetointegradorcciar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.pessoaRepository.findAll());

    }

    @GetMapping("/api/pessoa/nome")
    public List<Pessoa> pesquisarPorNome(@RequestParam String nome) {
        return pessoaRepository.findByNome(nome);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@Validated @RequestBody final Pessoa pessoa) {
        try {
            pessoaService.validaPessoa(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @Validated @RequestBody final Pessoa pessoa) {
        try {
            pessoaService.editarPessoa(id, pessoa);
            final Pessoa pessoa1 = this.pessoaRepository.findById(id).orElse(null);
            if (pessoa1 == null || !pessoa1.getId().equals(pessoa.getId())) {
                throw new RuntimeException("Nao foi possivel identificar o registro informado");
            }
            return ResponseEntity.ok("Pessoa atualizada com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deletaPessoa (@PathVariable ("id") final Long id){
        try {
            this.pessoaService.deletarPessoa(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
