package obiektowka;

import java.io.BufferedWriter;
import java.io.IOException;

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

	public double angle = 0;
	public double shootCooldown = 0;
	public final double shootDelay = 0.5;


	public Plane(final Engine engine, final SteeringMechanism steeringMechanism, final Pilot pilot, final ViewCone viewCone) {
		this.engine = engine;
		this.steeringMechanism = steeringMechanism;
		this.pilot = pilot;
		this.viewCone = viewCone;

		this.velocity = Vector2.normal_angle(this.angle);
	}

	@Override
	public void simulate(final double deltaTime, final Simulation simulation) {
		final var action = this.pilot.takeAction(this, simulation);
		// TODO: Do something for each action
		switch (action.type) {
			case ThrustVelocityChange: {
				ThrustAction act = (ThrustAction)action;
				var angle = Vector2.normal_angle(this.angle);
				var thrust = Vector2.multiply(angle, -act.value * deltaTime);
				this.velocity = Vector2.add(this.velocity, thrust);
			} break;
			case ThrustAngleChange: {
				ChangeThrustAngleAction act = (ChangeThrustAngleAction)action;
				this.angle += act.value * deltaTime;
			} break;
			case Shoot: {
				this.shootCooldown += this.shootDelay;
			} break;
			case FireRocket: 
				break;
			case None: 
				break;
		}

		this.shootCooldown -= deltaTime;

		var angle = Vector2.normal_angle(this.angle);
		var thrust = Vector2.multiply(angle, -this.engine.maxAcceleration * deltaTime);
		this.velocity = Vector2.add(this.velocity, thrust);

		super.simulate(deltaTime, simulation);
	}

	@Override
	public void write(BufferedWriter writer) throws IOException {
		super.write(writer);
		writer.write(this.angle + "\n");
		writer.write(this.shootCooldown + "\n");
		writer.write(this.shootDelay + "\n");
		writer.write(this.engine.id + "\n");
		writer.write(this.steeringMechanism.id + "\n");
		writer.write(this.pilot.id + "\n");
		writer.write(this.viewCone.id + "\n");
	}
}
