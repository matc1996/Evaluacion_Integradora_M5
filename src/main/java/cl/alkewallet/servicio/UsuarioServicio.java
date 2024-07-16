package cl.alkewallet.servicio;

import cl.alkewallet.modelo.Usuario;
import cl.alkewallet.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public List<Usuario> obtenerTodosLosUsuarios(){
        return usuarioRepositorio.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Usuario obtenerUsuarioPorId(long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }

}
