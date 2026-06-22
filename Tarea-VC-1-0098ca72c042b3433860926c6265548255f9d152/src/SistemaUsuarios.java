import java.util.ArrayList;

public class SistemaUsuarios {

    private ArrayList<Usuario> usuarios;

    public SistemaUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }

    public Admin buscarAdministrador(String email, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Admin &&
                    usuario.getEmail().equals(email) &&
                    usuario.getContrasena().equals(contrasena)) {
                return (Admin) usuario;
            }
        }
        return null;
    }

    public boolean existeAdministrador() {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Admin) {
                return true;
            }
        }
        return false;
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println("-------------------------");
            usuario.mostrarDatos();
        }
    }
}
