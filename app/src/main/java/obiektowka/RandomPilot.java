package obiektowka;

import java.util.Random;

/**
 * !include Pilot.java
 * class RandomPilot implements Pilot {
 * }
 * */
public class RandomPilot implements Pilot {
	Random random = new Random();

	@Override
	public Action takeAction(final Plane self, final Simulation simulation) {
		// TODO Auto-generated method stub
		var c = this.random.nextInt() % 2;

		final Plane target_plane[] = {null};
		// TODO: Maybe instead of forEachPlane, reduce search range using some quadtree
		simulation.forEachPlane((p) -> {
			if (p != self) {
				if (target_plane[0] == null) {
					// TODO: select plan, if visible
				} else {
					// TODO: if better target than target_plane, select it
				}
			}
		});

		final var plane = target_plane[0];
		if (plane != null) {
			final var offset = Vector2.difference(self.position, plane.position);
			final double absoluteAngle = 0; // angle between self.velocity and offset

			final var absoluteAcceptableShootingAngle = 0.1;
			if (absoluteAngle < absoluteAcceptableShootingAngle) {
				return new ShootAction();
			} else {
				// return new ChangeThrustAngleAction(Math.clam);
			}
		}

		switch(c) {
			case 0: return new NoneAction();
			case 1: return new ThrustAction(self.engine.maxAcceleration);
			default: return new NoneAction();
		}
	}

}
