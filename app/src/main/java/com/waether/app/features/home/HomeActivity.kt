package com.waether.app.features.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.waether.app.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}


class HomeFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    private val disposables = CompositeDisposable()

    private val showButtonReciever = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"Pressed",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchProgress.observe(this, Observer {
            search_progress_bar.visibility = if (it!!) View.VISIBLE else View.GONE
        })

        viewModel.cityResult.observe(this, Observer {
            Toast.makeText(activity, "result size = ${it?.size}", Toast.LENGTH_LONG).show()
        })

        search_button.setOnClickListener {
            viewModel.searchBottonClick(editText.text?.toString())
        }

        recyclerview_search_result.layoutManager = LinearLayoutManager(context)
        recyclerview_search_result.adapter = CitySearchResultAdapter(this,viewModel.cityResult)

        activity?.registerReceiver(showButtonReciever, IntentFilter(ACTION_SHOW_CITY_BUTTON_CLICKED))

    }
}
