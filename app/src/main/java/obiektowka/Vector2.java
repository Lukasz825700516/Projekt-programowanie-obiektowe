package obiektowka;

/**
 * @startuml ../../../../../sprawozdanie/uml/Vector2.tex
 * class Vector2 {
 * 	+ double x
 * 	+ double y
 * 	--
 * 	+ double length()
 * 	+ {static} Vector2 difference(Vector2, Vector2)
 * 	+ {static} Vector2 multiply(Vector2, Vector2)
 * }
 * @enduml
 * */
public class Vector2 {
	double x;
	double y;

	public double length() {
		// TODO
		return 0;
	}
	static public Vector2 difference(Vector2 a, Vector2 b) {
		// TODO: create new vector, calculate division in it, and return
		return null;
	}
	static public Vector2 multiply(Vector2 other, double a) {
		// TODO: create new vector, multiply it, and retun
		return null;
	}
}
