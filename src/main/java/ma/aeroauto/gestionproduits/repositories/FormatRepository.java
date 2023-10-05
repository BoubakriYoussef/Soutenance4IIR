package ma.aeroauto.gestionproduits.repositories;

import ma.aeroauto.gestionproduits.entities.Format;
import ma.aeroauto.gestionproduits.entities.Process;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormatRepository extends JpaRepository<Format,Long> {
    Page<Format> findByTypeFormatContains(String kw, Pageable pageable);

    public Format findFormatBytypeFormat(String typeFormat);
}
