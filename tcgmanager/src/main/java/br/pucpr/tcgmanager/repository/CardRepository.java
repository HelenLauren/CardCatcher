package br.pucpr.tcgmanager.repository;

import br.pucpr.tcgmanager.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByApiId(String apiId);
}
