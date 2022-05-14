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
public interface Pilot {
	public Action takeAction(Plane self, Simulation simulation);
}
