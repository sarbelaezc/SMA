package ontology;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Create
* @author ontology bean generator
* @version 2019/08/14, 03:15:02
*/
public class Create implements AgentAction {

   /**
* Protege name: trip
   */
   private Trip trip;
   public void setTrip(Trip value) { 
    this.trip=value;
   }
   public Trip getTrip() {
     return this.trip;
   }

}
