package cl.alkewallet.servicio;

import cl.alkewallet.modelo.Cuenta;
import cl.alkewallet.repositorio.CuentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServicio {

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaRepositorio.findAll();
    }

    public Cuenta guardarCuenta(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return  cuentaRepositorio.findById(id).orElse(null);
    }

    public void eliminarCuenta(Long id) {
        cuentaRepositorio.deleteById(id);
    }

    public void depositar(Long idCuenta, double cantidad) {
        Cuenta cuenta = cuentaRepositorio.findById(idCuenta).orElse(null);
        if (cuenta != null) {
            cuenta.depositar(cantidad);
            cuentaRepositorio.save(cuenta);
        }
    }

    public void retirar(Long idCuenta, double cantidad) {
        Cuenta cuenta = cuentaRepositorio.findById(idCuenta).orElse(null);
        if (cuenta != null) {
            cuenta.retirar(cantidad);
            cuentaRepositorio.save(cuenta);
        }
    }

}
