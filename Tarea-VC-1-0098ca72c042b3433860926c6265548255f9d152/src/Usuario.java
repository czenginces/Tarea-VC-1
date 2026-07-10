public abstract class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String paisNacimiento;

    public Usuario(String nombre, String apellido, String email,
                   String password, String paisNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.paisNacimiento = paisNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public abstract String obtenerTipoUsuario();

    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellido +
                " | Email: " + email +
                " | País: " + paisNacimiento +
                " | Tipo: " + obtenerTipoUsuario();
    }
}