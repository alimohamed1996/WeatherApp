package com.waether.app.features.randomizer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.waether.app.R
import com.waether.app.core.ViewById
import kotlinx.android.synthetic.main.activity_randomizer.*

@ViewById(R.layout.activity_randomizer)
class RandomizerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_randomizer)
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(RandomizerViewModel::class.java)
        viewModel.numberLiveData.observe(this,
            Observer { random_number_textView.text = it.toString() }
            )

        button.setOnClickListener {
            viewModel.increment()
        }
        task_button.setOnClickListener {
            viewModel.stateSwitch()
        }
        viewModel.stateLiveData.observe(this,
            Observer {
                val text = if (it == true) "Clicked" else "UnClicked"
                task_button.text = text
            })
    }
}