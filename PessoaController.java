package cci.projetointegrador.controller;

import cci.projetointegrador.entity.Pessoa;
import cci.projetointegrador.repository.PessoaRepository;
import cci.projetointegrador.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping ("/{id}")
        public ResponseEntity<Pessoa> findByIDPath (@PathVariable("id") final Long id){
            final Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);
            return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.pessoaRepository.findAll());

    }

    @PostMapping
    public ResponseEntity <?> cadastrar (@RequestBody final Pessoa pessoa) {
        try {
            pessoaService.validaPessoa(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Pessoa pessoa) {
        try {
            pessoaService.validaPessoa(pessoa);
            final Pessoa pessoa1 = this.pessoaRepository.findById(id).orElse(null);
            if (pessoa1 == null || !pessoa1.getId().equals(pessoa.getId())) {
                throw new RuntimeException("Nao foi possivel identificar o registro informado");
            }
            this.pessoaRepository.save(pessoa);
            return ResponseEntity.ok("Pessoa atualizada com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping ("delete/{id}")

    public void deletaPessoa (@PathVariable Long id)
    {
        pessoaRepository.deleteById(id);
    }


}
