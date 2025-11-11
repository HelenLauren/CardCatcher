package br.pucpr.tcgmanager.controller;

import br.pucpr.tcgmanager.model.Card;
import br.pucpr.tcgmanager.service.CardService;
import br.pucpr.tcgmanager.repository.CardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardRepository repository;
    private final CardService service;

    public CardController(CardRepository repository, CardService service) {
        this.repository = repository;
        this.service = service;
    }

    // --- CRUD local (banco) ---

    @GetMapping
    public List<Card> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable Long id) {
        Optional<Card> card = repository.findById(id);
        return card.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Card create(@RequestBody Card card) {
        return repository.save(card);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody Card details) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(details.getName());
                    existing.setType(details.getType());
                    existing.setRarity(details.getRarity());
                    existing.setHp(details.getHp());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- Integração com API Pokémon TCG ---

    @GetMapping("/search")
    public ResponseEntity<List<Card>> searchFromApi(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByNameFromApi(name));
    }

    @PostMapping("/import")
    public ResponseEntity<Card> importFromApi(@RequestParam String name) {
        return ResponseEntity.ok(service.importCardByName(name));
    }
}
