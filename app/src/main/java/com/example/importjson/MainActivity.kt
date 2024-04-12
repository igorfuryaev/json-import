package com.example.importjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val jsonData = getJsonDataFromUrl(https://randomuser.me/api/?results=10)
        val jsonObject = JSONObject(jsonData)

        fun getJsonDataFromUrl(url: String): String {
            val connection = URL(url).openConnection()
            val reader = BufferedReader(InputStreamReader(connection.getInputStream()))
            val jsonData = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                jsonData.append(line)
            }
            reader.close()

            return jsonData.toString()
        }

    }
}