package br.pucpr.aulawebservice.controller;

import br.pucpr.aulawebservice.dto.UserCollectionDTO;
import br.pucpr.aulawebservice.service.UserCollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-collections")
public class UserCollectionController {

    private final UserCollectionService service;

    public UserCollectionController(UserCollectionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserCollectionDTO>> list(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(service.listByUser(user.getUsername()));
    }

    @PostMapping
    public ResponseEntity<UserCollectionDTO> create(@AuthenticationPrincipal UserDetails user,
                                                    @RequestParam String name) {
        return ResponseEntity.ok(service.create(user.getUsername(), name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails user,
                                       @PathVariable Long id) {
        service.delete(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{collectionId}/add-card/{cardId}")
    public ResponseEntity<UserCollectionDTO> addCard(@AuthenticationPrincipal UserDetails user,
                                                     @PathVariable Long collectionId,
                                                     @PathVariable Long cardId) {
        return ResponseEntity.ok(service.addCard(collectionId, cardId, user.getUsername()));
    }

    @PostMapping("/{collectionId}/remove-card/{cardId}")
    public ResponseEntity<UserCollectionDTO> removeCard(@AuthenticationPrincipal UserDetails user,
                                                        @PathVariable Long collectionId,
                                                        @PathVariable Long cardId) {
        return ResponseEntity.ok(service.removeCard(collectionId, cardId, user.getUsername()));
    }
}
