import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FloorPlan {
	private SurfaceType[][] floorGrid; // 2D grid of surface types
	private boolean[][] dirtGrid; // 2D grid to track if tiles are dirty.

	// Constructor: Initializes the floor plan with given dimensions
	public FloorPlan(int width, int height) {
		floorGrid = new SurfaceType[width][height];
		dirtGrid = new boolean[width][height];
	}

	// Method to load floor plan from a CSV file and set initial surface and dirt states
	public void loadFromFile(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int y = 0;
			while ((line = br.readLine()) != null) {
				String[] cells = line.split(",");
				for (int x = 0; x < cells.length; x++) {
					switch (cells[x]) {
						case "B":
							floorGrid[x][y] = SurfaceType.BARE_FLOOR;
							break;
						case "L":
							floorGrid[x][y] = SurfaceType.LOW_PILE_CARPET;
							break;
						case "H":
							floorGrid[x][y] = SurfaceType.HIGH_PILE_CARPET;
							break;
						case "O":
							floorGrid[x][y] = SurfaceType.OBSTACLE;
							break;
						default:
							floorGrid[x][y] = SurfaceType.UNKNOWN;
							break;
					}
					// Initialize all tiles to be dirty (this can be customized later)
					dirtGrid[x][y] = true;
				}
				y++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Display the floor plan grid
	public void displayFloorPlan() {
		for (int y = 0; y < floorGrid[0].length; y++) {
			for (int x = 0; x < floorGrid.length; x++) {
				System.out.print(floorGrid[x][y].toString().charAt(0) + " "); // Print first letter of SurfaceType
			}
			System.out.println(); // New line after each row
		}
	}

	// Check if a path in a given direction is clear (no obstacle or stairs)
	public boolean isPathClear(Direction direction, int x, int y) {
		int[] newCoords = direction.move(x, y);
		if (newCoords[0] >= 0 && newCoords[0] < floorGrid.length
				&& newCoords[1] >= 0 && newCoords[1] < floorGrid[0].length) {
			SurfaceType surfaceType = floorGrid[newCoords[0]][newCoords[1]];
			return surfaceType != SurfaceType.OBSTACLE && surfaceType != SurfaceType.STAIRS;
		}
		return false;
	}

	// Get the surface type at the specified coordinates
	public SurfaceType getSurfaceType(int x, int y) {
		return floorGrid[x][y];
	}

	// Get the surface type in the direction from the current position
	public SurfaceType getSurfaceTypeInDirection(Direction direction, int x, int y) {
		int[] newCoords = direction.move(x, y);
		if (newCoords[0] >= 0 && newCoords[0] < floorGrid.length
				&& newCoords[1] >= 0 && newCoords[1] < floorGrid[0].length) {
			return floorGrid[newCoords[0]][newCoords[1]];
		}
		return SurfaceType.UNKNOWN;
	}

	// Check if a tile is dirty at (x, y)
	public boolean isDirty(int x, int y) {
		return dirtGrid[x][y];
	}

	// Mark a tile as clean at (x, y)
	public void cleanTile(int x, int y) {
		dirtGrid[x][y] = false;
	}
}
 
