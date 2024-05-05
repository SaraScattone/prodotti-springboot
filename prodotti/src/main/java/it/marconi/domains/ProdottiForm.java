package it.marconi.domains;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdottiForm {
    
    @NotEmpty
    @Size(max = 30)
    private String nome;

    @NotEmpty
    @Size(max = 30)
    private String categoria;

    @NotEmpty
    @Pattern(regexp = "([0-3]{4})")
    private int annoProduzione;

    @NotEmpty
    private int quantita;
}
