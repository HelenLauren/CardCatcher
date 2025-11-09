package br.pucpr.tcgmanager.controller;

import br.pucpr.tcgmanager.model.Card;
import br.pucpr.tcgmanager.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardRepository repository;

    // List
    @GetMapping
    public List<Card> getAll() {
        return repository.findAll();
    }

    // Find
    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable Long id) {
        Optional<Card> card = repository.findById(id);
        return card.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Add
    @PostMapping
    public Card create(@RequestBody Card card) {
        return repository.save(card);
    }

    // Updt
    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody Card cardDetails) {
        Optional<Card> optionalCard = repository.findById(id);
        if (!optionalCard.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Card card = optionalCard.get();
        card.setName(cardDetails.getName());
        card.setType(cardDetails.getType());
        card.setRarity(cardDetails.getRarity());
        card.setHp(cardDetails.getHp());
        return ResponseEntity.ok(repository.save(card));
    }

    // Del
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
