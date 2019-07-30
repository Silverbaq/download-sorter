package dk.w4.downloadsorter.controllers

import dk.w4.downloadsorter.model.MyFile
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

class DBController(private val dbName: String = "files.db") {

    init {
        connectToDB()
        transaction {
            SchemaUtils.create(MyFile)
        }
    }

    fun connectToDB() {
        Database.connect("jdbc:sqlite:$dbName", driver = "org.sqlite.JDBC")
    }

    fun insertFile(file: File) {
        connectToDB()
        transaction {
            MyFile.insert {
                it[name] = file.name
                it[location] = file.canonicalPath
            }
        }
    }

    fun hasSeenBefore(file: File): Boolean {
        connectToDB()
        return transaction {
            var pressent = false
            for (f in MyFile.selectAll()) {
                if (f[MyFile.name] == file.name)
                    pressent = true
            }
            return@transaction pressent
        }
    }

    fun countFiles(): Int {
        connectToDB()
        return transaction {
            return@transaction MyFile.selectAll().count()

        }
    }

    companion object {
        val INSTANCE = DBController()
    }
}