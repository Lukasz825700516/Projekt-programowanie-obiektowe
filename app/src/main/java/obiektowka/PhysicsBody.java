package obiektowka;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @startuml ../../../../../sprawozdanie/uml/PhysicsBody.tex
 * !include Vector2.java
 * abstract class PhysicsBody implements SimulationAgent {
 * 	+ Vector2 position
 * 	+ Vector2 velocity
 * 	+ double mass
 * }
 * PhysicsBody *-left- Vector2
 * @enduml
 * */
public abstract class PhysicsBody implements SimulationAgent, Writer {
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public double mass = 100;

	@Override
	public void simulate(final double deltaTime, final Simulation s) {
		this.position.x += this.velocity.x * deltaTime;
		this.position.y += this.velocity.y * deltaTime;
	}

	@Override
	public void write(BufferedWriter writer) throws IOException {
		this.position.write(writer);
		this.velocity.write(writer);
		writer.write(mass + "\n");
	}
}
