package com.exemplo.api.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private List<String> usuarios = new ArrayList<>();

    @GetMapping
    public List<String> listarUsuarios() {
        return usuarios;
    }

    @GetMapping("/{id}")
    public String buscarUsuario(@PathVariable int id) {
        if (id >= 0 && id < usuarios.size()) {
            return usuarios.get(id);
        } else {
            return "Usuário não encontrado!";
        }
    }

    @PostMapping
    public String criarUsuario(@RequestBody String nome) {
        usuarios.add(nome);
        return "Usuário criado: " + nome;
    }

    @PutMapping("/{id}")
    public String atualizarUsuario(@PathVariable int id, @RequestBody String novoNome) {
        if (id >= 0 && id < usuarios.size()) {
            usuarios.set(id, novoNome);
            return "Usuário atualizado para: " + novoNome;
        } else {
            return "Usuário não encontrado!";
        }
    }

    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable int id) {
        if (id >= 0 && id < usuarios.size()) {
            String removido = usuarios.remove(id);
            return "Usuário removido: " + removido;
        } else {
            return "Usuário não encontrado!";
        }
    }
}
