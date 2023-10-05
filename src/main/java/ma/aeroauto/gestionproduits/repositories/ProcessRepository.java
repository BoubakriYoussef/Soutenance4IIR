package ma.aeroauto.gestionproduits.repositories;

import ma.aeroauto.gestionproduits.entities.Process;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<Process,Long> {

    Page<Process> findByTypeProcessContains(String kw,Pageable pageable);
}
