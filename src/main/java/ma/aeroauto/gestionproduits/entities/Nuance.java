package ma.aeroauto.gestionproduits.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class Nuance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idNuance;
    @NotEmpty
    private String typeNuance;

    public Long getIdNuance() {
        return idNuance;
    }

    public void setIdNuance(Long idNuance) {
        this.idNuance = idNuance;
    }

    public String getTypeNuance() {
        return typeNuance;
    }

    public void setTypeNuance(String typeNuance) {
        this.typeNuance = typeNuance;
    }
}
