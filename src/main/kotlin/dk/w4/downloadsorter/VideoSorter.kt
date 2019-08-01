package dk.w4.downloadsorter

import dk.w4.downloadsorter.controllers.DBController
import dk.w4.downloadsorter.controllers.FileController
import dk.w4.downloadsorter.model.Categorie
import java.io.File

class VideoSorter(
    private val dbController: DBController,
    private val fileController: FileController,
    private val inputPath: String = "./downloads",
    private val moviesOutput: String = "./movies/",
    private val showOutput: String = "./series/"
) {

    fun lookThroughFiles(): List<File> {
        val fileList = mutableListOf<File>()
        File(inputPath).walkTopDown().forEach {
            if (!dbController.hasSeenBefore(it)) {
                fileList.add(it)
            }
        }
        return fileList
    }

    fun analyzeFiles(files: List<File>) {
        files.map { file ->
            val name = file.name
            when (defineCategories(name)) {
                Categorie.VIDEO -> {
                    val name2 = file.name
                    dbController.insertFile(file)
                    if (isVideoATVShow(name2)) {
                        fileController.copyFile(file, showOutput + file.name)
                    } else {
                        fileController.copyFile(file, moviesOutput + file.name)
                    }
                }
                Categorie.MUSIC -> println("[-] " + file.name + " is skipped! (For now)")
                Categorie.OTHER -> {
                    println("[-] " + file.name + " is skipped!")
                    dbController.insertFile(file)
                }
            }
        }
    }

    fun defineCategories(name: String): Categorie {
        return if (".avi" in name || ".mp4" in name || ".mkv" in name) {
            Categorie.VIDEO
        } else if (".mp3" in name) {
            Categorie.MUSIC
        } else Categorie.OTHER
    }

    fun isVideoATVShow(name: String): Boolean {
        return Regex("[sS][0-9][0-9][eE][0-9]\\w+").containsMatchIn(name)
    }
}