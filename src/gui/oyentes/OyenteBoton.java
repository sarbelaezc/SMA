package gui.oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OyenteBoton implements ActionListener {
	
	private int selected;
	
	public OyenteBoton() {
		super();
	}

	public OyenteBoton(int selected) {
		this.setSelected(selected);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if(e.getSource() instanceof JButton) {
			if("Reservar viaje".equals(actionCommand)) {
				System.out.println("Holi");
				System.out.println(this.getSelected());
			}
		}
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

}
