import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    //  guarda el administrador registrado
    static Usuario administrador = null;

    public static void main(String[] args) {

        //  variable para controlar el menú
        int opcion = 0;

        //  menú que se repite hasta elegir salir
        while (opcion != 3) {
            System.out.println("\n=== MENÚ ===");
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

    // función para login
    public static void login() {
        System.out.println("\n=== LOGIN ===");

        if (administrador == null) {
            System.out.println("No hay usuario administrador registrado.");
            return;
        }

        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        // valida que no queden campos vacíos
        if (email.trim().isEmpty() || contrasena.trim().isEmpty()) {
            System.out.println("Debe ingresar email y contraseña.");
            return;
        }

        // valida credenciales
        if (email.equals(administrador.getEmail()) &&
                contrasena.equals(administrador.getContrasena())) {

            System.out.println("Login exitoso. Bienvenido " + administrador.getNombre());

        } else {
            System.out.println("Email o contraseña incorrectos.");
        }
    }

    // función para registrar administrador
    public static void registrarAdministrador() {
        System.out.println("\n=== REGISTRO ADMINISTRADOR ===");

        if (administrador != null) {
            System.out.println("Ya existe un administrador registrado.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Email: ");
        String emailRegistro = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasenaRegistro = scanner.nextLine();

        System.out.print("Repetir contraseña: ");
        String repetirContrasena = scanner.nextLine();

        System.out.print("País de nacimiento: ");
        String paisNacimiento = scanner.nextLine();

        // valida campos vacíos
        if (nombre.trim().isEmpty() ||
                apellido.trim().isEmpty() ||
                emailRegistro.trim().isEmpty() ||
                contrasenaRegistro.trim().isEmpty() ||
                repetirContrasena.trim().isEmpty() ||
                paisNacimiento.trim().isEmpty()) {

            System.out.println("Todos los campos son obligatorios.");
            return;
        }

        //  valida que las contraseñas coincidan
        if (!contrasenaRegistro.equals(repetirContrasena)) {
            System.out.println("Las contraseñas no coinciden.");
            return;
        }

        administrador = new Usuario(
                nombre,
                emailRegistro,
                contrasenaRegistro,
                apellido,
                paisNacimiento
        );

        System.out.println("\nUsuario administrador registrado correctamente.");
        System.out.println("Nombre: " + administrador.getNombre());
        System.out.println("Email: " + administrador.getEmail());
    }
}