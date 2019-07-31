import dk.w4.downloadsorter.VideoSorter
import dk.w4.downloadsorter.controllers.DBController
import dk.w4.downloadsorter.controllers.FileController
import dk.w4.downloadsorter.model.Categorie
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import java.io.File

class VideoSorterTest  {

    val dbController: DBController = mock(DBController::class.java)
    val fileController: FileController = mock(FileController::class.java)

    val videoSorter: VideoSorter = VideoSorter(dbController, fileController)

    @Test
    fun `Can provide a list of new files in a folder`(){
        val files = videoSorter.lookThroughFiles()
        assertEquals(5, files.size)
    }

    @Test
    fun `Can tell is a video is a movie or tv-show`(){
        val file1 = "something.mkv"
        val file2 = "something.mvk"
        val file3 = "somethingS04E02.mp4"
        val file4 = "somethings03e01.mp4"
        val file5 = "something-s3e1.mp4"

        val result1 = videoSorter.isVideoATVShow(file1)
        val result2 = videoSorter.isVideoATVShow(file2)
        val result3 = videoSorter.isVideoATVShow(file3)
        val result4 = videoSorter.isVideoATVShow(file4)
        val result5 = videoSorter.isVideoATVShow(file5)

        assertEquals(false, result1)
        assertEquals(false, result2)
        assertEquals(true, result3)
        assertEquals(true, result4)
        assertEquals(false, result5)
    }

    @Test
    fun `Can define a files category`(){
        val file1 = "something.mkv"
        val file2 = "something.mp3"
        val file3 = "something.mp4"
        val file4 = "something.txt"

        val result1 = videoSorter.defineCategories(file1)
        val result2 = videoSorter.defineCategories(file2)
        val result3 = videoSorter.defineCategories(file3)
        val result4 = videoSorter.defineCategories(file4)

        assertEquals(Categorie.VIDEO, result1)
        assertEquals(Categorie.MUSIC, result2)
        assertEquals(Categorie.VIDEO, result3)
        assertEquals(Categorie.OTHER, result4)
    }


}

