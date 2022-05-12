package obiektowka;

// Structure storing Plane movement data
//
/**
 * @startuml ../../../../../sprawozdanie/uml/Engine.tex
 * class Engine{
 * 	+ final double maxSpeed
 * 	+ final double maxAcceleration
 * 	--
 * 	- Engine(double, double)
 * 	--
 * 	+ {static} Engine create(double, double) throws
 * 	--
 * 	+ Vector2 calculateVelocity(double, double, PhysicsBody)
 * }
 * @enduml
 * */
public class Engine {
	public final double maxSpeed;
	public final double maxAcceleration;

	private Engine(double maxSpeed, double maxAcceleration) {
		this.maxSpeed = maxSpeed;
		this.maxAcceleration = maxAcceleration;
	}

	public Vector2 calculateVelocity(double thrustVelocity, double thrustAngle, PhysicsBody body) {
		// TODO: calculate new PhysicsBody velocity based on
		// its mass, and acceleration
		return null;
	}

	static public Engine create(double maxSpeed, double maxAcceleration) throws IllegalArgumentException {
		if (maxSpeed < 0) throw new IllegalArgumentException();
		if (maxAcceleration < 0) throw new IllegalArgumentException();

		return new Engine(maxSpeed, maxAcceleration);
	}
}
