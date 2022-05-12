package obiektowka;

import java.util.ArrayList;


/**
 * @startuml doc/Simulation.png
 * !include SpaceConstrain.java
 * class Simulation {
 * 	+ final SimulationAgent[] agents
 * 	+ final SpaceConstrain spaceConstrain
	 --
	+ Simulation(final SpaceConstrain) 
	--
	+ void simulate(final double)
 * }
 * Simulation *-up- SimulationAgent : 1..n
 * Simulation *-up- SpaceConstrain
 * @enduml
 * */
public class Simulation {
	public final ArrayList<SimulationAgent> agents = new ArrayList<SimulationAgent>();
	public final SpaceConstrain spaceConstrain;

	public void simulate(final double deltaTime) {
		for (final var agent : agents) {
			agent.simulate(deltaTime, this);
		}
	}

	public Simulation(final SpaceConstrain spaceConstrain) {
		this.spaceConstrain = spaceConstrain;
	}
}
