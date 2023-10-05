package ma.aeroauto.gestionproduits.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Materiau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriau;
    @NotEmpty
    private String typeMateriau;

    public Long getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Long idMateriau) {
        this.idMateriau = idMateriau;
    }

    public String getTypeMateriau() {
        return typeMateriau;
    }

    public void setTypeMateriau(String typeMateriau) {
        this.typeMateriau = typeMateriau;
    }
}
