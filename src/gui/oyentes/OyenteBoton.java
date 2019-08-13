package gui.oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OyenteBoton implements ActionListener {
	
	int selected;
	
	public OyenteBoton(int selected) {
		this.selected = selected;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if(e.getSource() instanceof JButton) {
			if("Reservar viaje".equals(actionCommand)) {
				System.out.print("Holi");
			}
		}
	}

}
