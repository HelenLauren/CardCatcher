package br.pucpr.tcgmanager.repository;

import br.pucpr.tcgmanager.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
    Optional<Set> findByApiId(String apiId);
}
