package PaqC01;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DiseñoContenedores extends JFrame {
    private JLabel NumId;
    private JTextField NumIdtext;
    private JLabel Peso;
    private JTextField Pesotext;
    private JLabel Desc;
    private JTextArea Desctext;
    private JLabel Emp_rem;
    private JLabel Emp_rec;
    private JTextField Emp_rectext;
    private JLabel Pais_proc;
    private JComboBox Pais_procbox;
    private JLabel Prior;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox Insp_Aduanas;
    private JPanel mainPanel;
    private JTextField Emp_remtext;
    private JLabel Ops;
    private JButton Ap_button;
    private JButton Desap_button;
    private JTextField numCol_text;
    private JButton ContData_button;
    private JTextField ID_text;
    private JButton Cuantos_button;
    private JComboBox cuantosPais_box;
    private JTextField Cant_text;
    private JPanel auxPanel;
    private JLabel Estado;
    private JTextArea Estad_text;
    private JLabel Logo;
    private JLabel Mensajes;
    private JLabel hub_Texto;
    private JRadioButton a1Hub;
    private JRadioButton a2Hub;
    private JRadioButton a3Hub;

    private int hubMostrar = 0;
    Puerto p1;


    public DiseñoContenedores() {
        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(1500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Hub h1 = new Hub();
        Hub h2 = new Hub();
        Hub h3 = new Hub();

        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        //Lectura de objetos
        try {
            fis = new FileInputStream("puerto.dat");
            entrada = new ObjectInputStream(fis);
            p1 = (Puerto) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            p1 = new Puerto();
            p1.setPuerto(new Hub[]{h1, h2, h3});
        }

        Estad_text.setText(p1.toStringHUB(hubMostrar));

        NumIdtext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Introduzca el número de identificación.");
                try {
                    int dato = Integer.parseInt(NumIdtext.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El dato no es un entero, vuelve a teclearlo.");
                }
            }
        });
        Pesotext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Introduzca el peso en toneladas.");
                try {
                    int dato = Integer.parseInt(Pesotext.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"El dato no es un entero, vuelve a teclearlo.");
                }
            }
        });
        Desctext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Complete la descripción del contenido.");
                //En este apartado no imponemos restricción
            }
        });
        Emp_remtext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Escriba el nombre de la empresa remitente.");
                try {
                    Integer.toString(Integer.parseInt(Emp_remtext.getText()));
                    JOptionPane.showMessageDialog(null,"El dato no es correcto (debe ser String), vuelve a teclearlo.");
                } catch(NumberFormatException ex) {
                }
            }
        });
        Emp_rectext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Escriba el nombre de la empresa receptora.");
                try {
                    Integer.toString(Integer.parseInt(Emp_rectext.getText()));
                    JOptionPane.showMessageDialog(null,"El dato no es correcto (debe ser String), vuelve a teclearlo.");
                } catch(NumberFormatException ex) {
                }
            }
        });
        Ap_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Contenedor apilado.");
                int prioridad = 0;
                if(a1RadioButton.isSelected()) {
                    prioridad = 1;
                } else if(a2RadioButton.isSelected()) {
                    prioridad = 2;
                } else if(a3RadioButton.isSelected()) {
                    prioridad = 3;
                }
                if(p1.apilaContenedor(hubMostrar, new Contenedor(Integer.parseInt(NumIdtext.getText()), Integer.parseInt(Pesotext.getText()), (String) Pais_procbox.getSelectedItem(), Insp_Aduanas.isSelected(), prioridad, Desctext.getText(), Emp_remtext.getText(), Emp_rectext.getText()))) {
                    JOptionPane.showMessageDialog(null, "El contenedor se ha apilado.");
                } else JOptionPane.showMessageDialog(null, "No hay espacio para ese contenedor en este hub.");
                Estad_text.setText(p1.toStringHUB(hubMostrar));

                FileOutputStream fos = null;
                ObjectOutputStream salida = null;
                //Escritura de objetos
                try {
                    fos = new FileOutputStream("puerto.dat");
                    salida = new ObjectOutputStream(fos);
                    salida.writeObject(p1);
                } catch (IOException w) {
                    w.printStackTrace();
                } finally {
                    try {
                        if(fos != null) fos.close();
                        if(salida != null) salida.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        Desap_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Contenedor desapilado.");
                p1.desapilaContenedor(hubMostrar, Integer.parseInt(numCol_text.getText()));
                Estad_text.setText(p1.toStringHUB(hubMostrar));

                FileOutputStream fos = null;
                ObjectOutputStream salida = null;
                //Escritura de objetos
                try {
                    fos = new FileOutputStream("puerto.dat");
                    salida = new ObjectOutputStream(fos);
                    salida.writeObject(p1);
                } catch (IOException w) {
                    w.printStackTrace();
                } finally {
                    try {
                        if(fos != null) fos.close();
                        if(salida != null) salida.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        numCol_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Introduzca el número de columna (0 a 11).");
                try {
                    int dato = Integer.parseInt(numCol_text.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"El dato no es un entero, vuelve a teclearlo.");
                }
            }
        });
        ContData_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Mostrando datos del contenedor.");
                DiseñoContenedoresP2 newframe = new DiseñoContenedoresP2(p1.mostrarDatos(hubMostrar, Integer.parseInt(ID_text.getText())));
                newframe.setVisible(true);
            }
        });
        ID_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Introduzca el ID del contenedor");
                try {
                    int dato = Integer.parseInt(ID_text.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"El dato no es válido para la ID.");
                }
            }
        });
        Cuantos_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Cantidad registrada.");
                Cant_text.setText(String.valueOf(p1.contenedoresPorPais((String) cuantosPais_box.getSelectedItem())));
            }
        });
        cuantosPais_box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Contenedores del país.");
            }
        });
        Cant_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Número de contenedores procedentes del país indicado.");
            }
        });
        Pais_procbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("País de procedencia registrado.");
            }
        });
        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Prioridad anotada.");
                a2RadioButton.setSelected(false);
                a3RadioButton.setSelected(false);
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Prioridad anotada.");
                a1RadioButton.setSelected(false);
                a3RadioButton.setSelected(false);
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Prioridad anotada.");
                a1RadioButton.setSelected(false);
                a2RadioButton.setSelected(false);
            }
        });
        Insp_Aduanas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Inspección confirmada.");
            }
        });
        Estad_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Plano del hub.");
            }
        });
        a1Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Hub seleccionado.");
                a2Hub.setSelected(false);
                a3Hub.setSelected(false);
                hubMostrar = 0;
                Estad_text.setText(p1.toStringHUB(hubMostrar));
            }
        });
        a2Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Hub seleccionado.");
                a1Hub.setSelected(false);
                a3Hub.setSelected(false);
                hubMostrar = 1;
                Estad_text.setText(p1.toStringHUB(hubMostrar));
            }
        });
        a3Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Hub seleccionado.");
                a1Hub.setSelected(false);
                a2Hub.setSelected(false);
                hubMostrar = 2;
                Estad_text.setText(p1.toStringHUB(hubMostrar));
            }
        });
    }

    public DiseñoContenedores(JLabel logo) throws HeadlessException {
        Logo = logo;
        Logo.setSize(150, 128);
    }
}
