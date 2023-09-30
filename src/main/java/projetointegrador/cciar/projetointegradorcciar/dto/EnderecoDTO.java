package projetointegrador.cciar.projetointegradorcciar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;

@Getter
@Setter
public class EnderecoDTO {

    private Pessoa pessoa;
    @NotNull(message = "CEP não pode ser nulo")
    @NotBlank(message = "CEP não pode estar em branco")
    @Size(max = 9, message = "CEP excede limite de caracteres")
    private String cep;

    @NotNull(message = "Logradouro não pode ser nulo")
    @NotBlank(message = "Logradouro não pode estar em branco")
    @Size(max = 200, message = "Logradouro excede limite de caracteres")
    private String logradouro;

    @NotNull(message = "Localidade não pode ser nula")
    @NotBlank(message = "Localidade não pode estar em branco")
    @Size(max = 200, message = "Localidade excede limite de caracteres")
    private String localidade;

    @NotNull(message = "Bairro não pode ser nulo")
    @NotBlank(message = "Bairro não pode estar em branco")
    @Size(max = 200, message = "Bairro excede limite de caracteres")
    private String bairro;

    @NotNull (message = "Número da casa não pode ser nulo")
    private int numCasa;

    @NotNull(message = "UF não pode ser nulo")
    @NotBlank(message = "UF não pode estar em branco")
    @Size(max = 100, message = "UF excede limite de caracteres")
    private String uf;
}
