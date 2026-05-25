
public class Usuario {

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
}
