package obiektowka;

/**
 * @startuml
 * class SpaceConstrain {
	+ final boolean wrapOnX
	+ final boolean wrapOnY
	+ final double width
	+ final double height
	--
	+ SpaceConstrain(final boolean, final boolean, final double, final double)
 * }
 * @enduml
 * */
public class SpaceConstrain {
	public final boolean wrapOnX;
	public final boolean wrapOnY;

	public final double width;
	public final double height;

	public SpaceConstrain(final boolean wrapOnX, final boolean wrapOnY, final double width, final double height) {
		this.wrapOnX = wrapOnX;
		this.wrapOnY = wrapOnY;
		this.width = width;
		this.height = height;
	}
}
