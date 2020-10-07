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
        @NamedQuery(name = "Worker.countAssignedWorkers", query = "SELECT COUNT(*) FROM Worker WHERE car.id = :id"),
        @NamedQuery(name = "Worker.findAll", query = "SELECT new Worker(w.id, w.pesel, w.name, w.surname, w.car) FROM Worker w"),
        @NamedQuery(name = "Worker.findById", query = "SELECT new Worker(w.id, w.pesel, w.name, w.surname, w.car) FROM Worker w WHERE w.id = :id")
})
public class Worker extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;
    private String pesel;
    private String name;
    private String surname;

    public Worker(String pesel, String name, String surname) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
    }

    public Worker(Long id, String pesel, String name, String surname) {
        this(pesel, name, surname);
        this.id = id;
    }

    public Worker(Long id, String pesel, String name, String surname, Car car) {
        this(pesel, name, surname);
        this.id = id;
        this.car = car;
    }

    public Worker(Car car, String pesel, String name, String surname) {
        this(pesel, name, surname);
        this.car = car;
    }
}
