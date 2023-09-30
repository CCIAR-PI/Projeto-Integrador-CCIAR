package projetointegrador.cciar.projetointegradorcciar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class AtividadeDTO {
    private Long id;
    private boolean ativo;

    @NotNull(message = "Nome da atividade não pode ser nula")
    @NotBlank(message = "Nome da atividade não pode estar em branco")
    @Size(max = 200, message = "Nome da atividade excede limite de caracteres")
    private String nomeAtividade;

    @NotNull(message = "Descrição da atividade não pode ser nula")
    @NotBlank(message = "Descrição da atividade não pode estar em branco")
    @Size(max = 500, message = "Descrição da atividade excede limite de caracteres")
    private String descricao;

    private LocalDateTime dataAtividade;

    private LocalTime horarioCadastro;
}
