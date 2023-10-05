package ma.aeroauto.gestionproduits.repositories;

import ma.aeroauto.gestionproduits.entities.Nuance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NuanceRepository extends JpaRepository<Nuance,Long> {
    Page<Nuance> findBytypeNuance(String kw, Pageable pageable);
}
