package com.example.eventssicredi.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventssicredi.R
import com.example.eventssicredi.extension.dateToString
import com.example.eventssicredi.extension.loadImage
import com.example.eventssicredi.domain.models.Event
import kotlinx.android.synthetic.main.activity_event_detalhe.*

class EventDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detalhe)

        val event = intent.getSerializableExtra("event") as Event

        imagEvent.loadImage(event.image)
        titleEvent.text = event.title
        textPrice.text = "R$ ${event.price}"
        textDate.text = event.date.dateToString()
        descripEvent.text = event.description

//        mapaEvent.onCreate(savedInstanceState)
//        mapaEvent.getMapAsync { googleMap ->
//            val localicao = LatLng(event.latitude, event.longitude)
//            googleMap?.addMarker(MarkerOptions().position(localicao).title("Local Event"))
//            googleMap?.moveCamera(CameraUpdateFactory.newLatLng(localicao))
//        }

        checkin.setOnClickListener {

        }

        sharedEvent.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, event.image)
                type = "text/html"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}