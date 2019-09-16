/*
 * This file is generated by jOOQ.
 */
package pl.kuba565.resttask.jooq.tables;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import pl.kuba565.resttask.jooq.Indexes;
import pl.kuba565.resttask.jooq.Keys;
import pl.kuba565.resttask.jooq.Public;
import pl.kuba565.resttask.jooq.tables.records.CarRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Car extends TableImpl<CarRecord> {

    private static final long serialVersionUID = 415062021;

    /**
     * The reference instance of <code>PUBLIC.CAR</code>
     */
    public static final Car CAR = new Car();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CarRecord> getRecordType() {
        return CarRecord.class;
    }

    /**
     * The column <code>PUBLIC.CAR.ID</code>.
     */
    public final TableField<CarRecord, Integer> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.CAR.LOG_ID</code>.
     */
    public final TableField<CarRecord, Long> LOG_ID = createField(DSL.name("LOG_ID"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>PUBLIC.CAR.WEIGHT</code>.
     */
    public final TableField<CarRecord, BigDecimal> WEIGHT = createField(DSL.name("WEIGHT"), org.jooq.impl.SQLDataType.DECIMAL, this, "");

    /**
     * The column <code>PUBLIC.CAR.NUMBER_OF_SEATS</code>.
     */
    public final TableField<CarRecord, Short> NUMBER_OF_SEATS = createField(DSL.name("NUMBER_OF_SEATS"), org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>PUBLIC.CAR.REGISTRATION_NUMBER</code>.
     */
    public final TableField<CarRecord, String> REGISTRATION_NUMBER = createField(DSL.name("REGISTRATION_NUMBER"), org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * Create a <code>PUBLIC.CAR</code> table reference
     */
    public Car() {
        this(DSL.name("CAR"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.CAR</code> table reference
     */
    public Car(String alias) {
        this(DSL.name(alias), CAR);
    }

    /**
     * Create an aliased <code>PUBLIC.CAR</code> table reference
     */
    public Car(Name alias) {
        this(alias, CAR);
    }

    private Car(Name alias, Table<CarRecord> aliased) {
        this(alias, aliased, null);
    }

    private Car(Name alias, Table<CarRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Car(Table<O> child, ForeignKey<O, CarRecord> key) {
        super(child, key, CAR);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK_LOG_INDEX_2, Indexes.PRIMARY_KEY_2);
    }

    @Override
    public Identity<CarRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CAR;
    }

    @Override
    public UniqueKey<CarRecord> getPrimaryKey() {
        return Keys.PK_CAR;
    }

    @Override
    public List<UniqueKey<CarRecord>> getKeys() {
        return Arrays.<UniqueKey<CarRecord>>asList(Keys.PK_CAR);
    }

    @Override
    public List<ForeignKey<CarRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CarRecord, ?>>asList(Keys.FK_LOG);
    }

    public Log log() {
        return new Log(this, Keys.FK_LOG);
    }

    @Override
    public Car as(String alias) {
        return new Car(DSL.name(alias), this);
    }

    @Override
    public Car as(Name alias) {
        return new Car(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Car rename(String name) {
        return new Car(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Car rename(Name name) {
        return new Car(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Long, BigDecimal, Short, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}