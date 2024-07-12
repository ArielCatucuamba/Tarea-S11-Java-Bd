import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Buscar extends  JFrame{
    private JButton buscarR;
    private JButton Volver;
    private JTextField tpbuscar;
    private JPanel panel1;

    public Buscar(){
        setTitle("Bsuqueda de registros");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);


        buscarR.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarINFO();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        Volver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Notas ventana = new Notas();
                ventana.setVisible(true);
                dispose();
            }
        });
    }

    public void buscarINFO() throws SQLException {
        // Obtiene el ID del campo de texto
        int id = Integer.parseInt(tpbuscar.getText());
        String sql = "SELECT * FROM estudiantes WHERE codigo_matricula = ?";

        // Prepara la conexión y la consulta
        try (Connection conecta = connection();
             PreparedStatement pstmt = conecta.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Establece el ID en la consulta

            // Ejecuta la consulta y obtiene el resultado
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Verifica si se encontró el estudiante
                    // Recupera los datos del estudiante
                    String nombre = rs.getString("nombre_apellido");
                    String direccion = rs.getString("direccion");
                    String edad = rs.getString("edad");
                    String telefono = rs.getString("telefono");
                    String correo = rs.getString("correo");
                    String nota1 = rs.getString("nota1");
                    String nota2 = rs.getString("nota2");

                    // Muestra la información del estudiante
                    JOptionPane.showMessageDialog(null, String.format(
                            "| Codigo: %d | Nombre: %s | Direccion: %s | Edad: %s | Telefono: %s | Correo: %s | Nota 1: %s | Nota 2: %s",
                            id, nombre, direccion, edad, telefono, correo, nota1, nota2
                    ));
                } else {
                    // Muestra un mensaje si no se encontró el estudiante
                    JOptionPane.showMessageDialog(null, "No se encontró el estudiante con el código: " + id);
                }
            }
        }
    }

    public Connection connection() throws SQLException {
        //creo 3 variables String (url,user,password) estan variables se llena con los datos de mi bdd
        String url="jdbc:mysql://localhost:3306/curso";
        String user="root";
        String password="123456";

        //es muy importante poner :
        return DriverManager.getConnection(url,user,password);

    }






}
