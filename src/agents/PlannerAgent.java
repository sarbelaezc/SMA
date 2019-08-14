package agents;

import ontology.*;
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

@SuppressWarnings("serial")
public class PlannerAgent extends Agent {

	private Codec codec = new SLCodec();
	private Ontology ontology = TripOntology.getInstance();
	private List trips = new ArrayList();
	
	public void setTrips(List trips) {
		this.trips = trips;
	}

	public PlannerAgent() {
		super();
		Trip trip1 = new Trip("9:00 am", 50, 1);
		Trip trip2 = new Trip("10:00 am", 50, 2);
		Trip trip3 = new Trip("11:00 am", 0, 3);
		trips.add(trip1);
		trips.add(trip2);
		trips.add(trip3);
	}

	protected void setup() {
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchLanguage(codec.getName()),
						MessageTemplate.MatchOntology(ontology.getName()));
				ACLMessage msg = blockingReceive(mt);
				try {
					if (msg != null) {
						System.out.println(" - " + myAgent.getLocalName() + " <- " + msg.getContent() + " from "
								+ msg.getSender().getName());
						if (msg.getPerformative() == ACLMessage.REQUEST) {
							ContentElement ce;

							ce = getContentManager().extractContent(msg);
							AID sender = msg.getSender();
							if (ce instanceof RequestTrips) {
								SendTrips st = new SendTrips();
								st.setTrips(trips);
								ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
								msg2.setLanguage(codec.getName());
								msg2.setOntology(ontology.getName());
								msg2.setSender(getAID());
								msg2.addReceiver(sender);
								getContentManager().fillContent(msg2, st);
								send(msg2);
							} else if (ce instanceof Reserve) {
								Reserve reserve = (Reserve) ce;
								Seat seat = reserve.getSeat();
								Trip trip = reserve.getChoosedTrip();
								bookSeat(trip,sender,seat);
							}
						}else if(msg.getPerformative()==ACLMessage.INFORM) {
							ContentElement ce;
							ce=getContentManager().extractContent(msg);
							if(ce instanceof SendTrips) {
								SendTrips st = (SendTrips) ce;
								setTrips(st.getTrips());
							}
						}
					}
				} catch (CodecException | OntologyException e) {
					System.out.println(e);
				}
			}
		});
		askRoute();
	}

	@SuppressWarnings("unused")
	public boolean bookSeat(Trip choosedTrip, AID sender, Seat seat) {
		int tripId = choosedTrip.getId();
		for (int i = 0; i <= trips.size(); i++) {
			Trip trip = (Trip) trips.remove(i);
			if (trip.getId() == tripId && trip.getCapacity() - 1 >= 0) {
				trip.setCapacity(trip.getCapacity() - 1);
				trip.addSeats(seat);
				trips.add(trip);
				ReserveCompleted resComp = new ReserveCompleted();
				resComp.setTrips(trips);
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setLanguage(codec.getName());
				msg.setOntology(ontology.getName());
				msg.setSender(getAID());
				msg.addReceiver(sender);
				try {
					getContentManager().fillContent(msg, resComp);
				}catch (CodecException | OntologyException e) {
					System.out.println(e);
				}
				send(msg);
				return true;
			} else {
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setLanguage(codec.getName());
				msg.setOntology(ontology.getName());
				msg.setSender(getAID());
				msg.addReceiver(sender);
				TripFull tf = new TripFull();
				try {
					getContentManager().fillContent(msg, tf);
				} catch (CodecException | OntologyException e) {
					System.out.println(e);
				}
				send(msg);
				return false;
			}
		}
		return false;
	}

	public void askRoute() {
		RequestRoutes rr = new RequestRoutes();
		rr.setTrips(trips);
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setLanguage(codec.getName());
		msg.setOntology(ontology.getName());
		msg.setSender(getAID());
		msg.addReceiver(new AID("router", AID.ISLOCALNAME));
		try {
			getContentManager().fillContent(msg, rr);
		} catch (CodecException | OntologyException e) {
			System.out.println(e);
		}
		send(msg);
	}
}
