package jp.honorable.mention.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CityAdapter(context: Context) : BaseAdapter() {
    private val mLayoutInflater: LayoutInflater
    var cityList = ArrayList<City>()

    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: mLayoutInflater.inflate(android.R.layout.simple_list_item_1, null)

        val textView = view.findViewById<TextView>(android.R.id.text1)

        textView.text = cityList[position].cityName

        return  view
    }

    override fun getItem(position: Int): Any {
        return  cityList[position]
    }

    override fun getItemId(position: Int): Long {
        return cityList[position].id.toLong()
    }

    override fun getCount(): Int {
        return cityList.size
    }
}