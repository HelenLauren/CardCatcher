package br.pucpr.tcgmanager.repository;

import br.pucpr.tcgmanager.model.UserCollectionCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserCollectionCardRepository extends JpaRepository<UserCollectionCard, Long> {
    List<UserCollectionCard> findByUserId(Long userId);
}
