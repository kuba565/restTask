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
        @NamedQuery(name = "User.findAll", query = "SELECT new User(u.id, u.name, u.password) FROM User u"),
        @NamedQuery(name = "User.findById", query = "SELECT new User(u.id, u.name, u.password) FROM User u WHERE u.id = :id")
})
public class User extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
