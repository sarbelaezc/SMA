package agents;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;
import jade.util.leap.List;
import ontology.*;

import javax.swing.JOptionPane;

import gui.UserGUI;

@SuppressWarnings({ "serial", "unused" })
public class UserAgent extends Agent {

	private Codec codec = new SLCodec();
	private Ontology ontology = TripOntology.getInstance();
	private List trips = null;
	UserGUI userGUI;

	protected void setup() {
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);

		this.requestTrips();
		MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
				MessageTemplate.MatchOntology(ontology.getName()));
		ACLMessage msg = blockingReceive(mt);
		try {
			if (msg != null) {
				System.out.println(
						" - " + getLocalName() + " <- " + msg.getContent() + " from " + msg.getSender().getName());
				if (msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD) {
					System.out.println("Message not understood");
				} else if (msg.getPerformative() == ACLMessage.INFORM) {
					ContentElement ce = getContentManager().extractContent(msg);
					if (ce instanceof SendTrips) {
						SendTrips st = (SendTrips) ce;
						setTrips(st.getTrips());
					}
				}
			}
		} catch (jade.content.lang.Codec.CodecException ce) {
			System.out.println(ce);
		} catch (jade.content.onto.OntologyException oe) {
			System.out.println(oe);
		}

		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
						MessageTemplate.MatchOntology(ontology.getName()));
				ACLMessage msg = blockingReceive(mt);
				try {
					if (msg != null) {
						System.out.println(" - " + myAgent.getLocalName() + " <- " + msg.getContent() + " from "
								+ msg.getSender().getName());
						if (msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD) {
							System.out.println("Message not understood");
						} else if (msg.getPerformative() == ACLMessage.INFORM) {
							ContentElement ce = getContentManager().extractContent(msg);
							if (ce instanceof SendTrips) {
								SendTrips st = (SendTrips) ce;
								setTrips(st.getTrips());
							}else if(ce instanceof ReserveCompleted) {
								JOptionPane.showMessageDialog(null, "Se reservo el cupo exitosamente.", "Reserva de cupo", JOptionPane.INFORMATION_MESSAGE);
							}else if (ce instanceof TripFull) {
								JOptionPane.showMessageDialog(null, "El viaje está lleno!", "¡Ups!", JOptionPane.ERROR_MESSAGE);
							}
						}

						block();
					}
				} catch (jade.content.lang.Codec.CodecException ce) {
					System.out.println(ce);
				} catch (jade.content.onto.OntologyException oe) {
					System.out.println(oe);
				}

			}
		});
		while (true) {
			if (trips != null) {
				userGUI = new UserGUI(this);
				userGUI.setAgent(this);
				userGUI.setVisible(true);
				break;
			}
		}

	}

	public void setTrips(List trips) {
		this.trips = trips;
	}

	public List getTrips() {
		return this.trips;
	}

	public void bookTrip(Trip choosedTrip) {
		System.out.println("Reservando viaje");
		Reserve reserve = new Reserve();
		reserve.setChoosedTrip(choosedTrip);
		Seat seat = new Seat();
		seat.setOcupant(getAID().getName());
		reserve.setSeat(seat);
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setLanguage(codec.getName());
		msg.setOntology(ontology.getName());
		msg.setSender(getAID());
		msg.addReceiver(new AID("planner", AID.ISLOCALNAME));
		try {
			getContentManager().fillContent(msg, reserve);
		} catch (CodecException | OntologyException e) {
			System.out.println(e);
		}
		send(msg);
	}

	public void requestTrips() {
		RequestTrips rt = new RequestTrips();
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setLanguage(codec.getName());
		msg.setOntology(ontology.getName());
		msg.setSender(getAID());
		msg.addReceiver(new AID("planner", AID.ISLOCALNAME));
		try {
			getContentManager().fillContent(msg, rt);
		} catch (CodecException | OntologyException e) {
			System.out.println(e);
		}
		send(msg);
	}
}
