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
        val strList = arrayOf("札幌","仙台","さいたま","千葉","東京","横浜", "新潟", "静岡", "名古屋", "京都", "大阪", "神戸", "岡山", "広島", "福岡", "熊本")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("追加したい都市を選択")
            .setItems(strList, {dialog, which ->
                when(which){
                    0 -> AddCityToCityList(0,"016010", strList[0])
                    1 -> AddCityToCityList(1,"040010", strList[1])
                    2 -> AddCityToCityList(2,"110010", strList[2])
                    3 -> AddCityToCityList(3,"120010", strList[3])
                    4 -> AddCityToCityList(4,"130010", strList[4])
                    5 -> AddCityToCityList(5,"140010", strList[5])
                    6 -> AddCityToCityList(6,"150010", strList[6])
                    7 -> AddCityToCityList(7,"220010", strList[7])
                    8 -> AddCityToCityList(8,"230010", strList[8])
                    9 -> AddCityToCityList(9,"260010", strList[9])
                    10 -> AddCityToCityList(10,"270000", strList[10])
                    11 -> AddCityToCityList(11,"280010", strList[11])
                    12 -> AddCityToCityList(12,"330010", strList[12])
                    13 -> AddCityToCityList(13,"340010", strList[13])
                    14 -> AddCityToCityList(14,"400010", strList[14])
                    15 -> AddCityToCityList(15,"430010", strList[15])
                }

            }).show()

        return  true
    }

    private fun AddCityToCityList(id : Int, cityID :String, cityName :String)
    {
        val city = City()
        city.id = id
        city.cityID = "city=" + cityID
        city.cityName = cityName
        mCityAdapter.cityList.add(city)
        mCityAdapter.notifyDataSetChanged()
    }
}
