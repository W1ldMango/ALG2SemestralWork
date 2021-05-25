package App;

public class Room {

    private int places;
    private int kids;
    private boolean lux;
    private int price;
    private int number;

    public Room(int number,int places, int kids, boolean lux, int price) {
        this.places = places;
        this.kids = kids;
        this.lux = lux;
        this.price = price;
        this.number = number;
    }

    public int getPlaces() {
        return places;
    }

    public int getKids() {
        return kids;
    }

    public boolean isLux() {
        return lux;
    }

    public int getPrice() {return price;}

    public int getNumber() {
        return number;
    }
}
