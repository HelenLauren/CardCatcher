package br.pucpr.tcgmanager.repository;

import br.pucpr.tcgmanager.model.OfficialDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficialDeckRepository extends JpaRepository<OfficialDeck, Long> {}
