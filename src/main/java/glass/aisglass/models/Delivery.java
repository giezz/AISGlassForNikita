package glass.aisglass.models;

public class Delivery {

    private int number_of_delivery;
    private double price_of_delivery;
    private String address_of_delivery;
    private String description_of_delivery;

    public Delivery(int number_of_delivery, double price_of_delivery, String address_of_delivery, String description_of_delivery) {
        this.number_of_delivery = number_of_delivery;
        this.price_of_delivery = price_of_delivery;
        this.address_of_delivery = address_of_delivery;
        this.description_of_delivery = description_of_delivery;
    }

    public Delivery(double price_of_delivery, String address_of_delivery, String description_of_delivery) {
        this.price_of_delivery = price_of_delivery;
        this.address_of_delivery = address_of_delivery;
        this.description_of_delivery = description_of_delivery;
    }

    public Delivery() {
    }

    public int getNumber_of_delivery() {
        return number_of_delivery;
    }

    public void setNumber_of_delivery(int number_of_delivery) {
        this.number_of_delivery = number_of_delivery;
    }

    public double getPrice_of_delivery() {
        return price_of_delivery;
    }

    public void setPrice_of_delivery(double price_of_delivery) {
        this.price_of_delivery = price_of_delivery;
    }

    public String getAddress_of_delivery() {
        return address_of_delivery;
    }

    public void setAddress_of_delivery(String address_of_delivery) {
        this.address_of_delivery = address_of_delivery;
    }

    public String getDescription_of_delivery() {
        return description_of_delivery;
    }

    public void setDescription_of_delivery(String description_of_delivery) {
        this.description_of_delivery = description_of_delivery;
    }
}
