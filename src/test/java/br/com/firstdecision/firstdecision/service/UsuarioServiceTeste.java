package br.com.firstdecision.firstdecision.service;

import br.com.firstdecision.firstdecision.model.Usuario;
import br.com.firstdecision.firstdecision.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioServiceTeste {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IUsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    public void testRegistrarUsuario() {

        Usuario usuario = new Usuario();
        usuario.nome = "John";
        usuario.email = "john@example.com";
        usuario.senha = "teste123";
        usuario.confirmacaoSenha = "teste123";

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);

        verify(usuarioRepository, times(1)).save(usuario);

        assertEquals(usuario, usuarioRegistrado);
    }

    @Test
    public void testRegistrarUsuarioComErro() {

        Usuario usuario = new Usuario();
        usuario.nome = "John";
        usuario.email = "john@example.com";
        usuario.senha = "teste123";
        usuario.confirmacaoSenha = "teste123";

        when(usuarioRepository.save(any(Usuario.class))).thenThrow(new DataAccessException("Erro ao salvar o usuário") {});

        Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);

        verify(usuarioRepository, times(1)).save(usuario);

        assertNull(usuarioRegistrado);
    }
}
