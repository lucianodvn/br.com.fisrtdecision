package br.com.firstdecision.firstdecision.controller;

import br.com.firstdecision.firstdecision.model.Usuario;
import br.com.firstdecision.firstdecision.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioControllerTeste {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarUsuario() {
        Usuario usuario = new Usuario();
        usuario.nome = "John";
        usuario.email = "john@example.com";
        usuario.senha = "teste123";
        usuario.confirmacaoSenha = "teste123";

        when(usuarioService.registrarUsuario(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> responseEntity = usuarioController.criarUsuario(usuario);

        verify(usuarioService, times(1)).registrarUsuario(usuario);

        assertSame(HttpStatus.CREATED, responseEntity.getStatusCode());

        assertSame(usuario, responseEntity.getBody());
    }
}
