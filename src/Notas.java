import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notas extends JFrame {
    private JButton insertarDatosButton;
    private JPanel panel1;
    private JButton buscarRefistrosButton;

    public Notas(){
        setTitle("Calificaciones");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);

        insertarDatosButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Ingreso ventana = new Ingreso();
                ventana.setVisible(true);
                dispose();
            }
        });


        buscarRefistrosButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Buscar ventana = new Buscar();
                ventana.setVisible(true);
                dispose();
            }
        });
    }
}
