package br.pucpr.tcgmanager.controller;

import br.pucpr.tcgmanager.model.TradeList;
import br.pucpr.tcgmanager.service.TradelistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tradelist")
@RequiredArgsConstructor
public class TradelistController {
    private final TradelistService service;

    @GetMapping("/{userId}")
    public List<TradeList> get(@PathVariable Long userId) {
        return service.getTradeListByUserId(userId);
    }

    @PostMapping("/{userId}/{cardId}")
    public ResponseEntity<TradeList> add(@PathVariable Long userId, @PathVariable Long cardId) {
        return ResponseEntity.ok(service.addToTradeList(userId, cardId));
    }

    @DeleteMapping("/{userId}/{cardId}")
    public ResponseEntity<Void> remove(@PathVariable Long userId, @PathVariable Long cardId) {
        service.removeFromTradeList(userId, cardId);
        return ResponseEntity.noContent().build();
    }
}
