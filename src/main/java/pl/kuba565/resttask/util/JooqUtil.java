package pl.kuba565.resttask.util;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class JooqUtil {
    public static <E> List<E> nativeQuery(EntityManager em, org.jooq.Query query, Class<E> type) {

        // Extract the SQL statement from the jOOQ query:
        Query result = em.createNativeQuery(query.getSQL(), type);

        // Extract the bind values from the jOOQ query:
        List<Object> values = query.getBindValues();
        for (int i = 0; i < values.size(); i++) {
            result.setParameter(i + 1, values.get(i));
        }

        // There's an unsafe cast here, but we can be sure that we'll get the right type from JPA
        return result.getResultList();
    }
}
