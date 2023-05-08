package controller;

import cci.projetointegrador.repository.PessoaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRep pessoaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final PessoaRep pessoa = this.pessoaRepository.findById(id).orElse(null);
        return ResponseEntity.ok(pessoa);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.pessoaRepository.findAll());

    }
    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final PessoaRep pessoa){
        try {
            this.pessoaRepository.save(pessoa);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final PessoaRep pessoa) {

        try {
            final PessoaRep pessoa1 = this.pessoaRepository.findById(id).orElse(null);
            if (pessoa1 == null || pessoa1.getId().equals(pessoa.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.pessoaRepository.save(pessoa);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("delete/{id}")
    public void deletePessoa (@PathVariable Long id) {pessoaRepository.deleteById(id);}
}

