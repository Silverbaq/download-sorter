import dk.w4.downloadsorter.controllers.DBController
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

class DBControllerTest {

    lateinit var dbController: DBController

    @BeforeEach
    fun setup(){
        dbController = DBController("test.db")
    }

    @AfterEach
    fun teardown(){

    }


    @Test
    fun `Hello world test`(){
        val text = "Hello, world!"
        assertEquals("Hello, world!", text)
    }

    @Test
    fun `Can insert into database`(){
        val file = File("./DBControllerTest.kt")

        val size1 = dbController.countFiles()

        dbController.insertFile(file)

        val size2 = dbController.countFiles()

        assertEquals(size1 +1, size2)
    }

    @Test
    fun `Can find files that have been seen before`(){
        val file = File("./DBControllerTest.kt")
        val result = dbController.hasSeenBefore(file)
        assertEquals(true, result)
    }
}