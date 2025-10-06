package br.pucpr.aulawebservice.controller;

import br.pucpr.aulawebservice.dto.UserCollectionDTO;
import br.pucpr.aulawebservice.service.UserCollectionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/collections")
public class UserCollectionController {
    private final UserCollectionService service;

    public UserCollectionController(UserCollectionService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public List<UserCollectionDTO> listUserCollections(@PathVariable String username) {
        return service.listByUser(username).stream().map(UserCollectionDTO::fromEntity).toList();
    }

    @PostMapping("/{username}")
    public UserCollectionDTO create(@PathVariable String username, @RequestParam String name) {
        return UserCollectionDTO.fromEntity(service.create(username, name));
    }
}
