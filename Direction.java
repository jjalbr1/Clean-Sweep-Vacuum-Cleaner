public enum Direction {
	NORTH, SOUTH, EAST, WEST;

	// Moves in the specified direction and returns the new coordinates.
	public int[] move(int x, int y) {
		if (this == NORTH) {
			return new int[] { x, y - 1 };
		} else if (this == SOUTH) {
			return new int[] { x, y + 1 };
		} else if (this == EAST) {
			return new int[] { x + 1, y };
		} else if (this == WEST) {
			return new int[] { x - 1, y };
		} else { // Don't move if direction is invalid
			return new int[] { x, y };
		}
	}
}
