package shan.com.requestandrespone;

/**
 * Created by ThienDN on 8/29/2015.
 */
public class ModelRespone {
    private int showId;
    private String showName;
    private String address;
    private String city;

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public ModelRespone(){}

    public ModelRespone(int showId, String showName, String address, String city) {
        super();
        this.showId = showId;
        this.showName = showName;
        this.address = address;
        this.city = city;
    }
}
