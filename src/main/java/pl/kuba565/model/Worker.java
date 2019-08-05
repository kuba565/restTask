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
        @NamedQuery(name = "Worker.countAssignedWorkers",
                query = "SELECT COUNT(*) FROM Worker WHERE car.id = :id"),
        @NamedQuery(name = "Worker.findAll",
                query = "SELECT w FROM Worker w"),
        @NamedQuery(name = "Worker.deleteById",
                query = "DELETE FROM Worker WHERE id = :id"),
        @NamedQuery(name = "Worker.checkIfExists",
                query = "SELECT COUNT(*) FROM Car WHERE id = :id"),
        @NamedQuery(name = "Worker.findById",
                query = "SELECT w FROM Worker w WHERE id = :id")
})
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Car car;
    private String pesel;
    private String name;
    private String surname;

    public Worker(Long id, String pesel, String name, String surname) {
        this.id = id;
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
    }

    public Worker(String pesel, String name, String surname) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
    }

    public Worker(Car car, String pesel, String name, String surname) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.car = car;
    }

    public Worker(Long id) {
        this.id = id;
    }
}
