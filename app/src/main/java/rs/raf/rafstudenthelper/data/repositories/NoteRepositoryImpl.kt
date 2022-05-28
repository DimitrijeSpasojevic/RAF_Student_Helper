package rs.raf.rafstudenthelper.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.datasources.local.NoteDao
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteEntity

class NoteRepositoryImpl(
    private val localDataSource: NoteDao
) : NoteRepository {

    override fun getAll(): Observable<List<Note>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Note(it.title,it.content)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Note>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    Note(it.title,it.content)
                }
            }
    }

    override fun insert(note: Note): Completable {
        val noteEntity = NoteEntity(title = note.title, content = note.content)
        return localDataSource.insert(noteEntity)
    }

}