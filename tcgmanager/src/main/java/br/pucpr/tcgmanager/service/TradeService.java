package br.pucpr.tcgmanager.service;

import br.pucpr.tcgmanager.model.Trade;
import br.pucpr.tcgmanager.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService {
    private final TradeRepository tradeRepository;

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade createTrade(Trade trade) {
        trade.setStatus("PENDING");
        return tradeRepository.save(trade);
    }

    public void deleteTrade(Long id) {
        if (!tradeRepository.existsById(id)) throw new IllegalArgumentException("ERR-TRADE-404");
        tradeRepository.deleteById(id);
    }
}
