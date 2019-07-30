package dk.w4.downloadsorter.model

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime


object MyFile : Table() {
    val id = varchar("id", 10).primaryKey() // Column<String>
    val name = varchar("name", length = 50) // Column<String>
    val location= varchar("location", length = 500) // Column<String>
    var dateOfMovement = datetime("moved").default(DateTime.now())
}