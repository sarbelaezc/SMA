package agents;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
<<<<<<< HEAD
=======

import gui.UserGUI;

>>>>>>> c17b6e0fb5137b3311d872892faf7504e34c1fc4
@SuppressWarnings("serial")
public class UserAgent extends Agent {
	
	
	
	protected void setup() {
<<<<<<< HEAD
		addBehaviour(new CyclicBehaviour(this) 
        {
			 public void action() 
             {
=======
		
		UserGUI userGUI = new UserGUI();
		userGUI.setAgent(this);
		userGUI.setVisible(true);
		
		//this.bookTrip();
		
		addBehaviour(new CyclicBehaviour(this) {
			 public void action() {
>>>>>>> c17b6e0fb5137b3311d872892faf7504e34c1fc4
                ACLMessage msg = receive();
                if (msg!=null) {
                    System.out.println( " - " +
                       myAgent.getLocalName() + " <- " +
                       msg.getContent() + " from " + msg.getSender().getName());

                    block();                    	
                 }
             }
        });
	}
	
	public void bookTrip() {
		System.out.println( "Reservando viaje");
		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
		msg.addReceiver( new AID( "planner", AID.ISLOCALNAME ) );
	    msg.setContent("Reservar viaje" );
	    send(msg);	
	}
}
