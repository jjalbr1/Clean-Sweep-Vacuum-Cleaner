
# Clean Sweep Vacuum Cleaner Project

## Overview
This project simulates a robotic vacuum cleaner called Clean Sweep. It can move around a floor plan, detect dirt, clean tiles, and use power based on the type of surface it's on. The floor plan is loaded from a CSV file and shown in the console.

## Features
- **Load Floor Plan from CSV:** Reads a floor plan from a CSV file, where each tile has a surface type like Bare Floor, Low-Pile Carpet, High-Pile Carpet, or Obstacle.
- **Movement and Obstacle Detection:** The vacuum moves in four directions (North, South, East, West) and avoids obstacles and stairs.
- **Dirt Detection and Cleaning:** The vacuum checks if a tile is dirty, cleans it, and uses power based on the surface type.
- **Power Management:** Tracks power usage for moving and cleaning. The vacuum recharges when it runs low.
- **Activity Logging:** Logs every action, including movements, cleaning, and power usage, and saves these logs for troubleshooting.
- **Display Floor Plan:** Shows the floor plan in the console with surface types for each tile.

## Requirements
- Java 8 or higher
- A CSV file that describes the floor plan, like this:
  ```
  B,L,H,O
  B,B,L,O
  L,L,H,B
  O,B,B,H
  ```
  Where:
  - `B` = Bare Floor
  - `L` = Low-Pile Carpet
  - `H` = High-Pile Carpet
  - `O` = Obstacle

## How to Run
1. Clone the project:
   ```bash
   git clone <repository-url>
   ```

2. Go to the project folder:
   ```bash
   cd <project-directory>
   ```

3. Compile the project:
   ```bash
   javac Main.java
   ```

4. Run the program:
   ```bash
   java Main
   ```

5. Make sure the CSV file (e.g., `floorplan.csv`) is in the right place, and update the file path in `Main.java` if needed.

## Project Files

- **CleanSweepVacuum.java**  
  Manages the vacuum’s movement, cleaning, power usage, and logging.

- **ActivityLogger.java**  
  Tracks and saves the vacuum’s actions for troubleshooting.

- **FloorPlan.java**  
  Loads the floor plan from a CSV file, tracks obstacles, and surface types.

- **DirtSensor.java**  
  Detects dirt and manages cleaning.

- **PowerManagement.java**  
  Tracks and recharges battery power based on movement and cleaning.

- **SurfaceSensor.java**  
  Detects the type of surface under the vacuum.

- **SurfaceType.java**  
  Lists types of surfaces (e.g., Bare Floor, Carpet, Obstacle).

- **Direction.java**  
  Lists four directions (North, South, East, West) and moves coordinates accordingly.

- **Main.java**  
  Runs the program, loads the floor plan, and starts the vacuum.

## Authors
- SE 459 Group 1
