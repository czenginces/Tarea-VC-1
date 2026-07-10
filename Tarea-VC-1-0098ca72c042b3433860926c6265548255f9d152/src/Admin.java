public class Admin extends Usuario {

    public Admin(String nombre, String apellido, String email,
                 String password, String paisNacimiento) {
        super(nombre, apellido, email, password, paisNacimiento);
    }

    @Override
    public String obtenerTipoUsuario() {
        return "Administrador";
    }
}