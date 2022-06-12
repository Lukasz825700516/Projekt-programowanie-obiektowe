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

		final Plane target_plane[] = {null};
		final double square_target_plane_angle[] = {Double.POSITIVE_INFINITY};
		// TODO: Maybe instead of forEachPlane, reduce search range using some quadtree
		simulation.forEachPlane((p) -> {
		if (p != self) {
			var offset = Vector2.difference(p.position, self.position);
			var square_distance = offset.square_length();
			if (square_distance > self.viewCone.maxViewDistance * self.viewCone.maxViewDistance) return;
			var view_direction = Vector2.normal_angle(self.angle);

			var angle = Math.atan2(offset.y, offset.x) - self.angle;


			var square_angle = angle * angle;
			if (target_plane[0] == null) {
				target_plane[0] = p;
				square_target_plane_angle[0] = square_angle;
			}

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
			final var angle = Math.atan2(offset.y, offset.x) - self.angle;
			final double angleSquare = angle * angle; // angle between self.velocity and offset

			final var absoluteAcceptableShootingAngle = 0.1;
			if (self.shootCooldown <= 0) {
				if (angleSquare < absoluteAcceptableShootingAngle)
					return new ShootAction();
				else 
					return new ChangeThrustAngleAction(angle);
			}
		}
		return new ThrustAction(self.engine.maxAcceleration);
		// var c = this.random.nextInt() % 2;
		// switch(c) {
			// case 0: return new NoneAction();
			// case 1: return new ThrustAction(self.engine.maxAcceleration);
			// default: return new NoneAction();
		// }
	}

}
