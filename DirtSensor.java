public class DirtSensor {
	private FloorPlan floorPlan;
	private PowerManagement powerManagement;
	private int dirtCapacity;	
	
	// Constructor: Associates the sensor with the floor plan and power management.
	public DirtSensor(FloorPlan floorPlan, PowerManagement powerManagement, int dirtCapacity) {
		this.floorPlan = floorPlan;
		this.powerManagement = powerManagement;
		this.dirtCapacity = dirtCapacity;
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
		consumeDirt(surfaceType);
	}

	public void consumeDirt(surfaceType surfaceType) {
		dirtCapacity -= 1;
		System.out.println("Capacity remaining: " + dirtCapacity);
	}
	
	public void emptyDirt() {
		dirtCapacity = 50;
		System.out.println("Dirt emptied. Capacity is 50.");
	}
	
	// Return dirt capacity remaining
	public int getDirtCapacity() {
		return dirtCapacity;
	}
}
