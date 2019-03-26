package com.waether.app.features.home

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.entities.City
import com.waether.app.R
import java.io.Serializable

const val ACTION_SHOW_CITY_BUTTON_CLICKED = "ACTION_SHOW_CITY_BUTTON_CLICKED"
const val EXTRA_CITY = "EXTRA_CITY"

class CitySearchResultViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        private val cityName by lazy { view.findViewById<TextView>(R.id.city_result_name_text_view) }
        private val showButton by lazy { view.findViewById<Button>(R.id.button_show) }
        fun bind(city: City){
            cityName.text = city.name ?: ""
            showButton.setOnClickListener {
                Intent(ACTION_SHOW_CITY_BUTTON_CLICKED)
                    .putExtra(EXTRA_CITY, city as Serializable)
                    .also { view.context.sendBroadcast(it) }
            }
        }

}

class CitySearchResultAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val cityResult: MutableLiveData<List<City>>
) :RecyclerView.Adapter<CitySearchResultViewHolder>(){

    init {
        cityResult.observe(lifecycleOwner, Observer {
            notifyDataSetChanged()
        })
    }
    override fun onCreateViewHolder(parentView: ViewGroup, p1: Int): CitySearchResultViewHolder {
        return LayoutInflater
            .from(parentView.context)
            .inflate(R.layout.view_city_search_result,parentView,false)
            .let { CitySearchResultViewHolder(it) }
    }

    override fun getItemCount() = cityResult.value?.size ?: 0


    override fun onBindViewHolder(viewHolder: CitySearchResultViewHolder, position: Int) {
        viewHolder.bind(cityResult.value!![position])
    }
}