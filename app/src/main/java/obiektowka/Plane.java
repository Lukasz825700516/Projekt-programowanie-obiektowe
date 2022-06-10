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
	private double angle = 0;


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
		switch (action.type) {
			case ThrustVelocityChange:
				ThrustAction act = (ThrustAction)action;
				var angle = Vector2.normal_angle(this.angle);
				var thrust = Vector2.multiply(angle, act.value);
				this.velocity = Vector2.add(this.velocity, thrust);
				break;
			case ThrustAngleChange:
				break;
			case Shoot:
				break;
			case FireRocket: 
				break;
			case None: 
				break;
		}

		super.simulate(deltaTime, simulation);
	}
}
