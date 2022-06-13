package obiektowka;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;


/**
 * @startuml ../../../../../sprawozdanie/uml/Simulation.tex
 * !include SpaceConstrain.java
 * !include SimulationAgent.java
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

public class Simulation implements Writer {
	public final ArrayList<Plane> planes = new ArrayList<Plane>();
	public final ArrayList<SimulationAgent> other = new ArrayList<SimulationAgent>();

	public final SpaceConstrain spaceConstrain;

	public void simulate(final double deltaTime) {
		for (final var agent : planes) {
			agent.simulate(deltaTime, this);
		}
		for (final var agent : other) {
			agent.simulate(deltaTime, this);
		}
	}

	public Simulation(final SpaceConstrain spaceConstrain) {
		this.spaceConstrain = spaceConstrain;
	}

	public void forEachPlane(Consumer<Plane> consumer) {
		this.planes.forEach(consumer);
	}

	@Override
	public void write(BufferedWriter writer) throws IOException {
		for (var p : this.planes) {
			p.write(writer);
			System.out.println("WROTE SOMETHING!");
		}
	}
}
