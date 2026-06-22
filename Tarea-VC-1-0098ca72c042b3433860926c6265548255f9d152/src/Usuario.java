public abstract class Usuario {

    private String nombre;
    private String email;
    private String contrasena;
    private String apellido;
    private String paisNacimiento;

    public Usuario(String nombre, String email, String contrasena, String apellido, String paisNacimiento) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.apellido = apellido;
        this.paisNacimiento = paisNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public abstract String getTipoUsuario();

    public abstract String mostrarPermisos();

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Email: " + email);
        System.out.println("País de nacimiento: " + paisNacimiento);
        System.out.println("Tipo de usuario: " + getTipoUsuario());
        System.out.println("Permisos: " + mostrarPermisos());
    }
}
