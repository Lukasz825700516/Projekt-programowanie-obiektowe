package obiektowka;

import java.util.Random;

/**
 * !include Pilot.java
 * class RandomPilot implements Pilot {
 * }
 * */
public class RandomPilot implements Pilot {
	Random random = new Random();

	Plane currentSelf;
	Simulation currentSimulation;

	@Override
	public void prepare(Plane self, Simulation simulation) {
		this.currentSelf = self;
		this.currentSimulation = simulation;
	}

	@Override
	public Action takeAction() {
		// TODO Auto-generated method stub
		var c = this.random.nextInt() % 2;
		switch(c) {
			case 0: return new NoneAction();
			case 1: return new ThrustAction(this.currentSelf.engine.maxAcceleration);
			default: return new NoneAction();
		}
	}

}
