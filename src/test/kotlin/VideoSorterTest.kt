import dk.w4.downloadsorter.VideoSorter
import dk.w4.downloadsorter.controllers.DBController
import dk.w4.downloadsorter.controllers.FileController
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class VideoSorterTest  {

    val dbController: DBController = mock(DBController::class.java)
    val fileController: FileController = mock(FileController::class.java)

    val videoSorter: VideoSorter = VideoSorter(dbController, fileController)

    @Test
    fun `Can provide a list of new files in a folder`(){
        //TODO: implement test
        assert(true)
    }

    @Test
    fun `Can tell is a video is a movie or tv-show`(){
        //TODO: implement test
        assert(true)
    }

    @Test
    fun `Can define a files category`(){
        //TODO: implement test
        assert(true)
    }


}

