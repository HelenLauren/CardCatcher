package br.pucpr.tcgmanager.repository;

import br.pucpr.tcgmanager.model.TradeList;
import br.pucpr.tcgmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TradeListRepository extends JpaRepository<TradeList, Long> {
    List<TradeList> findByUser(User user);
    Optional<TradeList> findByUserIdAndCardId(Long userId, Long cardId);
    List<TradeList> findByUserId(Long userId);
}
