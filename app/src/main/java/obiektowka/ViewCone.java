package obiektowka;

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
public class ViewCone {
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
}
