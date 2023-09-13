package com.example.dbmigrator;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.flywaydb.core.api.output.MigrateResult;

import javax.sql.DataSource;

public class Migrator {

    private final DataSource dataSource;

    public Migrator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MigrateResult migrateToLatest() {
        Configuration configuration =
                new FluentConfiguration()
                        .baselineOnMigrate(true)
                        .dataSource(dataSource);

        return new Flyway(configuration).migrate();
    }

    public MigrateResult migrateUpTo(int version) {
        Configuration configuration =
                new FluentConfiguration()
                        .baselineOnMigrate(true)
                        .target(MigrationVersion.fromVersion(String.valueOf(version)))
                        .dataSource(dataSource);

        return new Flyway(configuration).migrate();
    }
}
