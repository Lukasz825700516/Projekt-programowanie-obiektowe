package obiektowka;

/**
 * @startuml ../../../../../sprawozdanie/uml/SimulationAgent.tex
 * interface SimulationAgent {
 * 	+ void simulate(final double, final Simulation)
 * }
 * @enduml
 * */
public interface SimulationAgent {
	// Simulate what happend to entity since last simulation step.
	// @param deltaTime - Time elapsed since last simulation step.
	public void simulate(final double deltaTime, final Simulation simulation);
}
