package vista;

import Controlador.AlumnoDAO;
import Modelo.Alumno;
import Modelo.ResultSetTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Actions implements ActionListener {


    AlumnoDAO dao = new AlumnoDAO();

    protected void ConsultarBuscar(String FiltroNumC){      //Consulta tabla para encontrar un resultado

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class GUI extends JFrame implements ActionListener{
    boolean[] tipousqueda = new boolean[6];

    AlumnoDAO Adao = new AlumnoDAO();
    Actions accion = new Actions();
    ResultSetTableModel modelo;
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

        // widgets ventana altas alumnos
    JTextField txtNC1, txtNMS1, txtApP1, txtAPM1;
    JButton BAgregar1, BBorrar1, BCancelar1;
    JComboBox<Integer> CBSemestre1;
    JComboBox<String> CBCarrera1;
    JTable tabla1;

        // widgets ventana bajas alumnos
    JTextField txtNC2, txtNMS2, txtApP2, txtAPM2, txtSem, txtCar;
    JButton BBorrar2, BCancelar2, BBuscar2, BEliminar2;
    JTable tabla2;

        // widgets ventana modificasiones alumnos
    JTextField txtNC3, txtNMS3, txtApP3, txtAPM3;
    JButton BBorrar3, BCancelar3, BBuscar3, BGCambios3;
    JComboBox<Integer> CBSemestre3;
    JComboBox<String> CBCarrera3;
    JTable tabla3;

        // widgets ventana consultas alumnos
    JTextField txtNC4, txtNMS4, txtApP4, txtAPM4;
    JButton BBorrar4, BCancelar4, BBuscar4;
    JComboBox<Integer> CBSemestre4;
    JComboBox<String> CBCarrera4;
    JTable tabla4;
    JRadioButton RBTodos, RBNC, RBNMS, RBAPP, RBAPM, RBSEM, RBCAR;

    JInternalFrame VentanaAltasA, VentanaBajasA, VentanaModifA, VentanaConsulA;

        // menuBar
    JMenuBar menu;
    JMenu Alumnos, Docentes;
    JMenuItem altas, bajas, consultas, modificasiones;
    JDesktopPane panel = new JDesktopPane();

    public GUI(){
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Menus e InternalFrames");
        setSize(1020, 740);
        setLocationRelativeTo(null);
        setVisible(true);

        menu = new JMenuBar();
        Alumnos = new JMenu("Alumnos");
        altas = new JMenuItem("Alta");
        bajas = new JMenuItem("Baja");
        consultas = new JMenuItem("Consultar");
        modificasiones = new JMenuItem("Modificar");
        altas.addActionListener(this);
        bajas.addActionListener(this);
        consultas.addActionListener(this);
        modificasiones.addActionListener(this);
        Alumnos.add(altas);
        Alumnos.add(bajas);
        Alumnos.add(consultas);
        Alumnos.add(modificasiones);
        Docentes = new JMenu("Docentes");
        menu.add(Alumnos);
        menu.add(Docentes);
        setJMenuBar(menu);

        // Ventana Alumnos Altas
        VentanaAltasA = new JInternalFrame();
            VentanaAltasA.setResizable(true);
            VentanaAltasA.setDefaultCloseOperation(HIDE_ON_CLOSE);
            VentanaAltasA.setSize(500,500);
            VentanaAltasA.setLayout(gbl);
            VentanaAltasA.setLocation(0, 0); // Posición inicial
            VentanaAltasA.setClosable(true);

            JLabel tituloAltas = new JLabel("ALTAS ALUMNOS");
            tituloAltas.setFont(new Font("Arial", Font.BOLD, 24));
            tituloAltas.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.fill = 2;
        agregarComponente(VentanaAltasA, tituloAltas,0, 0, 5, 1);

        agregarComponente(VentanaAltasA, new JLabel("Numero de control"), 1,1,1,1);
        txtNC1 = new JTextField(20);
        agregarComponente(VentanaAltasA, txtNC1, 2,1,1, 1);

        agregarComponente(VentanaAltasA, new JLabel("Nombres"), 1, 2, 1, 1);
        txtNMS1 = new JTextField(15);
        agregarComponente(VentanaAltasA, txtNMS1, 2, 2, 1, 1);

        BAgregar1 = new JButton("AGREGAR");
        BAgregar1.addActionListener(this);
        agregarComponente(VentanaAltasA, BAgregar1, 3,1, 1,2);

        agregarComponente(VentanaAltasA, new JLabel("Apellido paterno"), 1,3,1,1);
        txtApP1 = new JTextField(20);
        agregarComponente(VentanaAltasA, txtApP1, 2,3,1,1);

        agregarComponente(VentanaAltasA, new JLabel("Apellido mateno"), 1,4,1,1);
        txtAPM1 = new JTextField(20);
        agregarComponente(VentanaAltasA, txtAPM1, 2,4,1,1);

        BBorrar1 = new JButton("BORRAR");
        BBorrar1.addActionListener(this);
        agregarComponente(VentanaAltasA, BBorrar1, 3,3,2,1);

        agregarComponente(VentanaAltasA, new JLabel("Smestre"), 1,5,1,1);
        CBSemestre1 = new JComboBox<Integer>();
        CBSemestre1.addItem(1);
        CBSemestre1.addItem(2);
        CBSemestre1.addItem(3);
        CBSemestre1.addItem(4);
        CBSemestre1.addItem(5);
        CBSemestre1.addItem(6);
        CBSemestre1.addItem(7);
        CBSemestre1.addItem(8);
        CBSemestre1.addItem(9);
        agregarComponente(VentanaAltasA, CBSemestre1, 2,5,1,1);

        agregarComponente(VentanaAltasA, new JLabel("Carrera"), 1,6,1,1);
        CBCarrera1 = new JComboBox<String>();
        CBCarrera1.addItem("SYSTEMAS");
        CBCarrera1.addItem("CONTADURIA");
        CBCarrera1.addItem("ADMINISTRACION DE EMPRESAS");
        agregarComponente(VentanaAltasA, CBCarrera1, 2,6,1,1);

        BCancelar1 = new JButton("CANCELAR");
        BCancelar1.addActionListener(this);
        agregarComponente(VentanaAltasA, BCancelar1, 3,5,2,1);

        tabla1 = new JTable();
        actualizarTabla(tabla1);
        JScrollPane scroll = new JScrollPane(tabla1);
        scroll.setPreferredSize(new Dimension(200,150));
        agregarComponente(VentanaAltasA, scroll, 0,7,5,1);


        panel.add(VentanaAltasA);

            // Ventana alumnos bajas
        VentanaBajasA = new JInternalFrame();
            VentanaBajasA.setResizable(true);
            VentanaBajasA.setDefaultCloseOperation(HIDE_ON_CLOSE);
            VentanaBajasA.setSize(600,500);
            VentanaBajasA.setLayout(gbl);
            VentanaBajasA.setLocation(0, 0); // Posición inicial
            VentanaBajasA.setClosable(true);

            JLabel tituloBajas = new JLabel("ALUMNOS BAJAS");
                tituloBajas.setFont(new Font("Arial", Font.BOLD, 24));
                tituloBajas.setHorizontalAlignment(SwingConstants.CENTER);
                gbc.fill = 2;
            agregarComponente(VentanaBajasA, tituloBajas, 0,0,5,1);

            JPanel panelBajasBuscar = new JPanel(new GridBagLayout());
                agregarComponente(panelBajasBuscar, new JLabel("Numero de control"), 0,0,1,1);
                txtNC2 = new JTextField(20);
                agregarComponente(panelBajasBuscar, txtNC2, 1,0,1,1);

                BBuscar2 = new JButton("BUSCAR");
                BBuscar2.addActionListener(this);
                agregarComponente(panelBajasBuscar, BBuscar2, 2,0,1,1);

                BBorrar2 = new JButton("BORRAR");
                BBorrar2.addActionListener(this);
                agregarComponente(panelBajasBuscar, BBorrar2, 3,0,1,1);

            agregarComponente(VentanaBajasA, panelBajasBuscar, 0,1,5,1);

            agregarComponente(VentanaBajasA, new JLabel("Nombre(s)"), 1,2,1,1);
            txtNMS2 = new JTextField(20);
            txtNMS2.setEditable(false);
            agregarComponente(VentanaBajasA, txtNMS2, 2,2, 1,1);

            agregarComponente(VentanaBajasA, new JLabel("APELLIDO PATERNO"), 1, 3, 1,1);
            txtApP2 = new JTextField(20);
            txtApP2.setEditable(false);
            agregarComponente(VentanaBajasA, txtApP2, 2,3,1,1);

            agregarComponente(VentanaBajasA, new JLabel("APELLIDO MATERNO"), 1, 4, 1,1);
            txtAPM2 = new JTextField(20);
            txtAPM2.setEditable(false);
            agregarComponente(VentanaBajasA, txtAPM2, 2,4,1,1);

            BEliminar2 = new JButton("ELIMINAR");
            BEliminar2.addActionListener(this);
            agregarComponente(VentanaBajasA, BEliminar2, 3,2,1,3);

            agregarComponente(VentanaBajasA, new JLabel("SEMESTRE"), 1,5,1,1);
            txtSem = new JTextField(20);
            txtSem.setEditable(false);
            agregarComponente(VentanaBajasA, txtSem, 2,5,1,1);

            BCancelar2 = new JButton("CANCELAR");
            BCancelar2.addActionListener(this);
            agregarComponente(VentanaBajasA, BCancelar2, 3,5,1,1);

            agregarComponente(VentanaBajasA, new JLabel("CARRERA"), 1,6,1,1);
            txtCar = new JTextField(20);
            txtCar.setEditable(false);
            agregarComponente(VentanaBajasA, txtCar, 2, 6, 1,1 );

            tabla2 = new JTable();
            tabla2.setModel(tabla1.getModel());
            JScrollPane scrol = new JScrollPane(tabla2);
            scrol.setPreferredSize(new Dimension(200,150));
            agregarComponente(VentanaBajasA, scrol, 0,7, 5, 1);

        panel.add(VentanaBajasA);

                // Ventana Alumnos Modificasiones
        VentanaModifA = new JInternalFrame();
            VentanaModifA.setResizable(true);
            VentanaModifA.setDefaultCloseOperation(HIDE_ON_CLOSE);
            VentanaModifA.setSize(600,500);
            VentanaModifA.setLayout(gbl);
            VentanaModifA.setLocation(0, 0); // Posición inicial
            VentanaModifA.setClosable(true);

        JLabel tituloModif = new JLabel("ALUMNOS MODIFICASIONES");
            tituloModif.setFont(new Font("Arial", Font.BOLD, 24));
            tituloModif.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.fill = 2;
        agregarComponente(VentanaModifA, tituloModif, 0,0,5,1);

        JPanel panelModifBuscar = new JPanel(new GridBagLayout());
        agregarComponente(panelModifBuscar, new JLabel("Numero de control"), 0,0,1,1);
        txtNC3 = new JTextField(20);
        agregarComponente(panelModifBuscar, txtNC3, 1,0,1,1);

        BBuscar3 = new JButton("BUSCAR");
        BBuscar3.addActionListener(this);
        agregarComponente(panelModifBuscar, BBuscar3, 2,0,1,1);

        BBorrar3 = new JButton("BORRAR");
        BBorrar3.addActionListener(this);
        agregarComponente(panelModifBuscar, BBorrar3, 3,0,1,1);

        agregarComponente(VentanaModifA, panelModifBuscar, 0,1,5,1);

        agregarComponente(VentanaModifA, new JLabel("Nombre(s)"), 1,2,1,1);
        txtNMS3 = new JTextField(20);
        agregarComponente(VentanaModifA, txtNMS3, 2,2, 1,1);

        agregarComponente(VentanaModifA, new JLabel("APELLIDO PATERNO"), 1, 3, 1,1);
        txtApP3 = new JTextField(20);
        agregarComponente(VentanaModifA, txtApP3, 2,3,1,1);

        agregarComponente(VentanaModifA, new JLabel("APELLIDO MATERNO"), 1, 4, 1,1);
        txtAPM3 = new JTextField(20);
        agregarComponente(VentanaModifA, txtAPM3, 2,4,1,1);

        BGCambios3 = new JButton("GUARDAR CAMBIOS");
        BGCambios3.addActionListener(this);
        agregarComponente(VentanaModifA, BGCambios3, 3,2,1,3);

        agregarComponente(VentanaModifA, new JLabel("SEMESTRE"), 1,5,1,1);
        CBSemestre3 = new JComboBox<Integer>();
        CBSemestre3.addItem(1);
        CBSemestre3.addItem(2);
        CBSemestre3.addItem(3);CBSemestre3.addItem(4);CBSemestre3.addItem(5);
        CBSemestre3.addItem(6);CBSemestre3.addItem(7);CBSemestre3.addItem(8);
        CBSemestre3.addItem(9);
        agregarComponente(VentanaModifA, CBSemestre3, 2,5,1,1);

        BCancelar3 = new JButton("CANCELAR");
        BCancelar3.addActionListener(this);
        agregarComponente (VentanaModifA, BCancelar3, 3,5,1,1);

        agregarComponente(VentanaModifA, new JLabel("CARRERA"), 1,6,1,1);
        CBCarrera3 = new JComboBox<String>();
        CBCarrera3.addItem("SYSTEMAS");
        CBCarrera3.addItem("CONTADURIA");
        CBCarrera3.addItem("ADMINISTRACION DE EMPRESAS");
        agregarComponente(VentanaModifA, CBCarrera3, 2, 6, 1,1 );

        tabla3 = new JTable();
        tabla3.setModel(tabla1.getModel());
        JScrollPane scro = new JScrollPane(tabla3);
        scro.setPreferredSize(new Dimension(200,150));
        agregarComponente(VentanaModifA, scro, 0,7, 5, 1);

        panel.add(VentanaModifA);

                // Ventana Alumnos Consultas
        VentanaConsulA = new JInternalFrame();
            VentanaConsulA.setResizable(true);
            VentanaConsulA.setDefaultCloseOperation(HIDE_ON_CLOSE);
            VentanaConsulA.setSize(600,500);
            VentanaConsulA.setLayout(gbl);
            VentanaConsulA.setLocation(0, 0); // Posición inicial
            VentanaConsulA.setClosable(true);

           JLabel tituloConsult = new JLabel("ALUMNOS CONSULTAS");
           tituloConsult.setFont(new Font("Arial", Font.BOLD, 24));
           tituloConsult.setHorizontalAlignment(SwingConstants.CENTER);
           gbc.fill = 2;
           agregarComponente(VentanaConsulA, tituloConsult, 0,0,5,1);

            JPanel panelBusqueda = new JPanel();
            panelBusqueda.setLayout(new GridBagLayout());
            panelBusqueda.setBorder(BorderFactory.createTitledBorder("Selecciona criterio de búsqueda"));

            RBTodos = new JRadioButton("TODOS");
            RBTodos.addActionListener(this);
            agregarComponente(panelBusqueda, RBTodos,0,0,1,1);
            RBNC = new JRadioButton("NUMERO DE CONTROL");
            RBNC.addActionListener(this);
            agregarComponente(panelBusqueda, RBNC, 1,0,1,1);
            txtNC4 = new JTextField(20);
            txtNC4.setEditable(false);
            agregarComponente(panelBusqueda, txtNC4, 2,0,1,1);

            RBNMS = new JRadioButton("NOMBRE");
            RBNMS.addActionListener(this);
            agregarComponente(panelBusqueda, RBNMS, 1,1,1,1);
            txtNMS4 = new JTextField(20);
            txtNMS4.setEditable(false);
            agregarComponente(panelBusqueda, txtNMS4, 2,1,1,1);

            RBAPP = new JRadioButton("APELLIDO PATERNO");
            RBAPP.addActionListener(this);
            agregarComponente(panelBusqueda, RBAPP,1,2, 1, 1);
            txtApP4 = new JTextField(20);
            txtApP4.setEditable(false);
            agregarComponente(panelBusqueda, txtApP4, 2,2, 1,1);

            BBuscar4 = new JButton("BUSCAR");
            BBuscar4.addActionListener(this);
            agregarComponente(panelBusqueda, BBuscar4, 3,1,1,2);

            RBAPM = new JRadioButton("APELLIDO MATERNO");
            RBAPM.addActionListener(this);
            agregarComponente(panelBusqueda, RBAPM, 1,3,1,1);
            txtAPM4 = new JTextField(20);
            txtAPM4.setEditable(false);
            agregarComponente(panelBusqueda, txtAPM4, 2,3,1,1);

            RBSEM = new JRadioButton("SEMESTRE");
            RBSEM.addActionListener(this);
            agregarComponente(panelBusqueda, RBSEM, 1,4,1,1);
            CBSemestre4 = new JComboBox<Integer>();
            CBSemestre4.addItem(1);
            CBSemestre4.addItem(2);
            CBSemestre4.addItem(3);
            CBSemestre4.addItem(4);
            CBSemestre4.addItem(5);
            CBSemestre4.addItem(6);
            CBSemestre4.addItem(7);
            CBSemestre4.addItem(8);
            CBSemestre4.addItem(9);
            agregarComponente(panelBusqueda, CBSemestre4, 2,4,1,1);

            BBorrar4 = new JButton("BORRAR");
            BBorrar4.addActionListener(this);
            agregarComponente(panelBusqueda, BBorrar4, 3, 3,1,2);

            RBCAR = new JRadioButton("CARRERA");
            RBCAR.addActionListener(this);
            agregarComponente(panelBusqueda, RBCAR, 1,5,1,1);
            CBCarrera4 = new JComboBox<String>();
            CBCarrera4.addItem("SYSTEMAS");
            CBCarrera4.addItem("CONTADURIA");
            CBCarrera4.addItem("ADMINISTRACION DE EMPRESAS");
            agregarComponente(panelBusqueda, CBCarrera4, 2,5, 1, 1);

            BCancelar4 = new JButton("CACELAR");
            BCancelar4.addActionListener(this);
            agregarComponente(panelBusqueda, BCancelar4, 3, 5, 1, 1);

            agregarComponente(VentanaConsulA, panelBusqueda, 0,1, 5, 1);


        tabla4 = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Núm Control", "Nombre", "Apellido Paterno", "Apellido Materno", "Semestre", "Carrera"}
        ));
            JScrollPane scrl = new JScrollPane(tabla4);
            scrl.setPreferredSize(new Dimension(200, 150));
            agregarComponente(VentanaConsulA, scrl, 0,2, 5, 1);

        panel.add(VentanaConsulA);


        add(panel);

    }

    public void actualizarTabla( JTable tabla) {
        try {
            // Si el modelo ya existe, solo actualizas la consulta
            if (modelo != null) {
                modelo.establecerConsulta("SELECT * FROM alumnos");
            } else {
                // Si no existe, lo creas y lo asignas a la tabla
                modelo = new ResultSetTableModel(
                        "org.mariadb.jdbc.Driver",
                        "jdbc:mariadb://localhost:3306/bd_topicos_2025",
                        "SELECT * FROM alumnos"
                );
                tabla.setModel(modelo);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la tabla.");
        }
    }

    private void agregarComponente(JInternalFrame frame, JComponent componente, int x, int y, int w, int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbl.setConstraints(componente, gbc);
        frame.add(componente, gbc);
    }
    private void agregarComponente(JPanel frame, JComponent componente, int x, int y, int w, int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbl.setConstraints(componente, gbc);
        frame.add(componente, gbc);
    }

    protected int indexCarrera(String carrera){
        if(carrera.toUpperCase().equals("SYSTEMAS")){
            return 0;
        }else if (carrera.toUpperCase().equals("CONTADURIA")){
            return 1;
        }else if (carrera.toUpperCase().equals("ADMINISTRACION DE EMPRESAS")){
            return 2;
        }else {
            System.out.println("no se encontro coincidencia");
            return 0;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == altas){
            VentanaAltasA.setVisible(true);
        }else if (e.getSource() == bajas){
            VentanaBajasA.setVisible(true);
            System.out.println("Presiono bajas");
        }else if (e.getSource() == modificasiones){
            JOptionPane.showMessageDialog(null,
                    "Si hay mas de un Alumno con el mismo numero de control\n" +
                            "solo se podra modificar uno de ellos");
            VentanaModifA.setVisible(true);
            System.out.println("Presiono modificasiones");
        }else if (e.getSource() == consultas){
            VentanaConsulA.setVisible(true);
            System.out.println("Presiono consulta");
        }

        // acciones ventana Altas Alumnos
        if (e.getSource() == BAgregar1){
            String NC = txtNC1.getText();
            String nom = txtNMS1.getText();
            String APP = txtApP1.getText();
            String APM = txtAPM1.getText();
            int sem = (Integer)CBSemestre1.getSelectedItem();
            String car = (String) CBCarrera1.getSelectedItem();
            Alumno alum = new Alumno(NC,nom,APP,APM, sem,car);
            boolean n = Adao.agregarAlumno(alum);
            if(n){
                JOptionPane.showMessageDialog(null,
                        "Alumno Agregado a la base de datos");
            }else{
                JOptionPane.showMessageDialog(null,
                        "Error al intentar añadir al alumno");
            }
            actualizarTabla(tabla1);
        }else if (e.getSource() == BBorrar1){
            txtNC1.setText("");
            txtNMS1.setText("");
            txtApP1.setText("");
            txtAPM1.setText("");
            CBSemestre1.setSelectedIndex(0);
            CBCarrera1.setSelectedIndex(0);
        }else if(e.getSource() == BCancelar1){
            VentanaAltasA.setVisible(false);
            txtNC1.setText("");
            txtNMS1.setText("");
            txtApP1.setText("");
            txtAPM1.setText("");
            CBSemestre1.setSelectedIndex(0);
            CBCarrera1.setSelectedIndex(0);
        }

        // acciones Ventana Bajas

        if(e.getSource() == BBuscar2){
            String nc =txtNC2.getText();
            Alumno alum = Adao.ObtenerAlumno(nc);
            txtNMS2.setText(alum.getNombre());
            txtApP2.setText(alum.getPrimerAp());
            txtAPM2.setText(alum.getSegundoAp());
            txtSem.setText(String.valueOf(alum.getSemestre()));
            txtCar.setText(alum.getCarrera());
        }else if(e.getSource() == BBorrar2){
            txtNC2.setText("");
            txtNMS2.setText("");
            txtApP2.setText("");
            txtAPM2.setText("");
            txtSem.setText("");
            txtCar.setText("");
        }else if (e.getSource() == BEliminar2){
            String nc = txtNC2.getText();
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "Si hay mas de un alumno con el mismo numero de control\n" +
                            "Se eliminara sin importar Mayusculas y minusculas.\n¿Estás seguro?",
                    "Confirmar Baja",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (opcion == JOptionPane.YES_NO_OPTION){
                boolean n = Adao.eliminarAlumno(nc);
                if (n){
                    JOptionPane.showMessageDialog(null,
                            "Alumno dado de baja con exito");
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Error al intentar eliminar del registro");
                }
            }else{
                JOptionPane.showMessageDialog(null,
                        "Operacion cancelada");
            }

            actualizarTabla(tabla1);
        } else if (e.getSource() == BCancelar2) {
            VentanaBajasA.setVisible(false);
            txtNC2.setText("");
            txtNMS2.setText("");
            txtApP2.setText("");
            txtAPM2.setText("");
            txtSem.setText("");
            txtCar.setText("");
        }

        // acciones Alumnos modificar
        if(e.getSource() == BBuscar3){
            String nc = txtNC3.getText();
            Alumno al = Adao.ObtenerAlumno(nc);
            txtNC3.setEditable(false);
            txtAPM3.setText(al.getSegundoAp());
            txtApP3.setText(al.getPrimerAp());
            txtNMS3.setText(al.getNombre());
            CBSemestre3.setSelectedIndex(al.getSemestre()-1);
            CBCarrera3.setSelectedItem(String.valueOf(al.getCarrera()));
        }
        else if (e.getSource() == BBorrar3){
            txtNC3.setText("");
            txtAPM3.setText("");
            txtApP3.setText("");
            txtNMS3.setText("");
            CBSemestre3.setSelectedIndex(0);
            CBCarrera3.setSelectedIndex(0);
        }
        else if(e.getSource() == BGCambios3){
            txtNC3.setEditable(true);
            String nc = txtNC3.getText();
            String nom = txtNMS3.getText();
            String APP = txtApP3.getText();
            String APM = txtAPM3.getText();
            int Sem = Integer.parseInt(String.valueOf(CBSemestre3.getSelectedItem()));
            String car =String.valueOf(CBCarrera3.getSelectedItem());
            Alumno al = new Alumno(nc, nom, APP, APM, Sem, car);
            boolean n = Adao.editarAlumno(al);
            if (n){
                JOptionPane.showMessageDialog(null,
                        "Datos actualizados");

                txtNC3.setText("");
                txtAPM3.setText("");
                txtApP3.setText("");
                txtNMS3.setText("");
                CBSemestre3.setSelectedIndex(0);
                CBCarrera3.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null,
                        "Error al intentar actualizar los datos");
            }
            actualizarTabla(tabla1);

        }else if (e.getSource() == BCancelar3){
            txtNC3.setEditable(true);
            VentanaModifA.setVisible(false);
            txtNC3.setText("");
            txtAPM3.setText("");
            txtApP3.setText("");
            txtNMS3.setText("");
            CBSemestre3.setSelectedIndex(0);
            CBCarrera3.setSelectedIndex(0);
        }

        // acciones alumnos consultas

        if (e.getSource() == BBorrar4){
            RBNC.setSelected(false);
            RBTodos.setSelected(false);
            RBCAR.setSelected(false);
            RBAPP.setSelected(false);
            RBNMS.setSelected(false);
            RBAPM.setSelected(false);
            RBSEM.setSelected(false);
            txtNC4.setEditable(true);
            txtAPM4.setEditable(false);
            txtNMS4.setEditable(false);
            txtApP4.setEditable(false);
            txtNC4.setText("");
            txtAPM4.setText("");
            txtNMS4.setText("");
            txtApP4.setText("");
            CBCarrera4.setSelectedIndex(0);
            CBSemestre4.setSelectedIndex(0);
        }
        else if (e.getSource() == BCancelar4){
            RBNC.setSelected(false);
            RBTodos.setSelected(false);
            RBCAR.setSelected(false);
            RBAPP.setSelected(false);
            RBNMS.setSelected(false);
            RBAPM.setSelected(false);
            RBSEM.setSelected(false);
            txtNC4.setEditable(true);
            txtAPM4.setEditable(false);
            txtNMS4.setEditable(false);
            txtApP4.setEditable(false);
            txtNC4.setText("");
            txtAPM4.setText("");
            txtNMS4.setText("");
            txtApP4.setText("");
            CBCarrera4.setSelectedIndex(0);
            CBSemestre4.setSelectedIndex(0);
            VentanaConsulA.setVisible(false);
        }


        if (e.getSource() == RBTodos){
            RBCAR.setSelected(true);
            RBNC.setSelected(true);
            RBAPP.setSelected(true);
            RBNMS.setSelected(true);
            RBAPM.setSelected(true);
            RBSEM.setSelected(true);
            txtNC4.setEditable(true);
            txtAPM4.setEditable(true);
            txtNMS4.setEditable(true);
            txtApP4.setEditable(true);
            for (int i = 0; i< tipousqueda.length; i++){
                tipousqueda[i] = true;
            }
        }else if (e.getSource() == RBNC){
            RBNC.setSelected(true);
            RBTodos.setSelected(false);
            RBCAR.setSelected(false);
            RBAPP.setSelected(false);
            RBNMS.setSelected(false);
            RBAPM.setSelected(false);
            RBSEM.setSelected(false);
            txtNC4.setEditable(true);
            txtAPM4.setEditable(false);
            txtNMS4.setEditable(false);
            txtApP4.setEditable(false);
            for (int i = 0; i< tipousqueda.length; i++){
                tipousqueda[i] = (i == 0);
            }
        } else if (e.getSource() == RBNMS) {
            RBNMS.setSelected(true);
            RBTodos.setSelected(false);
            RBCAR.setSelected(false);
            RBNC.setSelected(false);
            RBAPP.setSelected(false);
            RBAPM.setSelected(false);
            RBSEM.setSelected(false);
            txtNC4.setEditable(false);
            txtAPM4.setEditable(false);
            txtNMS4.setEditable(true);
            txtApP4.setEditable(false);
            for (int i = 0; i< tipousqueda.length; i++){
                tipousqueda[i] = (i == 1);
            }
        } else if (e.getSource() == RBAPP) {
            RBAPP.setSelected(true);
            RBTodos.setSelected(false);
            RBCAR.setSelected(false);
            RBNC.setSelected(false);
            RBAPM.setSelected(false);
            RBSEM.setSelected(false);
            RBNMS.setSelected(false);
            txtNC4.setEditable(false);
            txtAPM4.setEditable(false);
            txtNMS4.setEditable(false);
            txtApP4.setEditable(true);
            for (int i = 0; i< tipousqueda.length; i++){
                tipousqueda[i] = (i == 2);
            }
        } else if (e.getSource() == RBAPM) {
            RBAPM.setSelected(true);
            RBTodos.setSelected(false);
            RBCAR.setSelected(false);
            RBNC.setSelected(false);
            RBAPP.setSelected(false);
            RBSEM.setSelected(false);
            RBNMS.setSelected(false);
            txtNC4.setEditable(false);
            txtAPM4.setEditable(true);
            txtNMS4.setEditable(false);
            txtApP4.setEditable(false);
            for (int i = 0; i< tipousqueda.length; i++){
                tipousqueda[i] = (i == 3);
            }
        }else if (e.getSource() == RBSEM){
            RBSEM.setSelected(true);
            RBTodos.setSelected(false);
            RBCAR.setSelected(false);
            RBNC.setSelected(false);
            RBAPP.setSelected(false);
            RBAPM.setSelected(false);
            RBNMS.setSelected(false);
            txtNC4.setEditable(false);
            txtAPM4.setEditable(false);
            txtNMS4.setEditable(false);
            txtApP4.setEditable(false);
            for (int i = 0; i< tipousqueda.length; i++){
                tipousqueda[i] = (i == 4);
            }
        }
        else if (e.getSource() == RBCAR){
            RBCAR.setSelected(true);
            RBTodos.setSelected(false);
            RBNC.setSelected(false);
            RBAPP.setSelected(false);
            RBAPM.setSelected(false);
            RBSEM.setSelected(false);
            RBNMS.setSelected(false);
            txtNC4.setEditable(false);
            txtAPM4.setEditable(false);
            txtNMS4.setEditable(false);
            txtApP4.setEditable(false);
            for (int i = 0; i< tipousqueda.length; i++){
                tipousqueda[i] = (i == 5);
            }
        } else if (e.getSource() == BBuscar4) {
            String filtros = "";
            if (tipousqueda[1] == true){
                filtros = txtNMS4.getText();
            }if (tipousqueda[2] == true){
                filtros = txtApP4.getText();
            }if (tipousqueda[3]==true) {
                filtros = txtAPM4.getText();
            } if (tipousqueda[4] == true) {
                filtros = String.valueOf(CBSemestre4.getSelectedItem());
                System.out.println(filtros);
            }if (tipousqueda[5]==true) {
                filtros = String.valueOf(CBCarrera4.getSelectedItem());
                System.out.println(filtros);
            }if (tipousqueda[0] == true){
                filtros = txtNC4.getText();
            }
            ArrayList<Alumno> listAlum = Adao.mostrarAlumnos(filtros, tipousqueda);
            actualizarDatos(tabla4, listAlum);
        }

    }
    protected void actualizarDatos(JTable tabla, ArrayList<Alumno> listaFiltrada){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiar datos anteriores

        for (Alumno alumno : listaFiltrada) {
            model.addRow(new Object[]{
                    alumno.getNumControl(),
                    alumno.getNombre(),
                    alumno.getPrimerAp(),
                    alumno.getSegundoAp(),
                    alumno.getSemestre(),
                    alumno.getCarrera()
            });
        }
    }
}


public class VentanaInicio extends JFrame {

    public static void main (String[] args){

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GUI();

            }
        });

        String numC,nom, primerAP, segundoAP;
        int edad, Sem;
        String Carrera;

        Scanner ins = new Scanner(System.in);
        System.out.println("opcion");
        String op = ins.next();
        if (op.equals("1")){    // agregar
            numC = ins.next();
            nom = ins.next();
            primerAP = ins.next();
            segundoAP = ins.next();
            Sem = ins.nextInt();
            Carrera = ins.next();
            Alumno al = new Alumno(numC, nom, primerAP, segundoAP,Sem,Carrera);
            AlumnoDAO daos = new AlumnoDAO();
                    daos.agregarAlumno(al);
        }
        else if (op.equals("2")){   // buscar
            AlumnoDAO daos = new AlumnoDAO();
            String numCl = ins.next();

            Alumno al = daos.ObtenerAlumno(numCl);
            if (al == null){
                System.out.println("Alumno no encontrado");
            }else {
                System.out.println(al.getCarrera());
            }

        }
        else if (op.equals("3")){
            AlumnoDAO daos = new AlumnoDAO();
            String nC = ins.next();
            daos.eliminarAlumno(nC);
            System.out.println("Alumno eliminado");
        }
    }
}