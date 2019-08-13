package agents;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RouterAgent extends Agent {
	public int id;
	protected void setup() {
		addBehaviour(new CyclicBehaviour(this) 
        {
			 public void action() 
             {
                ACLMessage msg = receive();
                if (msg!=null) {
                    System.out.println( " - " +
                       myAgent.getLocalName() + " <- " +
                       msg.getContent() + " from " + msg.getSender().getName());
                    
                    if(msg.getContent().equals("Pedir ruta")) {
                    	assignRoute();
                    }
                    block();
                 }
                
             }
        });
		
	}

	public void assignRoute() {
		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
		msg.addReceiver( new AID( "planner", AID.ISLOCALNAME ) );
	    msg.setContent("1" );
	    send(msg);
	}
}
