package br.pucpr.aulawebservice.controller;

import br.pucpr.aulawebservice.dto.CardDTO;
import br.pucpr.aulawebservice.model.Card;
import br.pucpr.aulawebservice.service.CardService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService service;

    public CardController(CardService service) { this.service = service; }

    @GetMapping
    public List<CardDTO> listAll() {
        return service.listAll().stream().map(CardDTO::fromEntity).toList();
    }

    @PostMapping
    public CardDTO create(@RequestBody Card card) {
        return CardDTO.fromEntity(service.save(card));
    }
}
