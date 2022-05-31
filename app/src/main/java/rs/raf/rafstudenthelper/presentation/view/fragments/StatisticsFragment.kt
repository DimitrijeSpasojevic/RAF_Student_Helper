package rs.raf.rafstudenthelper.presentation.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.rafstudenthelper.presentation.contract.NoteContract
import rs.raf.rafstudenthelper.presentation.view.states.CoursesState
import rs.raf.rafstudenthelper.presentation.view.states.NotesStatisticsState
import rs.raf.rafstudenthelper.presentation.viewmodel.NoteViewModel
import java.sql.Date
import java.time.LocalDate


class StatisticsFragment : Fragment() {

    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            noteViewModel.notesStatisticsState.observe(viewLifecycleOwner, Observer {
                renderState(it)
                setContent {
                    showStatistics(it)
                }
            })

        }
    }

    override fun onResume() {
        super.onResume()
        noteViewModel.getNotesBetweenDate(Date.valueOf(LocalDate.now().minusDays(4).toString()),Date.valueOf(LocalDate.now().toString()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        noteViewModel.notesStatisticsState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: NotesStatisticsState) {
        when(state) {
            is NotesStatisticsState.Success -> {
                Toast.makeText(context, "Statistika update", Toast.LENGTH_SHORT)
                    .show()
            }
            is NotesStatisticsState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT)
                .show()
        }
    }


    @Composable
    fun showStatistics(state: NotesStatisticsState) {

        var hashMap : HashMap<Date, Int> = HashMap<Date, Int> ()

        hashMap.put(Date.valueOf(LocalDate.now().minusDays(4).toString()) , 0)
        hashMap.put(Date.valueOf(LocalDate.now().minusDays(3).toString()), 0)
        hashMap.put(Date.valueOf(LocalDate.now().minusDays(2).toString()) , 0)
        hashMap.put(Date.valueOf(LocalDate.now().minusDays(1).toString()), 0)
        hashMap.put(Date.valueOf(LocalDate.now().toString()) , 0)

        when(state){
            is NotesStatisticsState.Success ->{

                for(note in state.notes){
                    hashMap.put(note.date, hashMap.get(note.date)?.plus(1) ?: 333)
                }
//                Text(text = hashMap.toString())
            }
        }

        val sortedMap = hashMap.toSortedMap(compareBy { it })
        val d: List<Int> = sortedMap.values.toList()

        androidx.compose.foundation.Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawRect(
                color = Color.Blue,
                topLeft = Offset(
                    x = canvasWidth / 10,
                    y = canvasWidth - canvasHeight / (canvasHeight / ( d[0]* 30))
                ),
                size = Size(width = canvasWidth / 7, height = canvasHeight / (canvasHeight / (d[0] * 30)))
            )
            drawRect(
                color = Color.Blue,
                topLeft = Offset(
                    x = canvasWidth / 10 + canvasWidth / 7 + 20f,
                    y = canvasWidth - canvasHeight / (canvasHeight / (d[1] * 30))
                ),
                size = Size(width = canvasWidth / 7, height = canvasHeight / (canvasHeight / (d[1] * 30)))
            )
            drawRect(
                color = Color.Blue,
                topLeft = Offset(
                    x = canvasWidth / 10 + (canvasWidth / 7) * 2 + 20f * 2,
                    y = canvasWidth - canvasHeight / (canvasHeight / (d[2] * 30))
                ),
                size = Size(width = canvasWidth / 7, height =  canvasHeight / (canvasHeight / (d[2] * 30)))
            )
            drawRect(
                color = Color.Blue,
                topLeft = Offset(
                    x = canvasWidth / 10 + (canvasWidth / 7) * 3 + 20f * 3,
                    y = canvasWidth - canvasHeight / (canvasHeight / (d[3] * 30))
                ),
                size = Size(width = canvasWidth / 7, height =  canvasHeight / (canvasHeight / (d[3] * 30)))
            )
            drawRect(
                color = Color.Blue,
                topLeft = Offset(
                    canvasWidth / 10 + (canvasWidth / 7) * 4 + 20f * 4,
                    y = canvasWidth - canvasHeight / (canvasHeight / (d[4] * 30))
                ),
                size = Size(width = canvasWidth / 7, height =  canvasHeight / (canvasHeight / (d[4] * 30)))
            )

        }
    }
}