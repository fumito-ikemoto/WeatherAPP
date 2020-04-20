package jp.honorable.mention.weatherapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import android.content.DialogInterface
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var mRealm : Realm
    private lateinit var mCityAdapter: CityAdapter
    private lateinit var mArrayAdapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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
            R.id.action_settings -> OpenCitySelectDialog()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun OpenCitySelectDialog() : Boolean{
        val strList = arrayOf("札幌","東京","大阪", "福岡")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("追加したい都市を選択")
            .setItems(strList, {dialog, which ->
                when(which){
                    0 -> {
                        val hokkaido = City()
                        hokkaido.id = 1
                        hokkaido.cityName = "札幌"
                        hokkaido.cityID = "city=016010"
                        mCityAdapter.cityList.add(hokkaido)
                        mCityAdapter.notifyDataSetChanged()
                    }
                    1 -> {
                        val tokyo = City()
                        tokyo.cityName = "東京"
                        tokyo.cityID = "city=130010"
                        tokyo.id = 2
                        mCityAdapter.cityList.add(tokyo)
                        mCityAdapter.notifyDataSetChanged()
                    }
                    2 -> {
                        val osaka = City()
                        osaka.id = 3
                        osaka.cityID = "city=270000"
                        osaka.cityName = "大阪"
                        mCityAdapter.cityList.add(osaka)
                        mCityAdapter.notifyDataSetChanged()
                    }
                    3 -> {
                        val fukuoka = City()
                        fukuoka.id = 4
                        fukuoka.cityID = "city=400010"
                        fukuoka.cityName = "福岡"
                        mCityAdapter.cityList.add(fukuoka)
                        mCityAdapter.notifyDataSetChanged()
                    }
                }

            }).show()

        return  true
    }
}
