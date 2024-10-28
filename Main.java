public class Main {
    public static void main(String[] args) {
        FloorPlan floorPlan = new FloorPlan(4, 4);
        floorPlan.loadFromFile("path/to/your/floorplan.csv");
        
        CleanSweepVacuum vacuum = new CleanSweepVacuum(0, 0, floorPlan);
        vacuum.move(Direction.EAST);
        vacuum.clean();
        
        // Save log to file after operations
        vacuum.dumpLog("activity_log.txt");
    }
}
