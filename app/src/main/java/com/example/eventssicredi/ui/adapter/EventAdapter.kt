package com.example.eventssicredi.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventssicredi.ui.view.EventDetalheActivity
import com.example.eventssicredi.R
import com.example.eventssicredi.extension.loadImage
import com.example.eventssicredi.domain.models.Event
import java.text.SimpleDateFormat
import java.util.*

class EventAdapter(
    private val contexto: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var lista: List<Event> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(contexto).inflate(R.layout.recycler_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val eventViewHolder = holder as EventViewHolder
        eventViewHolder.title.text = lista[position].title
        eventViewHolder.price.text = lista[position].price.toString()
        eventViewHolder.description.text = lista[position].description
        eventViewHolder.image.loadImage(lista[position].image)
        eventViewHolder.date.text = dataToText(lista[position].date)
        eventViewHolder.card.setOnClickListener {
            contexto.startActivity(
                Intent(
                    contexto,
                    EventDetalheActivity::class.java
                ).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    putExtra("event", lista[position])
            })
        }
    }

    override fun getItemCount(): Int {
        return lista.count()
    }

    inner class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var card: CardView = view.findViewById(R.id.cardEvent)
        var title: TextView = view.findViewById(R.id.titleEvent)
        var price: TextView = view.findViewById(R.id.priceEvent)
        var description: TextView = view.findViewById(R.id.descriptionEvent)
        var image: ImageView = view.findViewById(R.id.imagEvent)
        var date: TextView = view.findViewById(R.id.dateEvent)
    }

    fun setEvents(events: List<Event>) {
        this.lista = events
        notifyDataSetChanged()
    }

    fun dataToText(time: Long): String {
        val date = Calendar.getInstance()
        date.timeInMillis = time
        val formatador = SimpleDateFormat("dd/MM/yyyy")
        val dataFormatada = formatador.format(date.time)
        return dataFormatada
    }
}