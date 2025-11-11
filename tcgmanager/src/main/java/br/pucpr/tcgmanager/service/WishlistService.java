package br.pucpr.tcgmanager.service;

import br.pucpr.tcgmanager.model.*;
import br.pucpr.tcgmanager.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishListRepository wishlistRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public List<WishList> getWishlistByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public WishList addToWishlist(Long userId, Long cardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("ERR-USER-404"));
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("ERR-CARD-404"));
        WishList w = WishList.builder().user(user).card(card).build();
        return wishlistRepository.save(w);
    }

    public void removeFromWishlist(Long userId, Long cardId) {
        WishList w = wishlistRepository.findByUserIdAndCardId(userId, cardId)
                .orElseThrow(() -> new IllegalArgumentException("ERR-WISHLIST-404"));
        wishlistRepository.delete(w);
    }
}
