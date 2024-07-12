import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ingreso extends JFrame {
    private JTextField nom;
    private JTextField dir;
    private JTextField eda;
    private JTextField tel;
    private JTextField cor;
    private JTextField n1;
    private JTextField n2;
    private JButton button1;
    private JPanel panel1;
    private JTextField cod;
    private JButton Visualizar;
    private JTextArea ResgistrosV;
    private JButton volver;

    public Ingreso (){
        setTitle("Verificacion");
        setSize(500,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);


        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Visualizar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VisualizarRegi();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        volver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Notas ventana = new Notas();
                ventana.setVisible(true);
                dispose();
            }
        });
    }

    public void VisualizarRegi()throws SQLException{
        Connection conecta = connection();
        String query = "SELECT * FROM estudiantes";
        Statement statement = conecta.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        StringBuilder resultText = new StringBuilder();
        while(resultSet.next()){
            resultText.append(resultSet.getString("codigo_matricula")).append("\n");
            resultText.append(resultSet.getString("nombre_apellido")).append("\n");
            resultText.append(resultSet.getString("direccion")).append("\n");
            resultText.append(resultSet.getString("edad")).append("\n");
            resultText.append(resultSet.getString("telefono")).append("\n");
            resultText.append(resultSet.getString("correo")).append("\n");
            resultText.append(resultSet.getString("nota1")).append("\n");
            resultText.append(resultSet.getString("nota2")).append("\n").append("\n").append("\n");
        }
        ResgistrosV.setText(resultText.toString());
        conecta.close();



    }











    public void insertarDatos() throws SQLException {
        //Creo una variable para cada cambio donde gusrdare la informacion que se encentre en la caja de texto
        String codigo=cod.getText();
        String N_completo=nom.getText();
        String direccion=dir.getText();
        String edad=eda.getText();
        String telefono=tel.getText();
        String correo=cor.getText();
        String nota1=n1.getText();
        String nota2=n2.getText();

        //Llamo al metodo conecction (esto se llama SIEMPRE ANTES DE UNA CONSULTA)
        Connection conecta = connection();

        //Realizo la consulta para insertar nuevos datos a los registros
        String sql = "INSERT INTO estudiantes(codigo_matricula,nombre_apellido, direccion, edad, telefono, correo, nota1, nota2) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = conecta.prepareStatement(sql);

        //Establece el primer parametro de la consulta con pass como valor que se ingreso en el
        //JTExtfield convertido a entero
        pstmt.setInt(1,Integer.parseInt(codigo));
        //Lo dejo en String
        pstmt.setString(2,N_completo);
        pstmt.setString(3,direccion);

        pstmt.setInt(4,Integer.parseInt(edad));
        pstmt.setString(5,telefono);

        pstmt.setString(6,correo);
        pstmt.setInt(7,Integer.parseInt(nota1));
        pstmt.setInt(8,Integer.parseInt(nota2));

        //es para que recorra
        int rowsAfeccted =pstmt.executeUpdate();
        if ((rowsAfeccted>0)){
            JOptionPane.showMessageDialog(null,"REGISTRO INSERTADO CORRECTAMENTE");
        }
        pstmt.close();
        conecta.close();
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
