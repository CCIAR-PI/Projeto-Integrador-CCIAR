package projetointegrador.cciar.projetointegradorcciar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministradorDTO {
    private Long id;

    private String nomeAdm;
    private String loginNome;

    private String telefone;

    private String senha;

    private String email;

    private String emailRecup;

    private String cpf;
}
