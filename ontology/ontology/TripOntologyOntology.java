// file: TripOntologyOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package ontology;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: TripOntologyOntology.java
 * @author ontology bean generator
 * @version 2019/08/14, 18:52:46
 */
public class TripOntologyOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "TripOntology";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new TripOntologyOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String TRIPFULL="TripFull";
    public static final String SENDTRIPS_TRIPS="trips";
    public static final String SENDTRIPS="SendTrips";
    public static final String RESERVE_CHOOSEDTRIP="choosedTrip";
    public static final String RESERVE_SEAT="seat";
    public static final String RESERVE="Reserve";
    public static final String REQUESTROUTES_TRIPS="trips";
    public static final String REQUESTROUTES="RequestRoutes";
    public static final String RESERVECOMPLETED_TRIPS="trips";
    public static final String RESERVECOMPLETED="ReserveCompleted";
    public static final String REQUESTTRIPS="RequestTrips";
    public static final String TRIP_ID="id";
    public static final String TRIP_DEPARTURETIME="departureTime";
    public static final String TRIP_ROUTE="route";
    public static final String TRIP_SEATS="seats";
    public static final String TRIP_CAPACITY="capacity";
    public static final String TRIP="Trip";
    public static final String SEAT_OCUPANT="ocupant";
    public static final String SEAT="Seat";

  /**
   * Constructor
  */
  private TripOntologyOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema seatSchema = new ConceptSchema(SEAT);
    add(seatSchema, ontology.Seat.class);
    ConceptSchema tripSchema = new ConceptSchema(TRIP);
    add(tripSchema, ontology.Trip.class);

    // adding AgentAction(s)

    // adding AID(s)

    // adding Predicate(s)
    PredicateSchema requestTripsSchema = new PredicateSchema(REQUESTTRIPS);
    add(requestTripsSchema, ontology.RequestTrips.class);
    PredicateSchema reserveCompletedSchema = new PredicateSchema(RESERVECOMPLETED);
    add(reserveCompletedSchema, ontology.ReserveCompleted.class);
    PredicateSchema requestRoutesSchema = new PredicateSchema(REQUESTROUTES);
    add(requestRoutesSchema, ontology.RequestRoutes.class);
    PredicateSchema reserveSchema = new PredicateSchema(RESERVE);
    add(reserveSchema, ontology.Reserve.class);
    PredicateSchema sendTripsSchema = new PredicateSchema(SENDTRIPS);
    add(sendTripsSchema, ontology.SendTrips.class);
    PredicateSchema tripFullSchema = new PredicateSchema(TRIPFULL);
    add(tripFullSchema, ontology.TripFull.class);


    // adding fields
    seatSchema.add(SEAT_OCUPANT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    tripSchema.add(TRIP_CAPACITY, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
    tripSchema.add(TRIP_SEATS, seatSchema, 0, ObjectSchema.UNLIMITED);
    tripSchema.add(TRIP_ROUTE, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    tripSchema.add(TRIP_DEPARTURETIME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    tripSchema.add(TRIP_ID, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
    reserveCompletedSchema.add(RESERVECOMPLETED_TRIPS, tripSchema, 1, ObjectSchema.UNLIMITED);
    requestRoutesSchema.add(REQUESTROUTES_TRIPS, tripSchema, 1, ObjectSchema.UNLIMITED);
    reserveSchema.add(RESERVE_SEAT, seatSchema, ObjectSchema.MANDATORY);
    reserveSchema.add(RESERVE_CHOOSEDTRIP, tripSchema, ObjectSchema.MANDATORY);
    sendTripsSchema.add(SENDTRIPS_TRIPS, tripSchema, 1, ObjectSchema.UNLIMITED);

    // adding name mappings

    // adding inheritance

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }