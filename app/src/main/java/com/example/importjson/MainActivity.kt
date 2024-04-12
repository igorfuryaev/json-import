import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.example.importjson.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Используем Kotlin Coroutines для выполнения сетевого запроса в фоновом потоке
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val jsonData = getJsonDataFromUrl("https://randomuser.me/api/?results=10")
                withContext(Dispatchers.Main) {
                    val jsonObject = JSONObject(jsonData)
                    // Обновляем пользовательский интерфейс с использованием полученных данных
                    // Например:
                    // textView.text = jsonObject.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getJsonDataFromUrl(url: String): String {
        val connection = URL(url).openConnection() as HttpURLConnection
        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        val jsonData = StringBuilder()

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            jsonData.append(line)
        }
        reader.close()

        return jsonData.toString()
    }
}