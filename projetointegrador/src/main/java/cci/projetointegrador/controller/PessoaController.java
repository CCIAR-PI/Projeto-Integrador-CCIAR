package cci.projetointegrador.controller;

import cci.projetointegrador.entity.Pessoa;
import cci.projetointegrador.repository.PessoaRepository;
import cci.projetointegrador.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller //indica que a classe se trata de uma Controller
@RequestMapping(value = "/api/pessoa") //é a anotação utilizada tradicionalmente para implementar URL handler, ela suporta os métodos Post, Get, Put, Delete e Pacth.
public class PessoaController {

    @Autowired //injecao de dependencias de PessoaRepository
    private PessoaRepository pessoaRepository;

    @Autowired //injecao de dependencias de PessoaService
    private PessoaService pessoaService;

    @GetMapping ("/{id}") //@GetMapping Determina que o método aceitará requisições HTTP do tipo GET, e retornar usuário pelo seu ID.
        public ResponseEntity<Pessoa> findByIDPath (@PathVariable("id") final Long id){
            final Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);
            return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/lista")//Temos um método GET/lista para retornar todos os usuários;
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.pessoaRepository.findAll());

    }

    @PostMapping //@PostMapping: Determina que o método aceitará requisições HTTP do tipo POST. POST/pessoa para salvar um novo usuário;
    public ResponseEntity<?> cadastrar (@RequestBody final Pessoa pessoa) {
        try {
            pessoaService.validaPessoa(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping //@PutMapping: Determina que o método aceitará requisições HTTP do tipo PUT. PUT/pessoa para alterar um usuário;
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Pessoa pessoa) {
        try {
            pessoaService.validaPessoa(pessoa);
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
    @DeleteMapping ("delete/{id}") //@DeleteMapping: Determina que o método aceitará requisições HTTP do tipo DELETE.  DELETE/pessoa/delete/{id} para remover um usuário pelo seu id.

    public void deletaPessoa (@PathVariable Long id)
    {
        pessoaRepository.deleteById(id);
    }


}
