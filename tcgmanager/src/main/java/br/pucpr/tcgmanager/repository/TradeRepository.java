package br.pucpr.tcgmanager.repository;

import br.pucpr.tcgmanager.model.Trade;
import br.pucpr.tcgmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByOfferedByOrRequestedBy(User offeredBy, User requestedBy);
}
