package obiektowka;

// The AI of Plane
/**
 * @startuml
 * !include Action.java
 * interface Pilot {
 * 	+ void prepare(Plane, Simulation)
 * 	+ Action takeAction()
 * }
 * @enduml
 * */
public interface Pilot {
	public void prepare(Plane self, Simulation simulation);
	public Action takeAction();
}
