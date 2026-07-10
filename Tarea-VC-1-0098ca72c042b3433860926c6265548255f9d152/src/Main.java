import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SistemaUsuarios sistema = SistemaUsuarios.getInstancia();
    private static Usuario usuarioLogueado = null;

    public static void main(String[] args) {

        boolean salir = false;

        while (!salir) {
            if (usuarioLogueado == null) {
                salir = mostrarMenuPrincipal();
            } else {
                salir = mostrarMenuAdministrador();
            }
        }

        System.out.println("Programa finalizado.");
    }

    private static boolean mostrarMenuPrincipal() {

        System.out.println("\n=== SISTEMA DE GESTIÓN DE USUARIOS ===");
        System.out.println("1. Iniciar sesión administrador");
        System.out.println("2. Crear cuenta administrador");
        System.out.println("3. Reiniciar contraseña administrador");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarAdministrador();
                    break;
                case 3:
                    reiniciarPasswordAdministrador();
                    break;
                case 4:
                    return true;
                default:
                    System.out.println("La opción ingresada no existe.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un número.");
            scanner.nextLine();
        }

        return false;
    }

    private static boolean mostrarMenuAdministrador() {

        System.out.println("\n=== MENÚ DE ADMINISTRADOR ===");
        System.out.println("Usuario: " + usuarioLogueado.getNombre());
        System.out.println("1. Alta de Tester");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Buscar usuario por email");
        System.out.println("4. Mi perfil");
        System.out.println("5. Cambiar mi contraseña");
        System.out.println("6. Eliminar usuario Tester");
        System.out.println("7. Cerrar sesión");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarTester();
                    break;
                case 2:
                    sistema.listarUsuarios();
                    break;
                case 3:
                    buscarUsuario();
                    break;
                case 4:
                    mostrarMiPerfil();
                    break;
                case 5:
                    cambiarMiPassword();
                    break;
                case 6:
                    eliminarTester();
                    break;
                case 7:
                    cerrarSesion();
                    break;
                case 8:
                    return true;
                default:
                    System.out.println("La opción ingresada no existe.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un número.");
            scanner.nextLine();
        }

        return false;
    }

    private static void iniciarSesion() {

        boolean volverAlMenu = false;

        while (!volverAlMenu && usuarioLogueado == null) {

            System.out.println("\n--- INICIAR SESIÓN ADMINISTRADOR ---");

            System.out.print("Email: ");
            String email = scanner.nextLine();

            if (!sistema.emailValido(email)) {
                System.out.println("Error: El formato del email no es válido.");
                volverAlMenu = menuReintentarOVolver();
                continue;
            }

            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            try {
                Usuario usuario = sistema.login(email, password);

                if (usuario instanceof Admin) {
                    usuarioLogueado = usuario;
                    System.out.println("Inicio de sesión correcto. Bienvenido/a " + usuario.getNombre() + ".");
                    volverAlMenu = true;
                } else {
                    System.out.println("Error: Perfil de usuario NO administrador.");
                    volverAlMenu = menuReintentarOVolver();
                }

            } catch (UsuarioNoEncontradoException e) {
                System.out.println("Error: " + e.getMessage());
                volverAlMenu = menuReintentarOVolver();
            }
        }
    }

    private static void registrarAdministrador() {

        boolean volverAlMenu = false;

        while (!volverAlMenu) {

            System.out.println("\n--- CREAR CUENTA ADMINISTRADOR ---");

            try {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Contraseña: ");
                String password = scanner.nextLine();

                System.out.print("Confirmar contraseña: ");
                String confirmarPassword = scanner.nextLine();

                if (!password.equals(confirmarPassword)) {
                    throw new DatosInvalidosException("Las contraseñas no coinciden.");
                }

                System.out.print("País de nacimiento: ");
                String pais = scanner.nextLine();

                Admin admin = new Admin(nombre, apellido, email, password, pais);
                sistema.registrarUsuario(admin);

                System.out.println("Administrador registrado correctamente.");
                volverAlMenu = true;

            } catch (EmailDuplicadoException | DatosInvalidosException e) {
                System.out.println("Error: " + e.getMessage());
                volverAlMenu = menuReintentarOVolver();
            }
        }
    }

    private static void reiniciarPasswordAdministrador() {

        boolean volverAlMenu = false;

        while (!volverAlMenu) {

            System.out.println("\n--- REINICIAR CONTRASEÑA ADMINISTRADOR ---");

            try {
                System.out.print("Email: ");
                String email = scanner.nextLine();

                if (!sistema.emailValido(email)) {
                    throw new DatosInvalidosException("El formato del email no es válido.");
                }

                Usuario usuario = sistema.buscarPorEmail(email);

                if (!(usuario instanceof Admin)) {
                    throw new UsuarioNoEncontradoException("Usuario NO existe.");
                }

                System.out.print("Nueva contraseña: ");
                String nuevaPassword = scanner.nextLine();

                System.out.print("Confirmar nueva contraseña: ");
                String confirmarPassword = scanner.nextLine();

                if (!nuevaPassword.equals(confirmarPassword)) {
                    throw new DatosInvalidosException("Las contraseñas no coinciden.");
                }

                sistema.cambiarPassword(email, nuevaPassword);

                System.out.println("Contraseña actualizada correctamente.");
                volverAlMenu = true;

            } catch (UsuarioNoEncontradoException | DatosInvalidosException e) {
                System.out.println("Error: " + e.getMessage());
                volverAlMenu = menuReintentarOVolver();
            }
        }
    }

    private static void registrarTester() {

        boolean volverAlMenu = false;

        while (!volverAlMenu) {

            System.out.println("\n--- ALTA DE TESTER ---");

            try {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Contraseña: ");
                String password = scanner.nextLine();

                System.out.print("Confirmar contraseña: ");
                String confirmarPassword = scanner.nextLine();

                if (!password.equals(confirmarPassword)) {
                    throw new DatosInvalidosException("Las contraseñas no coinciden.");
                }

                System.out.print("País de nacimiento: ");
                String pais = scanner.nextLine();

                TipoTester tipoTester = seleccionarTipoTester();

                Tester tester = new Tester(nombre, apellido, email, password, pais, tipoTester);
                sistema.registrarUsuario(tester);

                System.out.println("Tester registrado correctamente.");
                volverAlMenu = true;

            } catch (EmailDuplicadoException | DatosInvalidosException e) {
                System.out.println("Error: " + e.getMessage());
                volverAlMenu = menuReintentarOVolver();
            }
        }
    }

    private static TipoTester seleccionarTipoTester()
            throws DatosInvalidosException {

        System.out.println("\nTipo de Tester:");
        System.out.println("1. Junior");
        System.out.println("2. Senior");
        System.out.println("3. Líder");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    return TipoTester.JUNIOR;
                case 2:
                    return TipoTester.SENIOR;
                case 3:
                    return TipoTester.LIDER;
                default:
                    throw new DatosInvalidosException("El tipo de Tester seleccionado no existe.");
            }

        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new DatosInvalidosException("Debe ingresar un número para seleccionar el tipo de Tester.");
        }
    }

    private static void buscarUsuario() {

        boolean volverAlMenu = false;

        while (!volverAlMenu) {

            System.out.println("\n--- BUSCAR USUARIO ---");

            System.out.print("Ingrese el email: ");
            String email = scanner.nextLine();

            try {
                if (!sistema.emailValido(email)) {
                    throw new DatosInvalidosException("El formato del email no es válido.");
                }

                Usuario usuario = sistema.buscarPorEmail(email);

                System.out.println("\nUsuario encontrado:");
                System.out.println(usuario);

                volverAlMenu = true;

            } catch (UsuarioNoEncontradoException | DatosInvalidosException e) {
                System.out.println("Error: " + e.getMessage());
                volverAlMenu = menuReintentarOVolver();
            }
        }
    }

    private static void mostrarMiPerfil() {

        boolean volverAlMenu = false;

        while (!volverAlMenu) {

            System.out.println("\n--- MI PERFIL ---");
            System.out.println("Nombre: " + usuarioLogueado.getNombre());
            System.out.println("Apellido: " + usuarioLogueado.getApellido());
            System.out.println("Email: " + usuarioLogueado.getEmail());
            System.out.println("País de nacimiento: " + usuarioLogueado.getPaisNacimiento());
            System.out.println("Perfil: Administrador");

            System.out.println("\n1. Editar perfil");
            System.out.println("2. Volver al menú");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    editarDatosPerfil();
                    break;
                case "2":
                    volverAlMenu = true;
                    break;
                default:
                    System.out.println("La opción ingresada no existe.");
            }
        }
    }

    private static void editarDatosPerfil() {

        System.out.println("\n--- EDITAR PERFIL ---");
        System.out.println("Deje el campo vacío y presione Enter para mantener el dato actual.");

        System.out.print("Nuevo nombre (" + usuarioLogueado.getNombre() + "): ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo apellido (" + usuarioLogueado.getApellido() + "): ");
        String apellido = scanner.nextLine();

        System.out.print("Nuevo país de nacimiento (" + usuarioLogueado.getPaisNacimiento() + "): ");
        String pais = scanner.nextLine();

        try {
            sistema.actualizarPerfil(usuarioLogueado.getEmail(), nombre, apellido, pais);
            System.out.println("Perfil actualizado correctamente.");

        } catch (UsuarioNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cambiarMiPassword() {

        boolean volverAlMenu = false;

        while (!volverAlMenu) {

            System.out.println("\n--- CAMBIAR MI CONTRASEÑA ---");

            try {
                System.out.print("Nueva contraseña: ");
                String nuevaPassword = scanner.nextLine();

                System.out.print("Confirmar nueva contraseña: ");
                String confirmarPassword = scanner.nextLine();

                if (!nuevaPassword.equals(confirmarPassword)) {
                    throw new DatosInvalidosException("Las contraseñas no coinciden.");
                }

                sistema.cambiarPassword(usuarioLogueado.getEmail(), nuevaPassword);

                System.out.println("Contraseña actualizada correctamente.");
                volverAlMenu = true;

            } catch (UsuarioNoEncontradoException | DatosInvalidosException e) {
                System.out.println("Error: " + e.getMessage());
                volverAlMenu = menuReintentarOVolver();
            }
        }
    }

    private static void eliminarTester() {

        boolean volverAlMenu = false;

        while (!volverAlMenu) {

            System.out.println("\n--- ELIMINAR USUARIO TESTER ---");

            ArrayList<Tester> testers = sistema.obtenerTesters();

            if (testers.isEmpty()) {
                System.out.println("No hay usuarios Tester registrados.");
                return;
            }

            System.out.println("\nTESTERS REGISTRADOS:");

            for (int i = 0; i < testers.size(); i++) {
                Tester tester = testers.get(i);

                System.out.println(
                        (i + 1) + ". " +
                                tester.getNombre() + " " +
                                tester.getApellido() +
                                " | " + tester.getEmail() +
                                " | " + tester.obtenerTipoUsuario()
                );
            }

            System.out.println("0. Volver al menú");
            System.out.print("Seleccione el Tester a eliminar: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 0) {
                    volverAlMenu = true;
                } else if (opcion < 1 || opcion > testers.size()) {
                    System.out.println("La opción ingresada no existe.");
                } else {
                    Tester testerSeleccionado = testers.get(opcion - 1);

                    System.out.println("\nUsuario seleccionado:");
                    System.out.println(testerSeleccionado);

                    System.out.println("\n1. Confirmar eliminación");
                    System.out.println("2. Cancelar");
                    System.out.print("Seleccione una opción: ");

                    String confirmar = scanner.nextLine();

                    switch (confirmar) {
                        case "1":
                            sistema.eliminarUsuario(testerSeleccionado.getEmail());
                            System.out.println("Usuario eliminado correctamente.");
                            volverAlMenu = true;
                            break;

                        case "2":
                            System.out.println("Eliminación cancelada.");
                            volverAlMenu = true;
                            break;

                        default:
                            System.out.println("La opción ingresada no existe.");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número.");
                scanner.nextLine();

            } catch (UsuarioNoEncontradoException | DatosInvalidosException e) {
                System.out.println("Error: " + e.getMessage());
                volverAlMenu = menuReintentarOVolver();
            }
        }
    }

    private static void cerrarSesion() {
        System.out.println("Sesión cerrada correctamente. Hasta luego, " + usuarioLogueado.getNombre() + ".");
        usuarioLogueado = null;
    }

    private static boolean menuReintentarOVolver() {

        while (true) {
            System.out.println("\n1. Intentar nuevamente");
            System.out.println("2. Volver al menú");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    System.out.println("La opción ingresada no existe.");
            }
        }
    }
}