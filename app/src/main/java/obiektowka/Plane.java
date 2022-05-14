package obiektowka;

// Agent of simulation
/**
 * @startuml ../../../../../sprawozdanie/uml/Plane.tex
 * !include SimulationAgent.java
 *
 * !include Engine.java
 * !include SteeringMechanism.java
 * !include ViewCone.java
 * !include Pilot.java
 * !include PhysicsBody.java
 *
 * class Plane extends PhysicsBody {
 * 	+ final Engine engine
 * 	+ final SteeringMechanism steeringMechanism
 * 	+ final Pilot pilot
 * 	+ final ViewCone viewCone
 * }
 *
 *
 * Plane o-- Engine
 * Plane o-- SteeringMechanism
 * Plane o-- ViewCone
 * Plane o-- Pilot
 *
 * @enduml
 * */
public class Plane extends PhysicsBody {
	public final Engine engine;
	public final SteeringMechanism steeringMechanism;
	public final Pilot pilot;
	public final ViewCone viewCone;


	public Plane(final Engine engine, final SteeringMechanism steeringMechanism, final Pilot pilot, final ViewCone viewCone) {
		this.engine = engine;
		this.steeringMechanism = steeringMechanism;
		this.pilot = pilot;
		this.viewCone = viewCone;
	}

	@Override
	public void simulate(final double deltaTime, final Simulation simulation) {
		final var action = this.pilot.takeAction(this, simulation);
		// TODO: Do something for each action

		super.simulate(deltaTime, simulation);
	}
}
