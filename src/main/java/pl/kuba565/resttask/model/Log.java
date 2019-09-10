package pl.kuba565.resttask.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Log extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String value;

    public Log(String value) {
        this.value = value;
    }

    public Log(Long id) {
        this.id = id;
    }

}
