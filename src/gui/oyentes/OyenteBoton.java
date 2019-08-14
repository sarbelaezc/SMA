package gui.oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import agents.UserAgent;
import ontology.Trip;

public class OyenteBoton implements ActionListener {
	
	private int selected;
	UserAgent userAgent;
	
	public OyenteBoton() {
		super();
	}

	public OyenteBoton(int selected) {
		this.setSelected(selected);
	}
	
	public OyenteBoton(UserAgent userAgent2) {
		// TODO Auto-generated constructor stub
		this.userAgent = userAgent2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if(e.getSource() instanceof JButton) {
			if("Reservar viaje".equals(actionCommand)) {
				System.out.println(this.getSelected());
				this.userAgent.bookTrip((Trip) userAgent.getTrips().get(selected));
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
