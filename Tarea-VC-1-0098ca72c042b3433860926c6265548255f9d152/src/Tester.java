public class Tester extends Usuario {

    public Tester(String nombre, String email, String contrasena, String apellido, String paisNacimiento) {
        super(nombre, email, contrasena, apellido, paisNacimiento);
    }

    @Override
    public String getTipoUsuario() {
        return "Tester";
    }

    @Override
    public String mostrarPermisos() {
        return "Puede realizar pruebas del sistema.";
    }
}
