package jp.honorable.mention.weatherapp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

class City {
    var cityName : String = ""
    var cityID : String = ""

    @PrimaryKey
    var id : Int = 1
}