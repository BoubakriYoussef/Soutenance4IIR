package ma.aeroauto.gestionproduits.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Piece {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPiece;
    private String nom_Piece;

    private String description;
    private double poidsNet;
    private double prixVente;
    private double stock;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="process_id")
    private Process process;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="forme_id")
    private Format format;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="materiau_id")
    private Materiau materiau;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="nuance_id")
    private Nuance nuance;

    public Long getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(Long idPiece) {
        this.idPiece = idPiece;
    }

    public String getNom_Piece() {
        return nom_Piece;
    }

    public void setNom_Piece(String nom_Piece) {
        this.nom_Piece = nom_Piece;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPoidsNet() {
        return poidsNet;
    }

    public void setPoidsNet(double poidsNet) {
        this.poidsNet = poidsNet;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Materiau getMateriau() {
        return materiau;
    }

    public void setMateriau(Materiau materiau) {
        this.materiau = materiau;
    }

    public Nuance getNuance() {
        return nuance;
    }

    public void setNuance(Nuance nuance) {
        this.nuance = nuance;
    }
}
