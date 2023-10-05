package ma.aeroauto.gestionproduits.repositories;

import ma.aeroauto.gestionproduits.entities.Format;
import ma.aeroauto.gestionproduits.entities.Materiau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriauRepository extends JpaRepository<Materiau,Long> {
    Page<Materiau> findByTypeMateriauContains(String kw, Pageable pageable);
}
