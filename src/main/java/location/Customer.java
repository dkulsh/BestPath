package location;

public class Customer extends Location{

    private boolean delivered;

    public Customer(double latitude, double longitude) {
        super(latitude, longitude);
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
