package br.pucpr.tcgmanager.service;

import br.pucpr.tcgmanager.model.*;
import br.pucpr.tcgmanager.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradelistService {
    private final TradeListRepository tradeListRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public List<TradeList> getTradeListByUserId(Long userId) {
        return tradeListRepository.findByUserId(userId);
    }

    public TradeList addToTradeList(Long userId, Long cardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("ERR-USER-404"));
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("ERR-CARD-404"));
        TradeList t = TradeList.builder().user(user).card(card).build();
        return tradeListRepository.save(t);
    }

    public void removeFromTradeList(Long userId, Long cardId) {
        TradeList t = tradeListRepository.findByUserIdAndCardId(userId, cardId)
                .orElseThrow(() -> new IllegalArgumentException("ERR-TRADELIST-404"));
        tradeListRepository.delete(t);
    }
}
