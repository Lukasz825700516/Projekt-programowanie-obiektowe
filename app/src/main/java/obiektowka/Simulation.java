package obiektowka;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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

	public final ArrayList<Engine> engines = new ArrayList<Engine>();
	public final ArrayList<SteeringMechanism> mechanisms = new ArrayList<SteeringMechanism>();
	public final ArrayList<Pilot> pilots = new ArrayList<Pilot>();
	public final ArrayList<ViewCone> viewCones = new ArrayList<ViewCone>();

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

	public static Simulation load(Scanner scaner) throws Exception {
		var sim = new Simulation(null);

		var token = scaner.next();
		if (token == "resource") {
			int id = scaner.nextInt();
			token = scaner.next();

			if (token == "engine") {
				var a = scaner.nextDouble();
				var b = scaner.nextDouble();
				var e = Engine.create(a, b);
				if (e == null) throw new Exception();
				sim.engines.add(e);
			}
			if (token == "steering_mechanism") {
				var a = scaner.nextDouble();
				var b = scaner.nextDouble();
				var e = SteeringMechanism.create(a, b);
				if (e == null) throw new Exception();
				sim.mechanisms.add(e);
			}
			if (token == "pilot") {
				var t = scaner.next();
				Pilot p = null;
				if (t == "random_pilot") p = new RandomPilot();
				if (p == null) throw new Exception();
				sim.pilots.add(p);
			}
			if (token == "view_cone") {
				var a = scaner.nextDouble();
				var b = scaner.nextDouble();
				var p = ViewCone.create(a, b);
				if (p == null) throw new Exception();
				sim.viewCones.add(p);
			}
		} else {
			while (scaner.hasNext()) {
				var p = Plane.load(scaner, sim);
				sim.planes.add(p);
			}
		}
		return sim;
	}
}
