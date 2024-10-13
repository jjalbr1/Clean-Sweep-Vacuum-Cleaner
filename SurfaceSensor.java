public class SurfaceSensor {
	// Always returns bare floor; can be expanded to detect different surface types.
	public SurfaceType getSurfaceType(int x, int y) {
		return SurfaceType.BARE_FLOOR;
	}
}
