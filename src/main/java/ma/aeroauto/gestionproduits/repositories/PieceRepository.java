package ma.aeroauto.gestionproduits.repositories;

import ma.aeroauto.gestionproduits.entities.Piece;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceRepository extends JpaRepository<Piece,Long> {
    Page<Piece> findByDescriptionContains(String kw, Pageable pageable);
}
