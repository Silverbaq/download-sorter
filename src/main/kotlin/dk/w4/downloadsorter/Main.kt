package dk.w4.downloadsorter

import dk.w4.downloadsorter.controllers.DBController
import dk.w4.downloadsorter.controllers.FileController
import java.lang.Thread.sleep

val dbController = DBController()
val fileController = FileController()

val videoSorter = VideoSorter(dbController, fileController)

fun main(args: Array<String>) {
    while (true) {
        println("Starting sequence")
        val files = videoSorter.lookThroughFiles()
        println("Found files: " + files.size + " files")
        videoSorter.analyzeFiles(files)
        println("It has been done!!!")
        println("Sleeping for 5 minutes.")
        sleep(1000 * 60 * 5)
    }
}