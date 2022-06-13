package obiektowka;

import java.io.BufferedWriter;
import java.io.IOException;

// Structure with info how Plane can manover
/**
 * @startuml ../../../../../sprawozdanie/uml/SteeringMechanism.tex
 * class SteeringMechanism {
 * 	+ final double maxAbsoluteThrustAngle
 * 	+ final double maxAbsoluteThrustAngleChange
 * 	--
 * 	- SteeringMechanism(double, double)
 * 	--
 * 	+ {static} SteeringMechanism create(double, double) throws
 * }
 * @enduml
 * */
public class SteeringMechanism extends Resource {
	// Max angle
	public final double maxAbsoluteThrustAngle;

	// Max speed of angle change
	public final double maxAbsoluteThrustAngleChange;

	private SteeringMechanism(double maxAbsoluteThrustAngle, double maxAbsoluteThrustAngleChange) {
		this.maxAbsoluteThrustAngle = maxAbsoluteThrustAngle;
		this.maxAbsoluteThrustAngleChange = maxAbsoluteThrustAngleChange;
	}

	static public SteeringMechanism create(double maxAbsoluteThrustAngle, double maxAbsoluteThrustAngleChange) throws IllegalArgumentException {
		if (maxAbsoluteThrustAngle < 0) throw new IllegalArgumentException();
		if (maxAbsoluteThrustAngleChange < 0) throw new IllegalArgumentException();

		return new SteeringMechanism(maxAbsoluteThrustAngle, maxAbsoluteThrustAngleChange);
	}

	@Override
	public void write(BufferedWriter writer) throws IOException {
		super.write(writer);
		writer.write("steering_mechanism\n");
		writer.write(this.maxAbsoluteThrustAngle + "\n");
		writer.write(this.maxAbsoluteThrustAngleChange + "\n");
	}
}
