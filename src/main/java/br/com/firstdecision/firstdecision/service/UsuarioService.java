package br.com.firstdecision.firstdecision.service;

import br.com.firstdecision.firstdecision.model.Usuario;
import br.com.firstdecision.firstdecision.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataAccessException e) {
            System.out.println("Erro ao salvar o usuário: " + e.getMessage());
            return null;
        }
    }
}
