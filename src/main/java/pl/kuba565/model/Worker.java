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
        @NamedQuery(name = "Worker.countAssignedWorkers", query = "SELECT COUNT(*) FROM Worker WHERE car.id = :id"),
        @NamedQuery(name = "Worker.deleteById", query = "DELETE FROM Worker WHERE id = :id"),
        @NamedQuery(name = "Worker.checkIfExists", query = "SELECT COUNT(*) FROM Car WHERE id = :id"),
        @NamedQuery(name = "Worker.findById", query = "SELECT new Worker(w.id, w.pesel, w.name, w.surname, w.car) FROM Worker w WHERE w.id = :id"),
        @NamedQuery(name = "Worker.findAll", query = "select new Worker(w.id, w.pesel, w.name, w.surname, w.car) from Worker w")
})
@NamedEntityGraph(name = "workerCarGraph",
        attributeNodes = @NamedAttributeNode(value = "car"))
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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

    public Worker(Long id, String pesel, String name, String surname, Car car) {
        this.id = id;
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.car = car;
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
}
