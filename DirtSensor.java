public class DirtSensor {
	private FloorPlan floorPlan;
	private PowerManagement powerManagement;

	// Constructor: Associates the sensor with the floor plan and power management.
	public DirtSensor(FloorPlan floorPlan, PowerManagement powerManagement) {
		this.floorPlan = floorPlan;
		this.powerManagement = powerManagement;
	}

	// Check if a tile at (x, y) is dirty
	public boolean isDirty(int x, int y) {
		return floorPlan.isDirty(x, y);
	}

	// Clean the tile at (x, y) and consume power accordingly
	public void cleanTile(int x, int y) {
		SurfaceType surfaceType = floorPlan.getSurfaceType(x, y);
		floorPlan.cleanTile(x, y);
		powerManagement.consumePower(surfaceType); // Power is consumed based on surface type
	}
}
