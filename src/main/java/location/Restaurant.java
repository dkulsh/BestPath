package location;

public class Restaurant extends Location{

    private int timeToPrepare;
    private boolean picked;

    public Restaurant(double latitude, double longitude) {
        super(latitude, longitude);
    }

    public int getTimeToPrepare() {
        return timeToPrepare;
    }

    public void setTimeToPrepare(int timeToPrepare) {
        this.timeToPrepare = timeToPrepare;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }
}
