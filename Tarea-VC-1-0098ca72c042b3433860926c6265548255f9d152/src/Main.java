import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static SistemaUsuarios sistemaUsuarios = new SistemaUsuarios();

    public static void main(String[] args) {

        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Login");
            System.out.println("2. Registro administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                login();
            } else if (opcion == 2) {
                registrarAdministrador();
            } else if (opcion == 3) {
                System.out.println("Saliendo del sistema...");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    public static void login() {
        System.out.println("\n=== LOGIN ===");

        if (!sistemaUsuarios.existeAdministrador()) {
            System.out.println("No hay usuario administrador registrado.");
            return;
        }

        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        if (email.trim().isEmpty() || contrasena.trim().isEmpty()) {
            System.out.println("Debe ingresar email y contraseña.");
            return;
        }

        Admin administrador = sistemaUsuarios.buscarAdministrador(email, contrasena);

        if (administrador != null) {
            System.out.println("Login exitoso. Bienvenido " + administrador.getNombre());
            menuAdministrador();
        } else {
            System.out.println("Email o contraseña incorrectos.");
        }
    }

    public static void registrarAdministrador() {
        System.out.println("\n=== REGISTRO ADMINISTRADOR ===");

        if (sistemaUsuarios.existeAdministrador()) {
            System.out.println("Ya existe un administrador registrado.");
            return;
        }

        Usuario administrador = crearUsuarioDesdeConsola("Administrador");
        sistemaUsuarios.agregarUsuario(administrador);

        System.out.println("\nUsuario administrador registrado correctamente.");
    }

    public static void menuAdministrador() {
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Registrar tester");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Buscar usuario");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                registrarTester();
            } else if (opcion == 2) {
                sistemaUsuarios.listarUsuarios();
            } else if (opcion == 3) {
                buscarUsuario();
            } else if (opcion == 4) {
                System.out.println("Volviendo al menú principal...");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    public static void registrarTester() {
        System.out.println("\n=== REGISTRO TESTER ===");

        Usuario tester = crearUsuarioDesdeConsola("Tester");
        sistemaUsuarios.agregarUsuario(tester);

        System.out.println("\nUsuario tester registrado correctamente.");
    }

    public static void buscarUsuario() {
        System.out.println("\n=== BUSCAR USUARIO ===");
        System.out.print("Ingrese el email del usuario a buscar: ");
        String email = scanner.nextLine();

        Usuario usuarioEncontrado = sistemaUsuarios.buscarUsuarioPorEmail(email);

        if (usuarioEncontrado != null) {
            System.out.println("\nUsuario encontrado:");
            usuarioEncontrado.mostrarDatos();
        } else {
            System.out.println("No se encontró un usuario con ese email.");
        }
    }

    public static Usuario crearUsuarioDesdeConsola(String tipoUsuario) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.print("Repetir contraseña: ");
        String repetirContrasena = scanner.nextLine();

        System.out.print("País de nacimiento: ");
        String paisNacimiento = scanner.nextLine();

        if (nombre.trim().isEmpty() ||
                apellido.trim().isEmpty() ||
                email.trim().isEmpty() ||
                contrasena.trim().isEmpty() ||
                repetirContrasena.trim().isEmpty() ||
                paisNacimiento.trim().isEmpty()) {

            System.out.println("Todos los campos son obligatorios.");
            return crearUsuarioDesdeConsola(tipoUsuario);
        }

        if (!contrasena.equals(repetirContrasena)) {
            System.out.println("Las contraseñas no coinciden.");
            return crearUsuarioDesdeConsola(tipoUsuario);
        }

        if (tipoUsuario.equals("Administrador")) {
            return new Admin(nombre, email, contrasena, apellido, paisNacimiento);
        } else {
            return new Tester(nombre, email, contrasena, apellido, paisNacimiento);
        }
    }
}
