package ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Reserve
* @author ontology bean generator
* @version 2019/08/14, 03:15:02
*/
public class Reserve implements AgentAction {

   /**
* Protege name: seat
   */
   private Seat seat;
   public void setSeat(Seat value) { 
    this.seat=value;
   }
   public Seat getSeat() {
     return this.seat;
   }

   /**
* Protege name: choosedTrip
   */
   private Trip choosedTrip;
   public void setChoosedTrip(Trip value) { 
    this.choosedTrip=value;
   }
   public Trip getChoosedTrip() {
     return this.choosedTrip;
   }

}
