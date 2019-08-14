package gui.oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class OyenteMenu implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
        if ("Salir".equals(actionCommand)) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?", "¡Saliendo!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == 0) {
                System.exit(0);
            } else if (resp == 1) {

            }
        } else if ("Info".equals(actionCommand)) {
            JOptionPane.showMessageDialog(null, "Desarrollado por: \n"
            								  + "     Santiago Arbelaez Cataño \n"
            								  + "     Jose Francisco Miguel Diaz Diaz \n"
            								  + "     Juan Esteban Lopez Lopez \n"
            								  + "     Miguel Pinzón Hernández",
            								  "Acerca de nosotros", JOptionPane.INFORMATION_MESSAGE);
        } else if ("Ayuda".equals(actionCommand)) {
            JOptionPane.showMessageDialog(null, "Envia un correo con tus dudas al correo: \n"
            								  + "         → sarbelaezc@unal.edu.co \n"
            								  + "         → jofdiazdi@unal.edu.co \n"
            								  + "         → juaelopezlop@unal.edu.co \n"
            								  + "         → cmpinzonh@unal.edu.co \n"
            								  + " \n"
            								  + " y te responderemos lo antes posible.",
            								  "Ver ayuda", JOptionPane.INFORMATION_MESSAGE);
        }
	}

}
