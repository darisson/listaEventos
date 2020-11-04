package com.example.eventssicredi.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventssicredi.R
import com.example.eventssicredi.ui.adapter.EventAdapter
import com.example.eventssicredi.domain.models.Event
import com.example.eventssicredi.ui.viewModel.EventViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listaPost: MutableList<Event> = mutableListOf()
    private lateinit var eventViewModel: EventViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val call = RetrofitInitializer().eventService().list()
//        call.enqueue(object : Callback<List<Event>?>{
//            override fun onResponse(call: Call<List<Event>?>, response: Response<List<Event>?>) {
//                response?.body()?.let {
//                    listaPost.clear()
//                    listaPost.addAll(it)
//                    val postAdapter = PostAdapter(applicationContext)
//                    recyclerView.adapter = postAdapter
//                    recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//                    Log.d("ASV", "Lista = ${it.size}")
//                    postAdapter.setEvents(it)
//                    it.forEach {
//                        Log.d("ASV", "Lista = $it")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<Event>?>, t: Throwable) {
//                Log.e("onFailure error", t.message!!)
//            }
//
//        })


        //Courtines
        val postAdapter = EventAdapter(applicationContext)

        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        eventViewModel.loadEvent()
        eventViewModel.allEvent.observe(this, Observer { events ->
            if (events.isNullOrEmpty()) {
                recyclerView.visibility = View.GONE
            } else {
                events?.let {
                    listaPost.clear()
                    listaPost.addAll(it)
                    postAdapter.setEvents(it)
                    recyclerView.adapter = postAdapter
                    recyclerView.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                }
            }

        })


    }

}