package pl.kuba565.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NamedQuery(name = "Car.findAll", query = "select new Car(c.id, c.weight, c.numberOfSeats, c.registrationNumber) from Car c")
@NamedQuery(name = "Car.deleteById", query = "DELETE FROM Car WHERE id = :id")
@NamedQuery(name = "Car.checkIfExists", query = "SELECT COUNT(*) FROM Car WHERE id = :id")
@NamedQuery(name = "Car.findById", query = "select new Car(c.id, c.weight, c.numberOfSeats, c.registrationNumber) from Car c where c.id=:id")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer weight;
    private Integer numberOfSeats;
    private String registrationNumber;
    @OneToOne(fetch = FetchType.LAZY)
    private Log log;

    public Car(Integer weight, Integer numberOfSeats, String registrationNumber) {
        this.weight = weight;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
    }

    public Car(Integer weight, Integer numberOfSeats, String registrationNumber, Log log) {
        this.weight = weight;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
        this.log = log;
    }

    public Car(Long id, Integer weight, Integer numberOfSeats, String registrationNumber) {
        this.id = id;
        this.weight = weight;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
    }

}