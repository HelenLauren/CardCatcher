package br.pucpr.tcgmanager.repository;

import br.pucpr.tcgmanager.model.UserCollectionCard;
import br.pucpr.tcgmanager.model.User;
import br.pucpr.tcgmanager.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserCollectionCardRepository extends JpaRepository<UserCollectionCard, Long> {
    List<UserCollectionCard> findByUser(User user);
    Optional<UserCollectionCard> findByUserAndCard(User user, Card card);
}
