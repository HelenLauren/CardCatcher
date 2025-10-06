package br.pucpr.aulawebservice.service;

import br.pucpr.aulawebservice.exception.BusinessException;
import br.pucpr.aulawebservice.model.*;
import br.pucpr.aulawebservice.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserCollectionService {
    private final UserCollectionRepository collectionRepo;
    private final UserRepository userRepo;
    private final CardRepository cardRepo;

    public UserCollectionService(UserCollectionRepository collectionRepo, UserRepository userRepo, CardRepository cardRepo) {
        this.collectionRepo = collectionRepo;
        this.userRepo = userRepo;
        this.cardRepo = cardRepo;
    }

    public List<UserCollection> listByUser(String username) {
        return collectionRepo.findByOwner_Username(username);
    }

    public UserCollection create(String username, String name) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new BusinessException("ERR002", "User not found"));
        UserCollection col = new UserCollection(name, user);
        return collectionRepo.save(col);
    }

    public void delete(Long id) {
        UserCollection col = collectionRepo.findById(id)
            .orElseThrow(() -> new BusinessException("ERR003", "Collection not found"));
        collectionRepo.delete(col);
    }
}
