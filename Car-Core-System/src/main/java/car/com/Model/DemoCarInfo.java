package car.com.Model;

import javax.persistence.*;

/**
 * Created by ThienDN on 9/2/2015.
 */
@Entity
@Table(name = "cardetaildemo_table")
public class DemoCarInfo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int carId;
    @Column
    private String carName;
    @Column
    private String carColor;
    @Column
    private String carPrice;
    @Column
    private String carQuantity;
    @Column
    private String carMode;
    @Column
    private String carMadeIn;
    @Column
    private String carDescription;
    @Column
    private String inProduction;
    @Column
    private String status;

    @Column
    private  String carImage;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarQuantity() {
        return carQuantity;
    }

    public void setCarQuantity(String carQuantity) {
        this.carQuantity = carQuantity;
    }

    public String getCarMode() {
        return carMode;
    }

    public void setCarMode(String carMode) {
        this.carMode = carMode;
    }

    public String getCarMadeIn() {
        return carMadeIn;
    }

    public void setCarMadeIn(String carMadeIn) {
        this.carMadeIn = carMadeIn;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public String getInProduction() {
        return inProduction;
    }

    public void setInProduction(String inProduction) {
        this.inProduction = inProduction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public DemoCarInfo(){}

    public DemoCarInfo(String carName, String carColor, String carPrice, String carQuantity, String carMode, String carMadeIn, String carDescription, String inProduction, String status, String carImage) {
        this.carName = carName;
        this.carColor = carColor;
        this.carPrice = carPrice;
        this.carQuantity = carQuantity;
        this.carMode = carMode;
        this.carMadeIn = carMadeIn;
        this.carDescription = carDescription;
        this.inProduction = inProduction;
        this.status = status;
        this.carImage = carImage;
    }
}
