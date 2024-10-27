public class PowerManagement {
	private final int maxBattery;
	private int battery;

	// Constructor: Sets the initial battery level and max battery capacity.
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

	//overloaded method if surface types are different
	public int consumePower(SurfaceType currentSurfaceType, SurfaceType nextSurfaceType) {
		int powerConsumed;
		int powerConsumedOne;
		int powerConsumedTwo;
		
		if (currentSurfaceType == SurfaceType.BARE_FLOOR) {
			powerConsumedOne = 1;
		} else if (currentSurfaceType == SurfaceType.LOW_PILE_CARPET) {
			powerConsumedOne = 2;
		} else if (currentSurfaceType == SurfaceType.HIGH_PILE_CARPET) {
			powerConsumedOne = 3;
		} else {
			powerConsumedOne = 1;
		}
		
		if (nextSurfaceType == SurfaceType.BARE_FLOOR) {
			powerConsumedTwo = 1;
		} else if (nextSurfaceType == SurfaceType.LOW_PILE_CARPET) {
			powerConsumedTwo = 2;
		} else if (nextSurfaceType == SurfaceType.HIGH_PILE_CARPET) {
			powerConsumedTwo = 3;
		} else {
			powerConsumedTwo = 1;
		}
		
		powerConsumed = (powerConsumedOne + powerConsumedTwo) / 2;
		
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
