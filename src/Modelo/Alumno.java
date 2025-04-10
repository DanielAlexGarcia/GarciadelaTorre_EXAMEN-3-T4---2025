package Modelo;

public class Alumno {
    private String numControl;
    private String Nombre;
    private String PrimerAp;
    private String segundoAp;
    private byte semestre;
    private String carrera;

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrimerAp() {
        return PrimerAp;
    }

    public void setPrimerAp(String primerAp) {
        PrimerAp = primerAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public byte getSemestre() {
        return semestre;
    }

    public void setSemestre(byte semestre) {
        this.semestre = semestre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Alumno(String numControl, String nombre, String primerAp, String segundoAp, int semestre, String carrera) {
        this.numControl = numControl;
        Nombre = nombre;
        PrimerAp = primerAp;
        this.segundoAp = segundoAp;
        this.semestre = (byte) semestre;
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "numControl='" + numControl + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", PrimerAp='" + PrimerAp + '\'' +
                ", segundoAp='" + segundoAp + '\'' +
                ", semestre=" + semestre +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}


