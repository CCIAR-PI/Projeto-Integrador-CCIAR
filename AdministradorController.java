package cci.projetointegrador.controller;

import cci.projetointegrador.repository.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/administrador")

public class AdministradorController {

    public class MarcaController {

        @Autowired
        private Administrador administradorRep;
        @GetMapping("/{id}")
        public ResponseEntity<cci.projetointegrador.entity.Administrador> findByIDPath (@PathVariable("id") final Long id) {
            final cci.projetointegrador.entity.Administrador marca = this.administradorRep.findById(id).orElse(null);
            return ResponseEntity.ok(marca);
        }

        // http://localhost:8080/api/administrador/1

        // http://localhost:8080/api/marca?id=1


        @GetMapping("/lista")
        public ResponseEntity<?> ListaCompleta() {
            return ResponseEntity.ok(this.administradorRep.findAll());

        }

        @PostMapping
        public ResponseEntity<?> cadastrar (@RequestBody final cci.projetointegrador.entity.Administrador administrador) {
            try {
                this.administradorRep.save(administrador);
                return ResponseEntity.ok("Registro cadastrado com sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
            }
        }

        @PutMapping
        public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final cci.projetointegrador.entity.Administrador administrador) {
            try {
                final cci.projetointegrador.entity.Administrador administrador1 = this.administradorRep.findById(id).orElse(null);

                if (administrador1 == null || administrador1.getId().equals(administrador.getId())) {
                    throw new RuntimeException("Nao foi possivel indentificar o registro informado");
                }
                this.administradorRep.save(administrador);
                return ResponseEntity.ok("Registro Cadastrado com Sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError()
                        .body("Error: " + e.getCause().getCause().getMessage());
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
            }
        }

        @DeleteMapping("delete/{id}")

        public void deletarAdministrador (@PathVariable Long id)
        {
            administradorRep.deleteById(id);
        }




    }

}
