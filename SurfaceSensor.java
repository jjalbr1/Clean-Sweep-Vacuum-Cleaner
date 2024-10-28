public class SurfaceSensor {
    private FloorPlan floorPlan;

    public SurfaceSensor(FloorPlan floorPlan) {
        this.floorPlan = floorPlan;
    }

    public SurfaceType getSurfaceType(int x, int y) {
        return floorPlan.getSurfaceType(x, y);
    }
}
