package obiektowka;

import java.util.List;

// The AI of Plane
/**
 * @startuml ../../../../../sprawozdanie/uml/Pilot.tex
 * !include Action.java
 * interface Pilot {
 * 	+ Action takeAction()
 * }
 * @enduml
 * */
public abstract class Pilot extends Resource {
	public abstract Action takeAction(Plane self, Simulation simulation);
}
