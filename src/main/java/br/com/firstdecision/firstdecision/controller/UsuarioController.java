package br.com.firstdecision.firstdecision.controller;

import br.com.firstdecision.firstdecision.model.Usuario;
import br.com.firstdecision.firstdecision.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/usuarios")
    public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

        @PostMapping
        public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
            Usuario novoUsuario = usuarioService.registrarUsuario(usuario);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        }
}
