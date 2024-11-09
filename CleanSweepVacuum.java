public class CleanSweepVacuum {
    private int currentX;
    private int currentY;
    private DirtSensor dirtSensor;
    private PowerManagement powerManagement;
    private FloorPlan floorPlan;

    public CleanSweepVacuum(int startX, int startY, FloorPlan floorPlan) {
        this.currentX = startX;
        this.currentY = startY;
        this.powerManagement = new PowerManagement(250); // Assume max battery is 250 units
        this.dirtSensor = new DirtSensor(floorPlan, powerManagement, 50); // Assume max dirt capacity is 50 units
        this.floorPlan = floorPlan;
        System.out.println("Vacuum initialized at position (" + startX + ", " + startY + ")");
    }

    public boolean canMove(Direction direction) {
        return floorPlan.isPathClear(direction, currentX, currentY);
    }

    public void move(Direction direction) {
        System.out.println("Attempting to move " + direction + " from (" + currentX + ", " + currentY + ")");

        if (canMove(direction)) {
            int[] newCoords = direction.move(currentX, currentY);
            SurfaceType currentSurface = floorPlan.getSurfaceType(currentX, currentY);
            SurfaceType nextSurface = floorPlan.getSurfaceType(newCoords[0], newCoords[1]);
            int powerUsed = powerManagement.consumePower(currentSurface, nextSurface);

            currentX = newCoords[0];
            currentY = newCoords[1];
            System.out.println("Moved " + direction + " to (" + currentX + ", " + currentY + "), Power used: " + powerUsed);
            System.out.println("Remaining power: " + powerManagement.getBatteryLevel());
        } else {
            System.out.println("Move blocked by obstacle or stairs.");
        }
    }

    public void clean() {
        System.out.println("Cleaning at (" + currentX + ", " + currentY + ")");

        if (dirtSensor.isDirty(currentX, currentY)) {
            dirtSensor.cleanTile(currentX, currentY);
            int remainingDirtCapacity = dirtSensor.getDirtCapacity();
            System.out.println("Tile cleaned. Dirt capacity remaining: " + remainingDirtCapacity);
            System.out.println("Remaining power after cleaning: " + powerManagement.getBatteryLevel());
        } else {
            System.out.println("No dirt detected at (" + currentX + ", " + currentY + ")");
        }

        if (powerManagement.getBatteryLevel() == 0) {
            System.out.println("Battery depleted. Returning to charging station.");
            recharge();
        }

        if (dirtSensor.getDirtCapacity() == 0) {
            System.out.println("Dirt capacity full. Emptying dirt.");
            emptyDirt();
        }
    }

    public void recharge() {
        powerManagement.recharge();
        System.out.println("Recharged battery to full capacity.");
    }

    public void emptyDirt() {
        dirtSensor.emptyDirt();
        System.out.println("Dirt container emptied.");
    }
}
