package pl.kuba565.resttask.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "Car.findAll", query = "SELECT new Car(c.id, c.weight, c.numberOfSeats, c.registrationNumber) FROM Car c"),
        @NamedQuery(name = "Car.findById", query = "SELECT new Car(c.id, c.weight, c.numberOfSeats, c.registrationNumber) FROM Car c WHERE c.id=:id")
})
public class Car extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer weight;
    private Integer numberOfSeats;
    private String registrationNumber;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Log log;

    public Car(Integer weight, Integer numberOfSeats, String registrationNumber) {
        this.weight = weight;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
    }

    public Car(Log log, Integer weight, Integer numberOfSeats, String registrationNumber) {
        this(weight, numberOfSeats, registrationNumber);
        this.log = log;
    }

    public Car(Long id, Integer weight, Integer numberOfSeats, String registrationNumber) {
        this(weight, numberOfSeats, registrationNumber);
        this.id = id;
    }

    public Car(Log log) {
        this.log = log;
    }
}