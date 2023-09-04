package projetointegrador.cciar.projetointegradorcciar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private String cep;

    private String logradouro;

    private String localidade;

    private String bairro;

    private int numCasa;

    private String uf;
}
