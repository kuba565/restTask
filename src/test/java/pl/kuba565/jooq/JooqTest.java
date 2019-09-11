package pl.kuba565.jooq;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.conf.RenderNameStyle;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;

import javax.persistence.EntityManager;

public class JooqTest extends AbstractTest {
    @Autowired
    private EntityManager entityManager;

    @Test
    public void shouldCreateCar() {
//        DSLContext dslContext = DSL.using(SQLDialect.H2);
//        dslContext.configuration().settings().setRenderNameStyle(RenderNameStyle.AS_IS);
//        SelectConditionStep<Record1<String>> jooqQuery = dslContext.select(CAR)
    }
}
