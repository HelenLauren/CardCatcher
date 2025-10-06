package br.pucpr.aulawebservice.repository;

import br.pucpr.aulawebservice.model.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserCollectionRepository extends JpaRepository<UserCollection, Long> {
    List<UserCollection> findByOwner_Username(String username);
}
