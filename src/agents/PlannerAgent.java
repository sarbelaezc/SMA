package agents;
import java.util.ArrayList;
import ontology.*;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class PlannerAgent extends Agent {
	public ArrayList<Trip> routes;
	protected void setup() {
		askRoute();
		addBehaviour(new CyclicBehaviour(this) {
			
			 public void action() {	
			 	Trip trip1 = new Trip();
				Trip trip2 = new Trip();
				ArrayList<Trip> trips2 = new ArrayList<Trip>();
				trips2.add(trip1);
				trips2.add(trip2);
				
                ACLMessage msg = receive();
                if (msg!=null) {
                    System.out.println( " - " +
                    					myAgent.getLocalName() + " <- " +
                    					msg.getContent() + " from " + msg.getSender().getName());
                    
                    if(msg.getContent().equals("Reservar viaje")) {
                    	bookSeat(1,trips2);	
                    }
                    
                    if(msg.getSender().getLocalName().equals("router") ) {
                    	setRoutes(1, trips2, msg.getContent());	
                    }
                    // block();
                 }
             }
        });
		
    }
	
	@SuppressWarnings("unused")
	public boolean bookSeat(int tripId, ArrayList<Trip> trips) {
		for(int i = 0; i <= trips.size(); i++) {
			Trip trip = trips.get(i);
			if(trip.getId() == tripId && trip.getCapacity() -1 >= 0) {
				trip.setCapacity(trip.getCapacity() - 1);
				ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
				msg.addReceiver( new AID( "user", AID.ISLOCALNAME ) );
			    msg.setContent("Reserva completa" );
			    send(msg);	
				return true;
			}
			else {
				ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
				msg.addReceiver( new AID( "user", AID.ISLOCALNAME ) );
			    msg.setContent("Viaje lleno" );
			    send(msg);	
				return false;
				}
		}
		return false;
		
	}
	
	public void askRoute(){
		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
		msg.addReceiver( new AID( "router", AID.ISLOCALNAME ) );
	    msg.setContent("Pedir ruta" );
	    send(msg);	
	}
	
	public ArrayList<Trip> setRoutes(int tripId, ArrayList<Trip> trips, String routeId) {
		return trips;
	}

}

