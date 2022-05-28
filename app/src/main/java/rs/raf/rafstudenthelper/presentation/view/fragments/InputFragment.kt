package rs.raf.rafstudenthelper.presentation.view.fragments

import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.rafstudenthelper.R
import rs.raf.rafstudenthelper.databinding.FragmentInputBinding
import rs.raf.rafstudenthelper.presentation.contract.MainContract
import rs.raf.rafstudenthelper.presentation.view.states.AddCourseState
import rs.raf.rafstudenthelper.presentation.viewmodel.MainViewModel
import java.lang.Long
import java.util.*

class InputFragment : Fragment(R.layout.fragment_input) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentInputBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
//        binding.addBtn.setOnClickListener {
//            val input = binding.inputEt.text.toString()
//            if (input.isBlank()) {
//                Toast.makeText(context, "Input cannot be empty!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            binding.inputEt.text.clear()
//
//            val temp: UUID = UUID.randomUUID()
//
//            val movieToAdd = Movie(
//                id = Long.toHexString(temp.mostSignificantBits)
//                        + Long.toHexString(temp.leastSignificantBits),
//                title = input
//            )
//            mainViewModel.addMovie(movieToAdd)
//        }
    }

    private fun initObservers() {
        mainViewModel.addDone.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: AddCourseState) {
        when(state) {
            is AddCourseState.Success -> Toast.makeText(context, "Movie added", Toast.LENGTH_SHORT)
                .show()
            is AddCourseState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}