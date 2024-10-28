import java.util.logging.Level;
import java.util.logging.Logger;

public class CleanSweepVacuum {
    private int currentX;
    private int currentY;
    private DirtSensor dirtSensor;
    private PowerManagement powerManagement;
    private FloorPlan floorPlan;
    private ActivityLogger logger;

    private static final Logger systemLogger = Logger.getLogger(CleanSweepVacuum.class.getName());

    public CleanSweepVacuum(int startX, int startY, FloorPlan floorPlan) {
        this.currentX = startX;
        this.currentY = startY;
        this.powerManagement = new PowerManagement(250); // Assume max battery is 250 units
        this.dirtSensor = new DirtSensor(floorPlan, powerManagement, 50); // Assume max dirt capacity is 50 units
        this.floorPlan = floorPlan;
        this.logger = new ActivityLogger();
        systemLogger.setLevel(Level.INFO); // Setting log level to INFO
        logger.log("Vacuum initialized at position (" + startX + ", " + startY + ")");
    }

    /**
     * Checks if the vacuum can move in the specified direction.
     * @param direction Direction to move in (NORTH, SOUTH, EAST, WEST)
     * @return true if movement is possible, false otherwise
     */
    public boolean canMove(Direction direction) {
        return floorPlan.isPathClear(direction, currentX, currentY);
    }

    /**
     * Moves the vacuum in the specified direction if possible.
     * Logs the movement and power consumption.
     * @param direction The direction to move.
     */
    public void move(Direction direction) {
        logger.log("Attempting to move " + direction + " from (" + currentX + ", " + currentY + ")");

        if (canMove(direction)) {
            int[] newCoords = direction.move(currentX, currentY);
            SurfaceType currentSurface = floorPlan.getSurfaceType(currentX, currentY);
            SurfaceType nextSurface = floorPlan.getSurfaceType(newCoords[0], newCoords[1]);
            int powerUsed = powerManagement.consumePower(currentSurface, nextSurface);

            currentX = newCoords[0];
            currentY = newCoords[1];
            logger.log("Moved " + direction + " to (" + currentX + ", " + currentY + "), Power used: " + powerUsed);
            logger.log("Remaining power: " + powerManagement.getBatteryLevel());
        } else {
            logger.log("Move blocked by obstacle or stairs.");
        }
    }

    /**
     * Cleans the current tile if it's dirty and logs the cleaning action.
     */
    public void clean() {
        logger.log("Cleaning at (" + currentX + ", " + currentY + ")");

        if (dirtSensor.isDirty(currentX, currentY)) {
            dirtSensor.cleanTile(currentX, currentY);
            int remainingDirtCapacity = dirtSensor.getDirtCapacity();
            logger.log("Tile cleaned. Dirt capacity remaining: " + remainingDirtCapacity);
            logger.log("Remaining power after cleaning: " + powerManagement.getBatteryLevel());
        } else {
            logger.log("No dirt detected at (" + currentX + ", " + currentY + ")");
        }

        if (powerManagement.getBatteryLevel() == 0) {
            logger.log("Battery depleted. Returning to charging station.");
            recharge();
        }

        if (dirtSensor.getDirtCapacity() == 0) {
            logger.log("Dirt capacity full. Emptying dirt.");
            emptyDirt();
        }
    }

    /**
     * Recharges the vacuum battery to full capacity.
     */
    public void recharge() {
        powerManagement.recharge();
        logger.log("Recharged battery to full capacity.");
    }

    /**
     * Empties the vacuum's dirt container to restore capacity.
     */
    public void emptyDirt() {
        dirtSensor.emptyDirt();
        logger.log("Dirt container emptied.");
    }

    /**
     * Dumps the activity log to a specified file.
     * @param filename The filename where the log will be saved.
     */
    public void dumpLog(String filename) {
        logger.saveLogToFile(filename);
    }
}
