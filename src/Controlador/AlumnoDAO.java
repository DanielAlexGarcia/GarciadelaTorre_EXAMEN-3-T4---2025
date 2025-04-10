package Controlador;

//DAO -> data acces object

import ConexionBBD.ConexionBD;
import Modelo.Alumno;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnoDAO {

    ConexionBD conexionBD = new ConexionBD();

    // =========== METODOS ABCC====================

    // ----------- ALTAS -----------------

    public boolean agregarAlumno(Alumno alumno){
        // Insert into alumnos VALUES()
        String nc = alumno.getNumControl();
        String nom = alumno.getNombre();
        String primerAP = alumno.getPrimerAp();
        String segundoAP = alumno.getSegundoAp();
        String sem = String.valueOf(alumno.getSemestre());
        String car = alumno.getCarrera();

        String sql = "INSERT INTO Alumnos(Num_Control, Nombre, APellido_Paterno, Apellido_Materno, Semestre, Carrera) " +
                "VALUES('"+nc+"','"+nom+"','"+primerAP+"','"+segundoAP+"',"+sem+",'"+car+"')";
        boolean r = conexionBD.ejecutarIntruccionLMD(sql);
        return r;
    }

    public boolean eliminarAlumno(String numControl){
        String sql = "DELETE FROM Alumnos Where Num_Control = '" + numControl+ "'";
        return conexionBD.ejecutarIntruccionLMD(sql);
    }

    public boolean editarAlumno(Alumno alumno){
        String Nom = alumno.getNombre();
        String APP = alumno.getPrimerAp();
        String APS = alumno.getSegundoAp();
        int sem = alumno.getSemestre();
        String Car = alumno.getCarrera();
        String sql = "UPDATE Alumnos SET Nombre = '"+Nom+"', APellido_Paterno = '"+APP+"', Apellido_Materno = '"+APS+"', Semestre = "+sem+", Carrera = '"+Car+"' " +
                "WHERE Num_Control = '"+alumno.getNumControl()+"' LIMIT 1";
        return conexionBD.ejecutarIntruccionLMD(sql);
    }

    public Alumno ObtenerAlumno(String filtro) {
        String sql = "SELECT * FROM Alumnos WHERE Num_Control = '"+filtro+"';";
        System.out.println(sql);
        ResultSet rs = conexionBD.ejecutarInstruccionSQL(sql);
        Alumno a = null;

        try {
            if (rs.next()){

                String nc = rs.getString(1);
                String n = rs.getString(2);
                String pa = rs.getString(3);
                String sa = rs.getString(4);
                int e = rs.getInt(5);
                String c = rs.getString(6);

                a = new Alumno(nc, n, pa, sa, e, c);

            }else{
                System.out.println("no se encontro registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a ;
    }

    public ArrayList<Alumno> mostrarAlumnos(String filtro, boolean[] tipobusqueda) {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        ArrayList<Alumno> listaFiltrada = new ArrayList<>();

        String sql = "SELECT * FROM Alumnos";
        ResultSet rs = conexionBD.ejecutarInstruccionSQL(sql);

        try {
            while (rs.next()) {
                String nc = rs.getString(1);
                String n = rs.getString(2);
                String pa = rs.getString(3);
                String sa = rs.getString(4);
                byte e = rs.getByte(5);
                String c = rs.getString(6);

                Alumno a = new Alumno(nc, n, pa, sa, e, c);
                listaAlumnos.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Validar el array de búsqueda
        if (tipobusqueda == null || tipobusqueda.length < 6) {
            System.out.println("Parámetros de búsqueda no válidos.");
            return new ArrayList<>();
        }

        // Si todos los valores son false, se asume sin filtros
        boolean algunFiltro = false;
        for (boolean b : tipobusqueda) {
            if (b) {
                algunFiltro = true;
                break;
            }
        }

        if (!algunFiltro) {
            System.out.println("No hay ningún parámetro especificado.");
            return listaAlumnos;
        }

        // Aplicar filtros
        for (Alumno al : listaAlumnos) {
            if (tipobusqueda[0] && al.getNumControl().equalsIgnoreCase(filtro)) {
                listaFiltrada.add(al);
            } else if (tipobusqueda[1] && al.getNombre().equalsIgnoreCase(filtro)) {
                listaFiltrada.add(al);
            } else if (tipobusqueda[2] && al.getPrimerAp().equalsIgnoreCase(filtro)) {
                listaFiltrada.add(al);
            } else if (tipobusqueda[3] && al.getSegundoAp().equalsIgnoreCase(filtro)) {
                listaFiltrada.add(al);
            } else if (tipobusqueda[4]) {
                try {
                    int semestre = Integer.parseInt(filtro);
                    if (al.getSemestre() == semestre) {
                        listaFiltrada.add(al);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El filtro no es un número válido para semestre.");
                }
            } else if (tipobusqueda[5] && al.getCarrera().equalsIgnoreCase(filtro)) {
                listaFiltrada.add(al);
            }
        }

        return listaFiltrada;
    }




}
