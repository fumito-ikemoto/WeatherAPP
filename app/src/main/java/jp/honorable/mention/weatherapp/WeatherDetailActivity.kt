package jp.honorable.mention.weatherapp

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_weather_detail.*
import kotlinx.android.synthetic.main.weather_list.*
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
                val forecasts = jsonObj.getJSONArray("forecasts")

                val todayData = forecasts.getJSONObject(0)
                val tomorrowData = forecasts.getJSONObject(1)
                val dayAfterTomorrowData = forecasts.getJSONObject(2)

                val telop1 = todayData.getString("telop")
                val telop2 = tomorrowData.getString("telop")
                val telop3 = dayAfterTomorrowData.getString("telop")
                todayWeatherText.text = telop1
                tomorrowWeatherText.text = telop2
                dayAfterTommorowWeatherText.text = telop3
                titleTextView.text = title

                if(todayData.getJSONObject("temperature").isNull("max"))
                {
                    todayMaxCelsius.text = "不明"
                }else{
                    todayMaxCelsius.text = todayData.getJSONObject("temperature").getJSONObject("max").getString("celsius")
                }

                if(tomorrowData.getJSONObject("temperature").isNull("max"))
                {
                    tomorrowMaxCelsius.text = "不明"
                }else{
                    tomorrowMaxCelsius.text = tomorrowData.getJSONObject("temperature").getJSONObject("max").getString("celsius")
                }

                if(dayAfterTomorrowData.getJSONObject("temperature").isNull("max"))
                {
                    dayAfterTomorrowMaxCelsius.text = "不明"
                }else{
                    dayAfterTomorrowMaxCelsius.text = dayAfterTomorrowData.getJSONObject("temperature").getJSONObject("max").getString("celsius")
                }

                if(todayData.getJSONObject("temperature").isNull("min"))
                {
                    todayMinCelsius.text = "不明"
                }else{
                    todayMinCelsius.text = todayData.getJSONObject("temperature").getJSONObject("min").getString("celsius")
                }

                if(tomorrowData.getJSONObject("temperature").isNull("min"))
                {
                    tomorrowMinCelsius.text = "不明"
                }else{
                    tomorrowMinCelsius.text = tomorrowData.getJSONObject("temperature").getJSONObject("min").getString("celsius")
                }

                if(dayAfterTomorrowData.getJSONObject("temperature").isNull("min"))
                {
                    dayAfterMinCelsius.text = "不明"
                }else{
                    dayAfterMinCelsius.text = dayAfterTomorrowData.getJSONObject("temperature").getJSONObject("min").getString("celsius")
                }


            }
            catch(e:Exception)
            {

            }
        }

    }
}
