package com.example.dbmigrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBMigrationApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBMigrationApp.class);

    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        var migrateResult = new Migrator(EnvDataSourceProvider.getDataSource()).migrateToLatest();
        LOGGER.info(
                "Executed {} migrations in {}ms.",
                migrateResult.migrationsExecuted,
                System.currentTimeMillis() - start);
    }
}
