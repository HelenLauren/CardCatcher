package br.pucpr.aulawebservice.repository;

import br.pucpr.aulawebservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByOfficialCollection_Name(String collectionName);
    List<Card> findByNameContainingIgnoreCase(String name);
}
