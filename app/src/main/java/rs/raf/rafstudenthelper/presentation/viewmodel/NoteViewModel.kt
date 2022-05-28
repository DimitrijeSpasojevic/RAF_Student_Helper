package rs.raf.rafstudenthelper.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.repositories.NoteRepository
import rs.raf.rafstudenthelper.presentation.contract.NoteContract
import rs.raf.rafstudenthelper.presentation.view.states.AddCourseState
import rs.raf.rafstudenthelper.presentation.view.states.AddNoteState
import rs.raf.rafstudenthelper.presentation.view.states.CoursesState
import rs.raf.rafstudenthelper.presentation.view.states.NotesState
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class NoteViewModel (
    private val noteRepository: NoteRepository
) : ViewModel(), NoteContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val notesState: MutableLiveData<NotesState> = MutableLiveData()
    override val addDone: MutableLiveData<AddNoteState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
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
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllNotes() {
        val subscription = noteRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getNotesByName(name: String) {
        publishSubject.onNext(name)
    }

    override fun addNote(note: Note) {
        val subscription = noteRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddNoteState.Success
                },
                {
                    addDone.value = AddNoteState.Error("Error happened while adding movie")
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