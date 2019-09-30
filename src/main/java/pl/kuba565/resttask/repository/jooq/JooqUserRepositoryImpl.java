package pl.kuba565.resttask.repository.jooq;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import pl.kuba565.resttask.model.User;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.kuba565.resttask.jooq.Tables.USER;

public class JooqUserRepositoryImpl extends JooqGenericRepositoryImpl<User> {
    private DSLContext dslContext;

    public JooqUserRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public User update(User entity) {
        dslContext.update(USER)
                .set(USER.NAME, entity.getName())
                .set(USER.PASSWORD, entity.getPassword())
                .where(USER.ID.eq(Math.toIntExact(entity.getId())))
                .execute();
        return findById(entity.getId());
    }

    @Override
    public void create(User entity) {
        dslContext.insertInto(USER,
                USER.NAME, USER.PASSWORD)
                .values(entity.getName(), String.valueOf(entity.getPassword()))
                .execute();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        Result<Record3<Integer, String, String>> result = dslContext
                .select(
                        USER.ID,
                        USER.NAME,
                        USER.PASSWORD
                )
                .from(USER)
                .fetch();

        if (result.isEmpty()) {
            throw new NoResultException();
        }

        for (int i = 0; i < result.size(); i++) {

            Integer userId = result.getValue(i, USER.ID);
            String userName = result.getValue(i, USER.NAME);
            String userPassword = result.getValue(i, USER.PASSWORD);

            User user = new User(userId.longValue(), userName, userPassword);

            users.add(user);
        }
        return users;
    }

    @Override
    public void deleteById(Long id) {
        dslContext.delete(USER).where(USER.ID.eq(Math.toIntExact(id))).execute();
    }

    @Override
    public boolean exists(Long id) {
        return !dslContext.select().where(USER.ID.eq(Math.toIntExact(id))).fetch().isEmpty();
    }

    @Override
    public User findById(Long id) {
        Result<Record3<Integer, String, String>> result = dslContext
                .select(
                        USER.ID,
                        USER.NAME,
                        USER.PASSWORD
                )
                .from(USER)
                .where(USER.ID.eq(Math.toIntExact(id)))
                .fetch();

        if (result.isEmpty()) {
            throw new NoResultException();
        }

        Integer userId = result.getValue(0, USER.ID);
        String userName = result.getValue(0, USER.NAME);
        String userPassword = result.getValue(0, USER.PASSWORD);

        return new User(userId.longValue(), userName, userPassword);
    }

    public User findByName(String name) {
        Result<Record3<Integer, String, String>> result = dslContext
                .select(
                        USER.ID,
                        USER.NAME,
                        USER.PASSWORD
                )
                .from(USER)
                .where(USER.NAME.eq(name))
                .fetch();

        if (result.isEmpty()) {
            throw new NoResultException();
        }

        Integer userId = result.getValue(0, USER.ID);
        String userName = result.getValue(0, USER.NAME);
        String userPassword = result.getValue(0, USER.PASSWORD);

        return new User(userId.longValue(), userName, userPassword);
    }
}
