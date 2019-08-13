package agents;
import java.util.ArrayList;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
public class PlannerAgent extends Agent {
	
	public ArrayList<Trip> tripsList;
	protected void setup() {
		// Setup trips 
		Trip trip1 = new Trip(1,1,30);
		Trip trip2 = new Trip(2,1,0);
		ArrayList<Trip> trips = new ArrayList();
		trips.add(trip1);
		trips.add(trip2);
		addBehaviour(new CyclicBehaviour(this) 
        {
			 public void action() 
             {
                ACLMessage msg = receive();
                if (msg!=null) {
                    System.out.println( " - " +
                       myAgent.getLocalName() + " <- " +
                       msg.getContent() + " from " + msg.getSender());
                    
                    if(msg.getContent().equals("Reservar viaje")) {
                    	bookSeat(1,trips);	
                    }
                    
                    if(msg.getSender().getLocalName().equals("router") ) {
                    	createTrips(msg.getContent());	
                    }
                    
                    block();
                 }
                
             }
        });
		
    }
	
	public boolean bookSeat(int tripId, ArrayList<Trip> trips ) {
		for(int i = 0; i <= trips.size(); i++) {
			Trip trip = trips.get(i);
			if(trip.id == tripId && trip.capacity -1 >= 0) {
				trip.capacity = trip.capacity - 1;
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
		msg.addReceiver( new AID( "user", AID.ISLOCALNAME ) );
	    msg.setContent("Pedir ruta" );
	    send(msg);	
	}
	
	public void createTrips(String routeId) {
		int route = Integer.parseInt(routeId);
		Trip trip1 = new Trip(3,route,15);
		Trip trip2 = new Trip(4,route,20);
		ArrayList<Trip> trips = new ArrayList();
		trips.add(trip1);
		trips.add(trip2);
		this.tripsList = trips;
		
	}

	
	
	
}

