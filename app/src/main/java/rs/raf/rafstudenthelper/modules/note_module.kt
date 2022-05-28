package rs.raf.rafstudenthelper.modules

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import rs.raf.rafstudenthelper.data.datasources.local.CourseDataBase
import rs.raf.rafstudenthelper.data.repositories.NoteRepository
import rs.raf.rafstudenthelper.data.repositories.NoteRepositoryImpl
import rs.raf.rafstudenthelper.presentation.viewmodel.NoteViewModel

val noteModule = module {

    viewModel{ NoteViewModel(noteRepository = get())}

    single<NoteRepository> { NoteRepositoryImpl(localDataSource = get()) }

    single { get<CourseDataBase>().getNoteDao() }
}