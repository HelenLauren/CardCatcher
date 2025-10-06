package br.pucpr.aulawebservice.service;

import br.pucpr.aulawebservice.exception.BusinessException;
import br.pucpr.aulawebservice.model.Card;
import br.pucpr.aulawebservice.repository.CardRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardService {
    private final CardRepository repo;

    public CardService(CardRepository repo) { this.repo = repo; }

    public List<Card> listAll() { return repo.findAll(); }

    public Card findById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new BusinessException("ERR001", "Card not found"));
    }

    public Card save(Card card) { return repo.save(card); }

    public Card update(Long id, Card updated) {
        Card existing = findById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setCategory(updated.getCategory());
        existing.setOfficialCollection(updated.getOfficialCollection());
        return repo.save(existing);
    }

    public void delete(Long id) { repo.delete(findById(id)); }
}
