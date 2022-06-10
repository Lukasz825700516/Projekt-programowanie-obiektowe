package obiektowka;

/**
 * @startuml ../../../../../sprawozdanie/uml/Vector2.tex
 * class Vector2 {
 * 	+ double x
 * 	+ double y
 * 	--
 * 	+ double square_length()
 * 	+ double length()
 * 	+ {static} Vector2 add(Vector2, Vector2)
 * 	+ {static} double scalar_multiplication(Vector2, Vector2)
 * 	+ {static} Vector2 difference(Vector2, Vector2)
 * 	+ {static} Vector2 multiply(Vector2, Vector2)
 * }
 * @enduml
 * */
public class Vector2 {
	public double x;
	public double y;

	static public Vector2 normal_angle(double angle) {
		var result = new Vector2();

		result.x = Math.cos(angle);
		result.y = Math.sin(angle);

		return result;
	}
	public Vector2 normalized() {
		return Vector2.multiply(this, 1/this.length());
	}
	public double square_length() {
		return x * x + y * y;
	}
	public double length() {
		return Math.sqrt(this.square_length());
	}
	static public Vector2 add(Vector2 a, Vector2 b) {
		Vector2 result = new Vector2();
		result.x = a.x + b.x;
		result.y = a.y + b.y;
		return result;
	}
	static public Vector2 difference(Vector2 a, Vector2 b) {
		Vector2 result = new Vector2();
		result.x = a.x - b.x;
		result.y = a.y - b.y;
		return result;
	}
	static public double scalar_multiplication(Vector2 a, Vector2 b) {
		return a.x * b.x + a.y * b.y;
	}
	static public Vector2 multiply(Vector2 other, double a) {
		Vector2 result = new Vector2();
		result.x = other.x * a;
		result.y = other.y * a;
		return result;
	}
}
