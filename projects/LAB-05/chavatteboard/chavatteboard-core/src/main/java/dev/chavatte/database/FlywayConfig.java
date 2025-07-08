package dev.chavatte.database;

import org.flywaydb.core.Flyway;

public class FlywayConfig {

  public static void migrate() {
    Flyway flyway = Flyway.configure()
        .dataSource(Database.getDbUrl(), null, null)
        .locations("db/migration")
        .baselineOnMigrate(true)
        .load();

    flyway.migrate();
  }
}