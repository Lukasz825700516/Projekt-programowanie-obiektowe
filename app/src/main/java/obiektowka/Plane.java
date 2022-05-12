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
 * 	- final Engine engine
 * 	- final SteeringMechanism steeringMechanism
 * 	- final Pilot pilot
 * 	- final ViewCone viewCone
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
	final Engine engine;
	final SteeringMechanism steeringMechanism;
	final Pilot pilot;
	final ViewCone viewCone;


	public Plane(final Engine engine, final SteeringMechanism steeringMechanism, final Pilot pilot, final ViewCone viewCone) {
		this.engine = engine;
		this.steeringMechanism = steeringMechanism;
		this.pilot = pilot;
		this.viewCone = viewCone;
	}

	@Override
	public void simulate(final double deltaTime, final Simulation simulation) {
		this.pilot.prepare(this, simulation);

		var action = this.pilot.takeAction();
		while (action.type != ActionType.None) {
			// TODO: Do something for each action

			action = this.pilot.takeAction();
		}
		super.simulate(deltaTime, simulation);
	}
}
