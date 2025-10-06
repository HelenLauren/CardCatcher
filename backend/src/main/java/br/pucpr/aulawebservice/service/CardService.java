package br.pucpr.aulawebservice.service;

import br.pucpr.aulawebservice.dto.CardDTO;
import br.pucpr.aulawebservice.exception.ApiException;
import br.pucpr.aulawebservice.model.Card;
import br.pucpr.aulawebservice.repository.CardRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardDTO> listAll() {
        return cardRepository.findAll().stream()
                .map(CardDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CardDTO getById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ApiException("ERR001", "Card not found"));
        return CardDTO.fromEntity(card);
    }

    public CardDTO create(Card card) {
        cardRepository.save(card);
        return CardDTO.fromEntity(card);
    }

    public CardDTO update(Long id, Card cardData) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ApiException("ERR001", "Card not found"));
        card.setName(cardData.getName());
        card.setPrice(cardData.getPrice());
        card.setOfficialCollection(cardData.getOfficialCollection());
        cardRepository.save(card);
        return CardDTO.fromEntity(card);
    }

    public void delete(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ApiException("ERR001", "Card not found"));
        cardRepository.delete(card);
    }
}
