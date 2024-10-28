public class CleanSweepVacuum {
	private int currentX;
	private int currentY;
	private DirtSensor dirtSensor;
	private PowerManagement powerManagement;
	private FloorPlan floorPlan;

	// Constructor: Initializes the vacuum's starting position, power, and dirt sensor.
	public CleanSweepVacuum(int startX, int startY, FloorPlan floorPlan) {
		this.currentX = startX;
		this.currentY = startY;
		this.powerManagement = new PowerManagement(250); // Assume 250 is the max battery
		this.dirtSensor = new DirtSensor(floorPlan, powerManagement, 50); // Assume 50 is the max dirt capacity
		this.floorPlan = floorPlan;
	}

	// Checks if the vacuum can move in a certain direction (no obstacle or stairs)
	public boolean canMove(Direction direction) {
		return floorPlan.isPathClear(direction, currentX, currentY);
	}

	// Detect if there's an obstacle in the given direction
	public boolean detectObstacle(Direction direction) {
		SurfaceType surfaceType = floorPlan.getSurfaceTypeInDirection(direction, currentX, currentY);
		return surfaceType == SurfaceType.OBSTACLE;
	}

	// Detect if there are stairs in the given direction
	public boolean detectStairs(Direction direction) {
		SurfaceType surfaceType = floorPlan.getSurfaceTypeInDirection(direction, currentX, currentY);
		return surfaceType == SurfaceType.STAIRS; 
	}

	// Detect if the path is unknown (not mapped) in the given direction
	public boolean detectUnknownPath(Direction direction) {
		SurfaceType surfaceType = floorPlan.getSurfaceTypeInDirection(direction, currentX, currentY);
		return surfaceType == SurfaceType.UNKNOWN;
	}

	// Move the vacuum in the specified direction if possible
	public void move(Direction direction) {
		if (canMove(direction)) {
			int[] newCoords = direction.move(currentX, currentY);
			SurfaceType currentSurfaceType = floorPlan.getSurfaceType(currentX, currentY);
			SurfaceType nextSurfaceType = floorPlan.getSurfaceType(newCoords[0], newCoords[1]);
			int powerUsed = 0;
			
			if (currentSurfaceType == nextSurfaceType) {
				powerUsed = powerManagement.consumePower(nextSurfaceType); // Power consumption depends on surface type
			} else {
				powerUsed = powerManagement.consumePower(currentSurfaceType, nextSurfaceType); // Power consumption is average of two surfaces
			}
			
			// Update current position
			this.currentX = newCoords[0];
			this.currentY = newCoords[1];
			System.out.println("Moving to: (" + currentX + ", " + currentY + ")");
			System.out.println("Power consumed for move: " + powerUsed);
		} else {
			System.out.println("Obstacle or stairs detected. Cannot move " + direction);
		}

		if (powerManagement.getBatteryLevel() == 0) {
			powerManagement.recharge();
		}
	}

	// Clean the current tile, if dirty, and consume power for cleaning
	public void clean() {
		SurfaceType surfaceType = floorPlan.getSurfaceType(currentX, currentY);
		if (dirtSensor.isDirty(currentX, currentY)) {
			dirtSensor.cleanTile(currentX, currentY); // Clean the tile
			powerManagement.consumePower(surfaceType); // Power used during cleaning
			dirtSensor.consumeDirt(surfaceType); // Dirt capacity increases
		} else {
			System.out.println("No dirt detected at: (" + currentX + ", " + currentY + ")");
		}
		if (powerManagement.getBatteryLevel() == 0) {
			recharge();
		}
		if (dirtSensor.getDirtCapacity() == 0) {
			emptyDirt();
		}
	}

	// Recharge the vacuum's battery to full capacity
	public void recharge() {
		powerManagement.recharge();
	}

	// Restore vacuum's dirt carrying capacity to full
	public void emptyDirt() {
		dirtSensor.emptyDirt();
	}
}
