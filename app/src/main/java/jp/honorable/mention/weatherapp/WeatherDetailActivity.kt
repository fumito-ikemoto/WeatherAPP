package jp.honorable.mention.weatherapp

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_weather_detail.*
import org.json.JSONObject

class WeatherDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        val cityName = intent.getStringExtra(CITYNAME)
        val cityID = intent.getStringExtra(CITYID)

        weatherTask(cityID).execute()
    }

    inner class weatherTask(cityID : String): AsyncTask<String, Void, String>()
    {
        val cityID = cityID

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = java.net.URL(URL + cityID).readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try
            {
                val jsonObj = JSONObject(result)
                val title = jsonObj.getString("title")
                val forecast = jsonObj.getJSONArray("forecasts").getJSONObject(0)
                val dateLabel = forecast.getString("dateLabel")
                val telop = forecast.getString("telop")

                titleTextView.text = title
                dateTextView.text = dateLabel
                weatherTextView.text = telop
            }
            catch(e:Exception)
            {

            }
        }

    }
}
