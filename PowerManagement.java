public class PowerManagement {
	private final int maxBattery;
	private int battery;

	// Constructor: Sets the initial battery level and max battery capacity
	public PowerManagement(int maxBattery) {
		this.battery = maxBattery;
		this.maxBattery = maxBattery;
	}

	// Consume power based on the type of surface the vacuum is on
	public int consumePower(SurfaceType surfaceType) {
		int powerConsumed;
		// Power consumption is higher on carpets compared to bare floors
		if (surfaceType == SurfaceType.BARE_FLOOR) {
			powerConsumed = 1;
		} else if (surfaceType == SurfaceType.LOW_PILE_CARPET) {
			powerConsumed = 2;
		} else if (surfaceType == SurfaceType.HIGH_PILE_CARPET) {
			powerConsumed = 3;
		} else {
			powerConsumed = 1;
		}

		// Reduce the battery by the power consumed
		battery -= powerConsumed;
		System.out.println("Power remaining: " + battery);

		return powerConsumed;
	}

	// Recharge the battery to full capacity
	public void recharge() {
		battery = maxBattery;
		System.out.println("Battery recharged to full.");
	}

	// Get the current battery level
	public int getBatteryLevel() {
		return battery;
	}
}
