public class Main {
    public static void main(String[] args) {
        FloorPlan floorPlan = new FloorPlan(20, 20);
        floorPlan.loadFromFile("path/to/floorplan_20x20.csv");
        
        CleanSweepVacuum vacuum = new CleanSweepVacuum(0, 0, floorPlan);

        // Loop through each row in a zigzag pattern
        for (int y = 0; y < 20; y++) {
            if (y % 2 == 0) {
                // Move left to right for even rows
                for (int x = 0; x < 20; x++) {
                    vacuum.move(Direction.EAST);
                    vacuum.clean();
                }
            } else {
                // Move right to left for odd rows
                for (int x = 19; x >= 0; x--) {
                    vacuum.move(Direction.WEST);
                    vacuum.clean();
                }
            }
            // Move down to the next row, if not on the last row
            if (y < 19) {
                vacuum.move(Direction.SOUTH);
                vacuum.clean();
            }
        }

        // Optionally, save activity log or print summary of actions
        System.out.println("Cleaning routine completed.");
    }
}
