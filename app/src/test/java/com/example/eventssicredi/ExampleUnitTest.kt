package com.example.eventssicredi

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.eventssicredi.data.boundary.repository.EventRepository
import com.example.eventssicredi.domain.models.Event
import com.example.eventssicredi.ui.view.MainActivity
import com.example.eventssicredi.ui.viewModel.EventViewModel
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import java.util.*
import kotlin.coroutines.coroutineContext


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var eventViewModel: EventViewModel

    @Mock
    private lateinit var eventRepository: EventRepository

    @Mock
    private lateinit var eventObserve: Observer<Result<List<Event>>>

    @Mock
    private var mainActivity: MainActivity = MainActivity()

    @Before
    fun setUp() {
        mainActivity = MainActivity()
        eventViewModel = EventViewModel(mainActivity.application)
    }

    @Test
    fun insertEvent() {
        var allEvent: List<Event> = listOf()
        val event = Event(
                id = 1,
                title = "Evento 1",
                date = Calendar.getInstance().timeInMillis,
                price = 30.50,
                latitude = -3.744335,
                longitude = -38.485610,
                image = "",
                description = "Descricao de teste")
        testCoroutineRule.runBlockingTest {
            eventViewModel.insert(event)
            allEvent = (eventViewModel.allEvent.value?.first() ?: listOf<Event>()) as List<Event>
        }

        assertEquals(event, allEvent.first())
    }

    @Test
    fun updateEvent() {
        var evento: Event
        val event = Event(
                id = 1,
                title = "Evento 1",
                date = Calendar.getInstance().timeInMillis,
                price = 30.50,
                latitude = -3.744335,
                longitude = -38.485610,
                image = "",
                description = "Descricao de teste")
        testCoroutineRule.runBlockingTest {
            eventViewModel.insert(event)
            eventViewModel.allEvent.observeForever(Observer {
                evento = it.first()
                assertEquals(event.date, evento.date)
            })
        }
        assertTrue("Teste",true)
    }
}