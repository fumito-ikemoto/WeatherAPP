package jp.honorable.mention.weatherapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import io.realm.Realm
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRealm : Realm
    private lateinit var mCityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
        }

        mCityAdapter = CityAdapter(this@MainActivity)

        cityList.setOnItemClickListener { parent, view, position, id ->
            var city = parent.adapter.getItem(position) as City
            val intent = Intent(this@MainActivity, WeatherDetailActivity::class.java)
            intent.putExtra(CITYNAME, city.cityName)
            intent.putExtra(CITYID, city.cityID)
            startActivity(intent)
        }

        reloadListView()
    }

    private fun reloadListView()
    {
        val tokyo = City()
        tokyo.cityName = "東京"
        tokyo.cityID = "city=130010"
        tokyo.id = 1

        mCityAdapter.cityList.clear()
        mCityAdapter.cityList.add(tokyo)
        cityList.adapter = mCityAdapter

        mCityAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
