package rs.raf.rafstudenthelper.presentation.viewmodel

import android.graphics.Movie
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Resource
import rs.raf.rafstudenthelper.data.repositories.CourseRepository
import rs.raf.rafstudenthelper.presentation.contract.MainContract
import rs.raf.rafstudenthelper.presentation.view.states.AddCourseState
import rs.raf.rafstudenthelper.presentation.view.states.CoursesState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val courseRepository: CourseRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val coursesState: MutableLiveData<CoursesState> = MutableLiveData()
    override val addDone: MutableLiveData<AddCourseState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                courseRepository
                    .getAllByName(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    coursesState.value = CoursesState.Success(it)
                },
                {
                    coursesState.value = CoursesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllCourses() {
        val subscription = courseRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> coursesState.value = CoursesState.Loading
                        is Resource.Success -> coursesState.value = CoursesState.DataFetched
                        is Resource.Error -> coursesState.value = CoursesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    coursesState.value = CoursesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllCourses() {
        val subscription = courseRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    coursesState.value = CoursesState.Success(it)
                },
                {
                    coursesState.value = CoursesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getCoursesByName(name: String) {
        publishSubject.onNext(name)
    }

    override fun addCourse(course: Course) {
        val subscription = courseRepository
            .insert(course)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddCourseState.Success
                },
                {
                    addDone.value = AddCourseState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}