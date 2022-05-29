package rs.raf.rafstudenthelper.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.datasources.local.NoteDao
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteEntity
import rs.raf.rafstudenthelper.data.models.NoteWithId

class NoteRepositoryImpl(
    private val localDataSource: NoteDao
) : NoteRepository {

    override fun getAll(): Observable<List<NoteWithId>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    NoteWithId(it.id,it.title,it.content,it.isArchived)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<NoteWithId>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    NoteWithId(it.id,it.title,it.content, it.isArchived)
                }
            }
    }

    override fun insert(note: Note): Completable {
        val noteEntity = NoteEntity(title = note.title, content = note.content, isArchived = false)
        return localDataSource.insert(noteEntity)
    }

    override fun deleteNote(note: NoteEntity): Completable {
        return localDataSource.deleteNote(note)

    }

    override fun updateNote(note: NoteEntity): Completable {
        return localDataSource.update(note)
    }
}