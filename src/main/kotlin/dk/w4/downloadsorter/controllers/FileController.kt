package dk.w4.downloadsorter.controllers

import java.io.File

class FileController{

    fun copyFile(file: File, to: String): File {
        println("[+] Copy file: " + file.name + "\t To: " + to)
        val toFile = File("" + to + '/'.toString() + file.name)
        file.copyTo(File(to))
        println("[*] " + file.name + " has been copied")
        return toFile
    }
}