package br.pucpr.aulawebservice.repository;

import br.pucpr.aulawebservice.model.OfficialCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OfficialCollectionRepository extends JpaRepository<OfficialCollection, Long> {
    Optional<OfficialCollection> findByName(String name);
}
