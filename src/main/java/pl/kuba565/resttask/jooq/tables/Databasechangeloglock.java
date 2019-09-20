/*
 * This file is generated by jOOQ.
 */
package pl.kuba565.resttask.jooq.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import pl.kuba565.resttask.jooq.Indexes;
import pl.kuba565.resttask.jooq.Keys;
import pl.kuba565.resttask.jooq.Public;
import pl.kuba565.resttask.jooq.tables.records.DatabasechangeloglockRecord;


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
public class Databasechangeloglock extends TableImpl<DatabasechangeloglockRecord> {

    private static final long serialVersionUID = -671596915;

    /**
     * The reference instance of <code>PUBLIC.DATABASECHANGELOGLOCK</code>
     */
    public static final Databasechangeloglock DATABASECHANGELOGLOCK = new Databasechangeloglock();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DatabasechangeloglockRecord> getRecordType() {
        return DatabasechangeloglockRecord.class;
    }

    /**
     * The column <code>PUBLIC.DATABASECHANGELOGLOCK.ID</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Integer> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Boolean> LOCKED = createField(DSL.name("LOCKED"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Timestamp> LOCKGRANTED = createField(DSL.name("LOCKGRANTED"), org.jooq.impl.SQLDataType.TIMESTAMP.precision(6), this, "");

    /**
     * The column <code>PUBLIC.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    public final TableField<DatabasechangeloglockRecord, String> LOCKEDBY = createField(DSL.name("LOCKEDBY"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>PUBLIC.DATABASECHANGELOGLOCK</code> table reference
     */
    public Databasechangeloglock() {
        this(DSL.name("DATABASECHANGELOGLOCK"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.DATABASECHANGELOGLOCK</code> table reference
     */
    public Databasechangeloglock(String alias) {
        this(DSL.name(alias), DATABASECHANGELOGLOCK);
    }

    /**
     * Create an aliased <code>PUBLIC.DATABASECHANGELOGLOCK</code> table reference
     */
    public Databasechangeloglock(Name alias) {
        this(alias, DATABASECHANGELOGLOCK);
    }

    private Databasechangeloglock(Name alias, Table<DatabasechangeloglockRecord> aliased) {
        this(alias, aliased, null);
    }

    private Databasechangeloglock(Name alias, Table<DatabasechangeloglockRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Databasechangeloglock(Table<O> child, ForeignKey<O, DatabasechangeloglockRecord> key) {
        super(child, key, DATABASECHANGELOGLOCK);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_D);
    }

    @Override
    public UniqueKey<DatabasechangeloglockRecord> getPrimaryKey() {
        return Keys.PK_DATABASECHANGELOGLOCK;
    }

    @Override
    public List<UniqueKey<DatabasechangeloglockRecord>> getKeys() {
        return Arrays.<UniqueKey<DatabasechangeloglockRecord>>asList(Keys.PK_DATABASECHANGELOGLOCK);
    }

    @Override
    public Databasechangeloglock as(String alias) {
        return new Databasechangeloglock(DSL.name(alias), this);
    }

    @Override
    public Databasechangeloglock as(Name alias) {
        return new Databasechangeloglock(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangeloglock rename(String name) {
        return new Databasechangeloglock(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangeloglock rename(Name name) {
        return new Databasechangeloglock(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Boolean, Timestamp, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}