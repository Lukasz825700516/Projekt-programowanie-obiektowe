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
		final double square_target_plane_angle[] = {Double.POSITIVE_INFINITY};
		// TODO: Maybe instead of forEachPlane, reduce search range using some quadtree
		simulation.forEachPlane((p) -> {
		if (p != self) {
			var offset = Vector2.difference(p.position, self.position);
			var square_distance = offset.square_length();
			if (!(square_distance < self.viewCone.maxViewDistance * self.viewCone.maxViewDistance)) return;
			var view_direction = self.velocity;

			var cos = Vector2.scalar_multiplication(offset.normalized(), view_direction.normalized());
			var angle = Math.acos(cos);
			var square_angle = angle * angle;
			if (square_angle < self.viewCone.maxAbsoluteViewConeAngle * self.viewCone.maxAbsoluteViewConeAngle) {
				if (target_plane[0] == null
				|| square_angle < square_target_plane_angle[0]
				) {
					target_plane[0] = p;
					square_target_plane_angle[0] = square_angle;
				}
			}
		} });

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
