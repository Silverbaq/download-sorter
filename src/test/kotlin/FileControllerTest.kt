import dk.w4.downloadsorter.controllers.FileController
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class FileControllerTest {

    lateinit var fileController: FileController
    private val filePath1 = "./testFile1.txt"
    private val filePath2 = "./testFile2.txt"


    @BeforeEach
    fun setup(){
        fileController = FileController()
    }

    @AfterEach
    fun teardown(){
        File(filePath1).delete()
        File(filePath2).delete()
    }

    @Test
    fun `Can move a file`(){
        File(filePath1).createNewFile()

        fileController.copyFile(File(filePath1), filePath2)

        val result = File(filePath2).exists()
        assertEquals(true, result)
    }
}