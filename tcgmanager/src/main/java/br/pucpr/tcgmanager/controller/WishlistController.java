package br.pucpr.tcgmanager.controller;

import br.pucpr.tcgmanager.model.WishList;
import br.pucpr.tcgmanager.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping("/{userId}")
    public List<WishList> get(@PathVariable Long userId) {
        return wishlistService.getWishlistByUserId(userId);
    }

    @PostMapping("/{userId}/{cardId}")
    public ResponseEntity<WishList> add(@PathVariable Long userId, @PathVariable Long cardId) {
        return ResponseEntity.ok(wishlistService.addToWishlist(userId, cardId));
    }

    @DeleteMapping("/{userId}/{cardId}")
    public ResponseEntity<Void> remove(@PathVariable Long userId, @PathVariable Long cardId) {
        wishlistService.removeFromWishlist(userId, cardId);
        return ResponseEntity.noContent().build();
    }
}
