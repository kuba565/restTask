package pl.kuba565.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Car.findAll",
                query = "Select new Car(c.id, c.weight, c.numberOfSeats, c.registrationNumber) FROM Car c"),
        @NamedQuery(name = "Car.deleteById",
                query = "DELETE FROM Car WHERE id = :id"),
        @NamedQuery(name = "Car.checkIfExists",
                query = "SELECT COUNT(*) FROM Car WHERE id = :id"),
        @NamedQuery(name = "Car.findById",
                query = "SELECT new Car(c.id, c.weight, c.numberOfSeats, c.registrationNumber) FROM Car c WHERE id = :id")
})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer weight;
    private Integer numberOfSeats;
    private String registrationNumber;
    @Lob
    @Column(updatable = false)
    private String log;

    public Car(Integer weight, Integer numberOfSeats, String registrationNumber) {
        this.weight = weight;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
    }

    public Car(Integer weight, Integer numberOfSeats, String registrationNumber, String log) {
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