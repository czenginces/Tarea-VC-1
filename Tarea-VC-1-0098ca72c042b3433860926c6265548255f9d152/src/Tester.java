public class Tester extends Usuario {

    private TipoTester tipoTester;

    public Tester(String nombre, String apellido, String email,
                  String password, String paisNacimiento,
                  TipoTester tipoTester) {

        super(nombre, apellido, email, password, paisNacimiento);
        this.tipoTester = tipoTester;
    }

    public TipoTester getTipoTester() {
        return tipoTester;
    }

    public void setTipoTester(TipoTester tipoTester) {
        this.tipoTester = tipoTester;
    }

    @Override
    public String obtenerTipoUsuario() {
        return "Tester " + tipoTester;
    }
}