package car.com.Model;

import javax.persistence.*;

/**
 * Created by ThienDN on 8/24/2015.
 */

@Entity
@Table(name = "showroom")
public class Model {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int showId;
    @Column
    private String showName;
    @Column
    private String address;
    @Column
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
    public Model(){};
    public Model(int showId, String showName, String address, String city) {
        super();
        this.showId = showId;
        this.showName = showName;
        this.address = address;
        this.city = city;
    }


}
