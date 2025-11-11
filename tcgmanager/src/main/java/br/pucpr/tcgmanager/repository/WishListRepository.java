package br.pucpr.tcgmanager.repository;

import br.pucpr.tcgmanager.model.WishList;
import br.pucpr.tcgmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUser(User user);
    Optional<WishList> findByUserIdAndCardId(Long userId, Long cardId);
    List<WishList> findByUserId(Long userId);
}
