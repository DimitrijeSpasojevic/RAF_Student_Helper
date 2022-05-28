package rs.raf.rafstudenthelper.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.rafstudenthelper.data.datasources.local.CourseDataBase
import rs.raf.rafstudenthelper.data.datasources.remote.CourseService
import rs.raf.rafstudenthelper.data.repositories.CourseRepository
import rs.raf.rafstudenthelper.data.repositories.CourseRepositoryImpl
import rs.raf.rafstudenthelper.presentation.viewmodel.MainViewModel

val movieModule = module {

    viewModel { MainViewModel(courseRepository = get()) }

    single<CourseRepository> { CourseRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<CourseDataBase>().getCourseDao() }

    single<CourseService> { create(retrofit = get()) }

}

