import dk.w4.downloadsorter.VideoSorter
import dk.w4.downloadsorter.controllers.DBController
import dk.w4.downloadsorter.controllers.FileController

val dbController = DBController()
val fileController = FileController()

val videoSorter = VideoSorter(dbController, fileController)

fun main(args: Array<String>) {
    println("Starting sequence")
    val files = videoSorter.lookThroughFiles()
    println("Found files: " + files.size + " files")
    videoSorter.analyzeFiles(files)
    println("It has been done!!!")
}