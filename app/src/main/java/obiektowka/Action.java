package obiektowka;

/**
 * @startuml ../../../../../sprawozdanie/uml/Action.tex
 * !include ActionType.java
 * abstract class Action {
 * 	+ final ActionType type;
 * 	--
 * 	+ Action(ActionType)
 * }
 * Action *-- ActionType
 * @enduml
 * */
public abstract class Action {
	public final ActionType type;

	public Action(ActionType type) {
		this.type = type;
	}
}
