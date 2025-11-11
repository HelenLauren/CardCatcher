package br.pucpr.tcgmanager.controller;

import br.pucpr.tcgmanager.model.Trade;
import br.pucpr.tcgmanager.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {
    private final TradeService service;

    @GetMapping
    public List<Trade> all() { return service.getAllTrades(); }

    @PostMapping
    public ResponseEntity<Trade> create(@RequestBody Trade t) { return ResponseEntity.ok(service.createTrade(t)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.deleteTrade(id); return ResponseEntity.noContent().build(); }
}
