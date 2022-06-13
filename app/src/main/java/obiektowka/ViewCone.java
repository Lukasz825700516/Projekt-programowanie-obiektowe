package obiektowka;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @startuml ../../../../../sprawozdanie/uml/ViewCone.tex
 * class ViewCone {
	+ final double maxViewDistance
	+ final double maxAbsoluteViewConeAngle
	--
	- ViewCone(final double, final double)
	--
	+ {static} ViewCone create(final double, final double) throws
	--
	+ boolean isVisible(PhysicsBody, PhysicsBody)
 * }
 * @enduml
 * */
public class ViewCone extends Resource {
	public final double maxViewDistance;
	public final double maxAbsoluteViewConeAngle;

	private ViewCone(final double maxViewDistance, final double maxAbsoluteViewConeAngle) {
		this.maxViewDistance = maxViewDistance;
		this.maxAbsoluteViewConeAngle = maxAbsoluteViewConeAngle;
	}

	public static ViewCone create(final double maxViewDistance, final double maxAbsoluteViewConeAngle) throws IllegalArgumentException {
		if (maxViewDistance < 0) throw new IllegalArgumentException();
		if (maxAbsoluteViewConeAngle < 0 || maxAbsoluteViewConeAngle > Math.PI) throw new IllegalArgumentException();

		return new ViewCone(maxViewDistance, maxAbsoluteViewConeAngle);
	}

	public boolean isVisible(PhysicsBody self, PhysicsBody other) {
		// TODO
		return false;
	}

	@Override
	public void write(BufferedWriter writer) throws IOException {
		super.write(writer);
		writer.write("view_cone\n");
		writer.write(this.maxViewDistance + "\n");
		writer.write(this.maxAbsoluteViewConeAngle + "\n");
	}
}
