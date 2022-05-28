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

	/**
	* 
	* @param thrustVelocity scalar in range of 0 and maxAcceleration
	* @param thrustAngle is relative to current velocity vector
	* @param body
	* @return
	*/
	public Vector2 calculateVelocity(double thrustVelocity, double thrustAngle, PhysicsBody body) {
		var direction = body.velocity.normalized();
		
		direction.x = direction.x * Math.cos(thrustAngle) + direction.y * Math.sin(thrustAngle);
		direction.y = direction.x * Math.sin(thrustAngle) + direction.y * Math.cos(thrustAngle);

		var thrust = Vector2.multiply(direction, thrustVelocity);
		var velocity = Vector2.add(body.velocity, thrust);
		var square_speed = velocity.square_length();

		if (square_speed > this.maxSpeed * this.maxSpeed) {
			velocity = Vector2.multiply(velocity.normalized(), this.maxSpeed);
		}

		return velocity;
	}

	/**
	* 
	* @param maxSpeed scalar greater than 0
	* @param maxAcceleration scalar greater than 0
	* @return
	* @throws IllegalArgumentException
	*/
	static public Engine create(double maxSpeed, double maxAcceleration) throws IllegalArgumentException {
		if (maxSpeed < 0) throw new IllegalArgumentException();
		if (maxAcceleration < 0) throw new IllegalArgumentException();

		return new Engine(maxSpeed, maxAcceleration);
	}
}
