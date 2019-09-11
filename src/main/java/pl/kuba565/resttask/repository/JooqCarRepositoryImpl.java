package pl.kuba565.resttask.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.resttask.model.Car;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class JooqCarRepositoryImpl extends GenericRepositoryImpl<Car> {
    @Autowired
    private DSLContext dsl;

    public JooqCarRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Car update(Car entity) {
        return null;
    }

    @Override
    public void create(Car entity) {

    }

    @Override
    public List<Car> findAll() {
        String url = "jdbc:h2:mem:test";
        String password = "";
        String userName = "";
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.H2);
            Result<Record> result = create.select().from(Car.class.toString()).fetch();
            System.out.println(result);
            return result.getValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public Car findById(Long id) {
        return null;
    }
}
