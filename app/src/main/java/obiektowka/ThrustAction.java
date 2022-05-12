package obiektowka;

public class ThrustAction extends Action {
	public final double value;
	public ThrustAction(final double value) {
		super(ActionType.ThrustVelocityChange);
		this.value = value;
	}
}

