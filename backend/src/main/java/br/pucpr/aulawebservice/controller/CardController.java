package br.pucpr.aulawebservice.controller;

import br.pucpr.aulawebservice.dto.CardDTO;
import br.pucpr.aulawebservice.model.Card;
import br.pucpr.aulawebservice.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//somente admins podem ter acesso a adicionar, editar e excluir cartas. Pq sim :)

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CardDTO> create(@RequestBody Card card) {
        return ResponseEntity.ok(service.create(card));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> update(@PathVariable Long id, @RequestBody Card card) {
        return ResponseEntity.ok(service.update(id, card));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
