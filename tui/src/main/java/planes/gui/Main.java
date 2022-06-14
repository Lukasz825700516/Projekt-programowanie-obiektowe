package planes.gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

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
		final Simulation sim;



		final var saveFile = new File("init");
		if (saveFile.exists() && saveFile.canRead()) {
			try {
				final var s = new Scanner(saveFile);
				sim = Simulation.load(s);
			} catch (Exception e) {
				System.err.println(e);
				return;
			}
		} else {
			sim = new Simulation(null);

			final var engine = Engine.create(10, 1);
			final var steeringMechanism = SteeringMechanism.create(30, Math.PI / 2 / 10);
			final var pilot = new RandomPilot();
			final var viewCone = ViewCone.create(100, Math.PI / 2 / 10);

			sim.engines.add(engine);
			sim.mechanisms.add(steeringMechanism);
			sim.pilots.add(pilot);
			sim.viewCones.add(viewCone);

			var rand = new Random();
			for (var i = 0; i < 30; i++) {
				var plane = new Plane(engine, steeringMechanism, pilot, viewCone);
				plane.position.x = (rand.nextFloat() - 0.5) * 80;
				plane.position.y = (rand.nextFloat() - 0.5) * 20;
				sim.planes.add(plane);
			}
		}


		var simulationStart = System.nanoTime();
		double time = 0;
		double save_delay = 10;
		double save_cooldown = save_delay;
		while (!sim.done()) {
			final var simulationStop = System.nanoTime();

			final double simulationDuration = simulationStop - simulationStart;
			var timeDelta = simulationDuration / 10000000;

			sim.simulate(timeDelta);

			time += timeDelta;


			final var renderInterval = 0.25;

			if (time > renderInterval) {
				time -= renderInterval;
				System.out.println("Simulation took " + timeDelta * 100000000 + "ns");
				display(sim, 80, 20, -40, -10);
				// log(sim);
			}

			save_cooldown -= timeDelta;
			if (save_cooldown < 0) {
				save_cooldown += save_delay;
				var file = new File("save");
				try {
					var writer = new BufferedWriter(new FileWriter(file));

					sim.write(writer);

					writer.close();

				} catch(Exception e) {
					System.err.println(e);
					return;
				}
			}

			simulationStart = System.nanoTime();
		}
	}
}
