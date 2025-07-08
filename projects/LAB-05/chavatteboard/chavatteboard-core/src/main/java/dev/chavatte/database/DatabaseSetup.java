package dev.chavatte.database;

public class DatabaseSetup {

    public static void createTables() {
        FlywayConfig.migrate();
    }
}