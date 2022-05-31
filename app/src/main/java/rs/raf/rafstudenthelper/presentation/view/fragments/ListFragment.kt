package rs.raf.rafstudenthelper.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.rafstudenthelper.R
import rs.raf.rafstudenthelper.databinding.FragmentListBinding
import rs.raf.rafstudenthelper.modules.noteModule
import rs.raf.rafstudenthelper.presentation.contract.MainContract
import rs.raf.rafstudenthelper.presentation.view.recycler.adapter.MovieAdapter
import rs.raf.rafstudenthelper.presentation.view.states.CoursesState
import rs.raf.rafstudenthelper.presentation.viewmodel.MainViewModel
import timber.log.Timber

class ListFragment : Fragment(R.layout.fragment_list) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var spinnerIsSet: Boolean = false

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
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
        initRecycler()
        initListeners()
    }

    private fun initSpinner(state: CoursesState){
        val daysInSpinner = mutableSetOf<String>()
        val groupsInSpinner = mutableSetOf<String>()
        when (state) {
            is CoursesState.Success -> {
                spinnerIsSet = true
                daysInSpinner.add("")
                groupsInSpinner.add("")
                for (course in state.courses ){
                    daysInSpinner.add(course.dan)
                    groupsInSpinner.add(course.grupe)
                }
            }
            else -> Toast.makeText(context, "Greska sa spinnerom", Toast.LENGTH_SHORT).show()
        }

        val spGrupa = binding.spGrupa
        if (spGrupa != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, groupsInSpinner.toList()
            )
            spGrupa.adapter = adapter
        }
        val spDan = binding.spDan
        if (spDan != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, daysInSpinner.toList()
            )
            spDan.adapter = adapter
        }
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
        adapter = MovieAdapter()
        binding.listRv.adapter = adapter
    }

    private fun initListeners() {
//        binding.inputEt.doAfterTextChanged {
//            val filter = it.toString()
//            mainViewModel.getMoviesByName(filter)
//        }

        with(binding){
            traziBtn.setOnClickListener {

                val name = inputPredmet.text.toString()
                val group = spGrupa.selectedItem.toString()
                val day = spDan.selectedItem.toString()
                val professor = inputProfessor.text.toString()
                Toast.makeText(context, "Predmet: $name" + "professor: $professor" + "day: $day" + "group: $group", Toast.LENGTH_LONG).show()
                mainViewModel.getAllFiltered(name,group,day,professor)
            }
        }
    }

    private fun initObservers() {
        mainViewModel.coursesState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
            if(!spinnerIsSet)
                initSpinner(it)
        })



        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query
        mainViewModel.getAllCourses()

        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMovies()
        mainViewModel.fetchAllCourses()
        mainViewModel.getAllGroups()
        mainViewModel.getAllDays()
    }

    private fun renderState(state: CoursesState) {
        when (state) {
            is CoursesState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.courses)
            }
            is CoursesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is CoursesState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is CoursesState.Loading -> {
                showLoadingState(true)
                Log.e("Loadnig", "Desava se loadin state")
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.inputProfessor.isVisible = !loading
        binding.inputPredmet.isVisible = !loading
        binding.listRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}