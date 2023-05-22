package cci.projetointegrador.controller;

import cci.projetointegrador.entity.Administrador;
import cci.projetointegrador.repository.AdministradorRepository;
import cci.projetointegrador.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // Está indicando que a classe é um controller
@RequestMapping(value = "/api/administrador") // Mapeamento e nivel de classe.
public class AdministradorController {
    @Autowired // Pegar todas as informações de dentro do AdministradorRepository sem instânciar e criar um objeto novo.
    private AdministradorRepository administradorRep;

    @Autowired // Pegar todas as informações de dentro do AdministradorRepository sem instânciar e criar um objeto novo.
    private AdministradorService administradorService;

    @GetMapping("/{id}") // Mapeamento e caminho até o ID.
    public ResponseEntity<Administrador> findByIDPath(@PathVariable("id") final Long id) {
        final Administrador administrador = this.administradorRep.findById(id).orElse(null);
        return ResponseEntity.ok(administrador);
    }


    @GetMapping("/lista") // Mapeamento e caminho até a lista.
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.administradorRep.findAll());

    }

    @PostMapping // Mapeamento e caminho até o cadastro.
    public ResponseEntity<?> cadastrar(@RequestBody final Administrador administrador) {
        try {
            administradorService.validaAdm(administrador);
            return ResponseEntity.ok("Admnistrador cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping // Mapeamento e nível de escopo
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Administrador administrador) {
        try {
            administradorService.validaAdm(administrador);
            final Administrador administrador1 = this.administradorRep.findById(id).orElse(null);

            if (administrador1 == null || administrador1.getId().equals(administrador.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.administradorRep.save(administrador);
            return ResponseEntity.ok("Administrador atualizado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")

    public void deletarAdministrador(@PathVariable Long id) {
        administradorRep.deleteById(id);
    }

}



