package obiektowka;

/**
 * @startuml
 * !include Vector2.java
 * abstract class PhysicsBody implements SimulationAgent {
 * 	~ Vector2 position
 * 	~ Vector2 velocity
 * 	~ double mass
 * }
 * PhysicsBody *-left- Vector2
 * @enduml
 * */
public abstract class PhysicsBody implements SimulationAgent {
	protected Vector2 position = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected double mass = 100;

	@Override
	public void simulate(final double deltaTime, final Simulation s) {
		this.position.x += this.velocity.x * deltaTime;
		this.position.y += this.velocity.y * deltaTime;
	}
}
