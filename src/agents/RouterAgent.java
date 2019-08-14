package agents;

import ontology.*;

import java.util.ArrayList;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class RouterAgent extends Agent {
	public int id;
	public ArrayList<Trip> routes;

	protected void setup() {
<<<<<<< HEAD
		this.routes.add(new Trip());
		addBehaviour(new ListeningBehaviour(this));

=======
		addBehaviour(new CyclicBehaviour(this) {
			 public void action() {
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
>>>>>>> c17b6e0fb5137b3311d872892faf7504e34c1fc4
	}

	public void assignRoute() {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("planner", AID.ISLOCALNAME));
		msg.setContent("1");
		send(msg);
	}
}

@SuppressWarnings("serial")
class ListeningBehaviour extends CyclicBehaviour {
	private RouterAgent myAgent;

	public ListeningBehaviour(RouterAgent agent) {
		this.myAgent = agent;
	}

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			System.out.println(
					" - " + myAgent.getLocalName() + " <- " + msg.getContent() + " from " + msg.getSender().getName());

			if (msg.getContent().equals("Pedir ruta")) {
				myAgent.assignRoute();
			}
			block();
		}

	}

}
