public class Admin extends Usuario {

    public Admin(String nombre, String email, String contrasena, String apellido, String paisNacimiento) {
        super(nombre, email, contrasena, apellido, paisNacimiento);
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }

    @Override
    public String mostrarPermisos() {
        return "Puede registrar, listar y buscar usuarios.";
    }
}
