package planes.gui;

import obiektowka.Engine;
import obiektowka.Plane;
import obiektowka.RandomPilot;
import obiektowka.Simulation;
import obiektowka.SteeringMechanism;
import obiektowka.ViewCone;

/**
 * @startuml ../../../../../../sprawozdanie/uml/Gui.tex
 * interface Display {
 * 	+ void display(final Simulation, final Vector2, final Vector2)
 * }
 * @enduml
 * */
public class Main {
	public static void display(final Simulation simulation, final int w, final int h, final int x, final int y) {
		boolean screen[] = new boolean[w * h];

		simulation.forEachPlane((p) -> {
			var javaSucksX = (int)Math.floor(p.position.x);
			var javaSucksY = (int)Math.floor(p.position.y);

			if (javaSucksX - x >= w || javaSucksX - x < 0) return;
			if (javaSucksY - y >= h || javaSucksY - y < 0) return;

			screen[w * (javaSucksY - y) + (javaSucksX - x)] = true;
		});

		for (int javaSucksY = 0; javaSucksY < h; javaSucksY++) {
			for (int javaSucksX = 0; javaSucksX < w; javaSucksX++) {
				System.out.print(screen[w * javaSucksY + javaSucksX] ? 'S' : '.');
			}
			System.out.println();
		}
	}

	public static void log(final Simulation simulation) {
		simulation.forEachPlane((p) -> {
			System.out.println("x: " + p.position.x + ", y: " + p.position.y + ", rt: " + p.angle);
		});
	}

	public static void main(String[] args) {
		// Create new simulation without any space sontrains
		final var sim = new Simulation(null);

		final var engine = Engine.create(10, 1);
		final var steeringMechanism = SteeringMechanism.create(10, Math.PI / 2 / 10);
		final var pilot = new RandomPilot();
		final var viewCone = ViewCone.create(100, Math.PI / 2 / 10);

		for (var i = 0; i < 5; i++) {
			var plane = new Plane(engine, steeringMechanism, pilot, viewCone);
			plane.position.x = 3 * i;
			plane.position.y = 3 * i;
			sim.planes.add(plane);
		}

		var simulationStart = System.nanoTime();
		double time = 0;
		while (true) {
			final var simulationStop = System.nanoTime();

			final double simulationDuration = simulationStop - simulationStart;
			var timeDelta = simulationDuration / 10000000;

			sim.simulate(timeDelta);

			time += timeDelta;


			final var renderInterval = 0.1;
			if (time > renderInterval) {
				time -= renderInterval;
				System.out.println("Simulation took " + timeDelta * 100000000 + "ns");
				display(sim, 80, 20, -40, -10);
				log(sim);
			}

			simulationStart = System.nanoTime();
		}
	}
}
