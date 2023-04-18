package PaqC01;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaqC01.*;

public class DiseñoContenedoresP2 extends  JFrame {
    private JLabel NumID;
    private JLabel Peso;
    private JLabel Descripcion;
    private JLabel EmpRem;
    private JLabel EmpRec;
    public JTextField NumID_text;
    public JTextField Peso_text;
    public JTextArea Descripcion_text;
    public JTextField EmpRem_text;
    public JTextField EmpRec_text;
    private JLabel Pais;
    private JTextField Pais_text;
    private JLabel Prioridad;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox Inspeccionado;
    private JPanel mainPanel;
    private JLabel Mensajes2;

    public DiseñoContenedoresP2(Contenedor c) {
        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(800, 300);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);

        NumID_text.setText(String.valueOf(c.getNumeroIdentf()));
        Peso_text.setText(String.valueOf(c.getPesoCont()));
        Descripcion_text.setText(c.getDescripcion());
        EmpRem_text.setText(c.getNombreEmpresaEnvia());
        EmpRec_text.setText(c.getNombreEmpresaRecibe());
        Pais_text.setText(c.getPais());
        if(c.getPrioridad() == 1) {
            a1RadioButton.setSelected(true);
        } else if(c.getPrioridad() == 2) {
            a2RadioButton.setSelected(true);
        } else if(c.getPrioridad() == 3) {
            a3RadioButton.setSelected(true);
        }
        if(c.isAduanas()) {
            Inspeccionado.setSelected(true);
        }

        NumID_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes2.setText("Número de identificación de la empresa.");
            }
        });
        Peso_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes2.setText("Peso del contenedor.");
            }
        });
        Descripcion_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes2.setText("Descripción del contenido del contenedor.");
            }
        });
        EmpRem_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes2.setText("Empresaque envía el contenedor.");
            }
        });
        EmpRec_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes2.setText("Empresa que recibe el contenedor.");
            }
        });
        Pais_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes2.setText("País del que proviene el contenedor.");
            }
        });
        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes2.setText("Prioridad del contenedor.");
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes2.setText("Prioridad del contenedor.");
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes2.setText("Prioridad del contenedor.");
            }
        });
        Inspeccionado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes2.setText("Inspeccionado en aduanas (Sí/No).");
            }
        });
    }
}
