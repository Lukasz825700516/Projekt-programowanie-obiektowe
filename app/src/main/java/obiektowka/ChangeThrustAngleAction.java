package obiektowka;
// Im dłużej piszę w tej javie, tym mam większe mdłości

public class ChangeThrustAngleAction extends Action {
	public double value;

	public ChangeThrustAngleAction(double value) {
		super(ActionType.ThrustAngleChange);

		this.value = value;
	}
}
