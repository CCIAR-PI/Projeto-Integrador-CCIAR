package projetointegrador.cciar.projetointegradorcciar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projetointegrador.cciar.projetointegradorcciar.dto.AdministradorDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Administrador;
import projetointegrador.cciar.projetointegradorcciar.repository.AdministradorRepository;
import projetointegrador.cciar.projetointegradorcciar.service.AdministradorService;

import java.util.List;

@Controller
@RequestMapping(value = "/api/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorRepository administradorRep;

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> findByIDPath(@PathVariable("id") final Long id) {
        final Administrador administrador = this.administradorRep.findById(id).orElse(null);
        return ResponseEntity.ok(administrador);
    }


    @GetMapping("/lista")
    public ResponseEntity<List <Administrador>> listaCompleta() {
        return ResponseEntity.ok(this.administradorRep.findAll());

    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@PathVariable("id") final Long id, @Validated @RequestBody final AdministradorDTO administrador) {
        try {
            administradorService.validaAdm(administrador);
            return ResponseEntity.ok("Admnistrador cadastrado com sucesso");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") final Long id, @Validated @RequestBody final AdministradorDTO administradorDTO) {
        try {
            administradorService.editaAdm(id, administradorDTO);
            return ResponseEntity.ok("Administrador atualizado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public void deletarAdministrador(@PathVariable Long id) {
        administradorRep.deleteById(id);
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }
}
