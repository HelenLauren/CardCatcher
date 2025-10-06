package br.pucpr.aulawebservice.service;

import br.pucpr.aulawebservice.dto.UserCollectionDTO;
import br.pucpr.aulawebservice.exception.ApiException;
import br.pucpr.aulawebservice.model.Card;
import br.pucpr.aulawebservice.model.User;
import br.pucpr.aulawebservice.model.UserCollection;
import br.pucpr.aulawebservice.repository.CardRepository;
import br.pucpr.aulawebservice.repository.UserCollectionRepository;
import br.pucpr.aulawebservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Aqui a criação de coleção pode ser feita por todos os usuários.

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

    public List<UserCollectionDTO> listByUser(String username) {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ApiException("ERR002", "User not found"));
        return collectionRepo.findByOwner_Username(user.getUsername())
                .stream()
                .map(UserCollectionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public UserCollectionDTO create(String username, String name) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ApiException("ERR002", "User not found"));
        UserCollection collection = new UserCollection(name, user);
        collectionRepo.save(collection);
        return UserCollectionDTO.fromEntity(collection);
    }

    public void delete(Long id, String username) {
        UserCollection collection = collectionRepo.findById(id)
                .orElseThrow(() -> new ApiException("ERR003", "Collection not found"));
        if (!collection.getOwner().getUsername().equals(username)) {
            throw new ApiException("ERR004", "Not authorized");
        }
        collectionRepo.delete(collection);
    }

    public UserCollectionDTO addCard(Long collectionId, Long cardId, String username) {
        UserCollection collection = collectionRepo.findById(collectionId)
                .orElseThrow(() -> new ApiException("ERR003", "Collection not found"));
        if (!collection.getOwner().getUsername().equals(username)) {
            throw new ApiException("ERR004", "Not authorized");
        }
        Card card = cardRepo.findById(cardId)
                .orElseThrow(() -> new ApiException("ERR001", "Card not found"));
        collection.getCards().add(card);
        collectionRepo.save(collection);
        return UserCollectionDTO.fromEntity(collection);
    }
    
//já trata erros com mensagem codificada.

    public UserCollectionDTO removeCard(Long collectionId, Long cardId, String username) {
        UserCollection collection = collectionRepo.findById(collectionId)
                .orElseThrow(() -> new ApiException("ERR003", "Collection not found"));
        if (!collection.getOwner().getUsername().equals(username)) {
            throw new ApiException("ERR004", "Not authorized");
        }
        Card card = cardRepo.findById(cardId)
                .orElseThrow(() -> new ApiException("ERR001", "Card not found"));
        collection.getCards().remove(card);
        collectionRepo.save(collection);
        return UserCollectionDTO.fromEntity(collection);
    }
}
