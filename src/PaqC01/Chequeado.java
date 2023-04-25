//Javier Yago Giménez
package PaqC01;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaqC01.*;

public class Chequeado extends JFrame{
    private JTextArea textArea1;
    private JPanel panel1;
    public Chequeado(Puerto c){
        setContentPane(panel1);
        setTitle("Welcome");
        setSize(800, 300);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        textArea1.setText(String.valueOf(c.toStringPuerto(12, 1)));

            }
        }


