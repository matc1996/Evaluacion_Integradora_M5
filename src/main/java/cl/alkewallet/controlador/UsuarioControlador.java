package cl.alkewallet.controlador;

import cl.alkewallet.modelo.Usuario;
import cl.alkewallet.servicio.CuentaServicio;
import cl.alkewallet.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CuentaServicio cuentaServicio;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServicio.obtenerTodosLosUsuarios());
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("saldo/{id}")
    public String verSaldo(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "saldo";
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/depositar/{id}")
    public String mostrarFormularioDeposito(@PathVariable Long id, Model model) {
        model.addAttribute("cuentaId", id);
        model.addAttribute("cantidad", 0.0);
        return "deposito";
    }

    @PostMapping("/depositar")
    public String depositar(@RequestParam Long cuentaId, @RequestParam double cantidad) {
        cuentaServicio.depositar(cuentaId, cantidad);
        return "redirect:/usuarios";
    }

    @GetMapping("/retirar/{id}")
    public String mostrarFormularioRetiro(@PathVariable Long id, Model model) {
        model.addAttribute("cuentaId", id);
        model.addAttribute("cantidad", 0.0);
        return "retiro";
    }

    @PostMapping("/retirar")
    public String retirar(@RequestParam Long cuentaId, @RequestParam double cantidad) {
        cuentaServicio.retirar(cuentaId, cantidad);
        return "redirect:/usuarios";
    }
}
