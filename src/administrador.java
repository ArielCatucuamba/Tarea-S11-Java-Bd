import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class administrador extends JFrame{
    private JPanel panel1;
    private JButton button1;
    private JTextField usuarioJTF;
    private JTextField PassJTF;

    public administrador(){
        setTitle("Verificacion");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);

        // hago el action listener del boton
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VerificarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //ahora creo mi funcion para verificar la informacion
    public void VerificarDatos() throws SQLException {
        //Llamo al metodo conecction (esto se llama SIEMPRE ANTES DE UNA CONSULTA)
        Connection conecta = connection();

        //Traigo la informacion de mi JTextfield y lo almaceno en una variable llamada correo
        String correo=usuarioJTF.getText();
        //hago lo mismo pero para la contraseña
        String pass =PassJTF.getText();



        //Realizo la consulta SQl la cual la guardo en una variable sql
        //Esta consulta selecciona todos los registros de usuariosadministrador donde el codigo
        // y el correo (los cuales se pondran mas adelante
        // ? significa paramatros que se agregaran mas adelante
        String sql="SELECT * FROM  usuariosadministrador WHERE codigo=? and correo=?";

        //No se en que se usa esto ya que la conexion ya la hicimos
        Connection connection;

        // Usa la conexion que cree arriba y sirve para ejecutar consultas de manera segura y bien
        PreparedStatement pstmt = conecta.prepareStatement(sql);

        //Establece el primer parametro de la consulta con pass como valor que se ingreso en el
        //JTExtfield convertido a entero
        pstmt.setInt(1,Integer.parseInt(pass));

        //establece el segundo parametro de la consulta
        pstmt.setString(2, correo);

        //Ejecuta la consulta SQL que hice anteriormente
        //ResultSet devuelve los resultados de la consulta
        //Recorre todos los registro hasta encontrar uno que cumpla la consulta
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()){
            JOptionPane.showMessageDialog(null,"Informacion ingresada correctamente");
            Notas ventana = new Notas();
            ventana.setVisible(true);
            dispose();
        }else {
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos.");

        }
        rs.close();
        pstmt.close();
        conecta.close();

    }



    //creo un metodo para la conexion a la base de datos
    public Connection connection() throws SQLException {
        //creo 3 variables String (url,user,password) estan variables se llena con los datos de mi bdd
        String url="jdbc:mysql://localhost:3306/curso";
        String user="root";
        String password="123456";

        //es muy importante poner :
        return DriverManager.getConnection(url,user,password);

    }



















}
