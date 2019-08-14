package agents;

import ontology.*;



import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;
import jade.util.leap.List;

@SuppressWarnings({"serial"})
public class RouterAgent extends Agent {
	public Codec codec = new SLCodec();
	public Ontology ontology = TripOntology.getInstance();

	protected void setup() {
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		addBehaviour(new ListeningBehaviour(this));
	}

	public List assignRoute(List trips) {
		int route = (int) ((Math.random()*((2-1)+1))+1);
		List ret = new ArrayList();
		for(int i = 0; i<trips.size();i++) {
			Trip trip = (Trip) trips.get(i);
			trip.setRoute(route);
			ret.add(trip);
			route = (int) ((Math.random()*(2-1)+1)+1);
		}
		return ret;
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
		MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchLanguage(myAgent.codec.getName()),
				MessageTemplate.MatchOntology(myAgent.ontology.getName()));
		ACLMessage msg = myAgent.blockingReceive(mt);
		try {
			if (msg != null) {
				System.out.println(
						" - " + myAgent.getLocalName() + " <- " + msg.getContent() + " from " + msg.getSender().getName());
				AID sender = msg.getSender();
				if(msg.getPerformative()==ACLMessage.REQUEST) {
					ContentElement ce ;
					ce = myAgent.getContentManager().extractContent(msg);
					if (ce instanceof RequestRoutes) {
						RequestRoutes rr = (RequestRoutes) ce;
						List trips = myAgent.assignRoute(rr.getTrips());
						SendTrips st = new SendTrips();
						st.setTrips(trips);
						ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
						msg2.setLanguage(myAgent.codec.getName());
						msg2.setOntology(myAgent.ontology.getName());
						msg2.addReceiver(sender);
						msg2.setSender(myAgent.getAID());
						myAgent.getContentManager().fillContent(msg2, st);
						myAgent.send(msg2);
					}
				}
				
			}
			
		}catch (CodecException | OntologyException e) {
			System.out.println(e);
		}
		

	}

}
