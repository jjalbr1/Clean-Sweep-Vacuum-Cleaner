
# Clean Sweep Vacuum Cleaner Project

## Overview
This project simulates a Clean Sweep vacuum cleaner that moves around a floor plan, detects dirt, cleans tiles, and consumes power based on the surface type. The floor plan is read from a CSV file, stored in memory, and displayed in the console.

## Features
- **Load Floor Plan from CSV:** The floor plan is read from a CSV file where each tile is a different surface type (Bare Floor, Low-Pile Carpet, High-Pile Carpet, Obstacle).
- **Movement and Obstacle Detection:** The vacuum moves in four directions (North, South, East, West) and detects obstacles and stairs to avoid collisions.
- **Dirt Detection and Cleaning:** The vacuum detects if a tile is dirty and cleans it, consuming power based on the surface type.
- **Power Management:** The vacuum consumes power when it moves or cleans. It can be recharged when needed.
- **Display Floor Plan:** The floor plan is displayed in the console after loading, showing the surface types for each tile.

## Requirements
- Java 8 or higher
- A CSV file representing the floor plan. Example content:
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
1. Clone the repository to your local machine:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd <project-directory>
   ```

3. Compile the project using your preferred Java IDE or directly via the command line:
   ```bash
   javac Main.java
   ```

4. Run the `Main` class:
   ```bash
   java Main
   ```

5. Ensure the CSV file (e.g., `floorplan.csv`) is in the correct path, and modify the file path in `Main.java` as needed.

## Project Structure

- **CleanSweepVacuum.java**  
  Handles the vacuum's movement, cleaning, and interaction with the floor plan. 

- **FloorPlan.java**  
  Loads the floor plan from a CSV file, stores the grid of surface types, and provides methods to interact with the floor plan. Also includes a method to display the grid in the console.

- **DirtSensor.java**  
  Detects if a tile is dirty and handles the cleaning process.

- **PowerManagement.java**  
  Manages the vacuum's battery power, tracking consumption based on surface types, and provides a recharge method.

- **SurfaceSensor.java**  
  Stub class that can be expanded for detecting different surface types.

- **SurfaceType.java**  
  Enum defining the different types of surfaces on the floor (e.g., Bare Floor, Low-Pile Carpet, High-Pile Carpet, Obstacle, etc.).

- **Direction.java**  
  Enum that defines the four movement directions (North, South, East, West) and handles coordinate changes based on direction.

- **Main.java**  
  Entry point to the program, loads the floor plan, initializes the vacuum, and demonstrates basic movement and cleaning functionality.


## Authors
- SE 459  Group 1
