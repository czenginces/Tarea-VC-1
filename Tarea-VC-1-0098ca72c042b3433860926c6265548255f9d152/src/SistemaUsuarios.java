import java.util.ArrayList;

public class SistemaUsuarios {

    private static SistemaUsuarios instancia;
    private ArrayList<Usuario> usuarios;

    private SistemaUsuarios() {
        usuarios = new ArrayList<>();
        cargarUsuariosIniciales();
    }

    public static SistemaUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new SistemaUsuarios();
        }
        return instancia;
    }

    private void cargarUsuariosIniciales() {
        usuarios.add(new Admin("Yanis", "Correa", "yaniscorrea@gmail.com", "12345678", "Uruguay"));
        usuarios.add(new Admin("Leonardo", "Perez", "leonardoperez@gmail.com", "12345678", "Uruguay"));
        usuarios.add(new Tester("Juan", "Perez", "juan@ces.com", "12345678", "Uruguay", TipoTester.JUNIOR));
        usuarios.add(new Tester("Jeniffer", "Mello", "jeniffer@gmail.com", "12345678", "Uruguay", TipoTester.JUNIOR));
        usuarios.add(new Tester("Nahuel", "Torena", "nahuel@gmail.com", "12345678", "Uruguay", TipoTester.SENIOR));
        usuarios.add(new Tester("Mariana", "Travieso", "mariana@gmail.com", "12345678", "Uruguay", TipoTester.SENIOR));
        usuarios.add(new Tester("Dardo", "Deleon", "dardo@gmail.com", "12345678", "Uruguay", TipoTester.LIDER));
    }

    public void registrarUsuario(Usuario usuario)
            throws EmailDuplicadoException, DatosInvalidosException {

        validarDatos(usuario);

        if (existeEmail(usuario.getEmail())) {
            throw new EmailDuplicadoException("El email ya se encuentra registrado.");
        }

        usuarios.add(usuario);
    }

    private void validarDatos(Usuario usuario) throws DatosInvalidosException {

        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre es obligatorio.");
        }

        if (usuario.getApellido() == null || usuario.getApellido().trim().isEmpty()) {
            throw new DatosInvalidosException("El apellido es obligatorio.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new DatosInvalidosException("El email es obligatorio.");
        }

        if (!emailValido(usuario.getEmail())) {
            throw new DatosInvalidosException("El formato del email no es válido.");
        }

        if (usuario.getPassword() == null || usuario.getPassword().length() < 8) {
            throw new DatosInvalidosException("La contraseña debe tener al menos 8 caracteres.");
        }

        if (usuario.getPaisNacimiento() == null || usuario.getPaisNacimiento().trim().isEmpty()) {
            throw new DatosInvalidosException("El país de nacimiento es obligatorio.");
        }
    }

    public boolean emailValido(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public boolean existeEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public Usuario login(String email, String password)
            throws UsuarioNoEncontradoException {

        Usuario usuario = buscarPorEmail(email);

        if (!usuario.getPassword().equals(password)) {
            throw new UsuarioNoEncontradoException("Contraseña incorrecta.");
        }

        return usuario;
    }

    public Usuario buscarPorEmail(String email)
            throws UsuarioNoEncontradoException {

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }

        throw new UsuarioNoEncontradoException("No se encontró un usuario con ese email.");
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE USUARIOS ---");

        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public ArrayList<Tester> obtenerTesters() {
        ArrayList<Tester> testers = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Tester) {
                testers.add((Tester) usuario);
            }
        }

        return testers;
    }

    public void cambiarPassword(String email, String nuevaPassword)
            throws UsuarioNoEncontradoException, DatosInvalidosException {

        if (nuevaPassword == null || nuevaPassword.length() < 8) {
            throw new DatosInvalidosException("La contraseña debe tener al menos 8 caracteres.");
        }

        Usuario usuario = buscarPorEmail(email);
        usuario.setPassword(nuevaPassword);
    }

    public void actualizarPerfil(String emailActual, String nuevoNombre,
                                 String nuevoApellido, String nuevoPais)
            throws UsuarioNoEncontradoException {

        Usuario usuario = buscarPorEmail(emailActual);

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            usuario.setNombre(nuevoNombre);
        }

        if (nuevoApellido != null && !nuevoApellido.trim().isEmpty()) {
            usuario.setApellido(nuevoApellido);
        }

        if (nuevoPais != null && !nuevoPais.trim().isEmpty()) {
            usuario.setPaisNacimiento(nuevoPais);
        }
    }

    public void eliminarUsuario(String email)
            throws UsuarioNoEncontradoException, DatosInvalidosException {

        Usuario usuario = buscarPorEmail(email);

        if (usuario instanceof Admin) {
            throw new DatosInvalidosException("No se pueden eliminar usuarios administradores.");
        }

        usuarios.remove(usuario);
    }
}