package ma.aeroauto.gestionproduits.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcess;
    @NotEmpty
    private String typeProcess;

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idProcess) {
        this.idProcess = idProcess;
    }

    public String getTypeProcess() {
        return typeProcess;
    }

    public void setTypeProcess(String typeProcess) {
        this.typeProcess = typeProcess;
    }
}
