package com.example.eventssicredi.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.dateToString(): String {
    val date = Calendar.getInstance()
    date.timeInMillis = this
    val formatador = SimpleDateFormat("dd/MM/yyyy")
    val dataFormatada = formatador.format(date.time)
    return dataFormatada
}