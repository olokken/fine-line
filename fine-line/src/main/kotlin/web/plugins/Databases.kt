package web.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import repository.configurations.GroupTable
import io.github.cdimascio.dotenv.dotenv

fun Application.configureDatabases() {
    val dotenv = dotenv {}

    val dbUrl = dotenv["DATABASE_URL"]
    val dbUsername = dotenv["DATABASE_USERNAME"]
    val dbPassword = dotenv["DATABASE_PASSWORD"]

    Database.connect(
        url = dbUrl,
        user = dbUsername,
        password = dbPassword,
    )

    transaction {
        exec("CREATE SCHEMA IF NOT EXISTS app_data")
    }

    transaction {
        Schema("fine_line").let { schema ->
            SchemaUtils.createMissingTablesAndColumns(GroupTable)
        }
    }
}
