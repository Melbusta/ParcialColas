public class Registro {
    private String nombre;
    private String cedula;
    private int edad;
    private double auxilio;
    private boolean esDesplazado;
    private int estrato;
    
    public Registro() {
    }
    public Registro(String nombre, String cedula, int edad, double auxilio, boolean esDesplazado, int estrato) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.auxilio = auxilio;
        this.esDesplazado = esDesplazado;
        this.estrato = estrato;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public double getAuxilio() {
        return auxilio;
    }
    public void setAuxilio(double auxilio) {
        this.auxilio = auxilio;
    }
    public boolean isEsDesplazado() {
        return esDesplazado;
    }
    public void setEsDesplazado(boolean esDesplazado) {
        this.esDesplazado = esDesplazado;
    }
    public int getEstrato() {
        return estrato;
    }
    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", edad=" + edad +
                ", auxilio=" + auxilio +
                ", esDesplazado=" + esDesplazado +
                ", estrato=" + estrato +
                '}';
    }
}