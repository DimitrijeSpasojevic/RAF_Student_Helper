package rs.raf.rafstudenthelper.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.datasources.local.CourseDao
import rs.raf.rafstudenthelper.data.datasources.remote.CourseService
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.CourseEntity
import rs.raf.rafstudenthelper.data.models.Resource
import timber.log.Timber

class CourseRepositoryImpl(
    private val localDataSource: CourseDao,
    private val remoteDataSource: CourseService
) : CourseRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.map {
                    CourseEntity(
                        predmet = it.predmet,
                        tip = it.tip,
                        nastavnik = it.nastavnik,
                        grupe = it.grupe,
                        dan = it.dan,
                        termin = it.termin,
                        ucionica = it.ucionica
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
        // Kada zelimo sami da kontrolisemo sta se desava sa greskom, umesto da je samo prepustimo
        // error handleru, mozemo iskoristiti operator onErrorReturn, tada sami na osnovu greske
        // odlucujemo koju vrednost cemo da vratimo. Ta vrednost mora biti u skladu sa povratnom
        // vrednoscu lanca.
        // Kada se iskoristi onErrorReturn onError lambda u viewmodelu nece biti izvrsena,
        // nego ce umesdto nje biti izvsena onNext koja ce kao parametar primiti vrednost koja
        // je vracena iz onErrorReturn
        // Obicno se koristi kada je potrebno proveriti koji kod greske je vratio server.
//            .onErrorReturn {
//                when(it) {
//                    is HttpException -> {
//                        when(it.code()) {
//                            in 400..499 -> {
//
//                            }
//                        }
//                    }
//                }
//                Timber.e("ON ERROR RETURN")
//            }
    }

    override fun getAllFiltered(
        name: String,
        group: String,
        day: String,
        professor: String
    ): Observable<List<Course>> {
        return localDataSource
            .getAllFiltered(name,group,day, professor)
            .map {
                it.map {
                    Course(it.predmet,it.tip,it.nastavnik,it.grupe,it.dan,it.termin,it.ucionica)
                }
            }
    }

    override fun getAll(): Observable<List<Course>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Course(it.predmet,it.tip,it.nastavnik,it.grupe,it.dan,it.termin,it.ucionica)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Course>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    Course(it.predmet,it.tip,it.nastavnik,it.grupe,it.dan,it.termin,it.ucionica)
                }
            }
    }

    override fun insert(course: Course): Completable {
        val courseEntity = CourseEntity(predmet = course.predmet, tip = course.tip,
        nastavnik = course.nastavnik, grupe = course.grupe, dan = course.dan, termin = course.termin,
        ucionica = course.ucionica)
        return localDataSource
            .insert(courseEntity)
    }

    override fun getAllGroups(): Observable<List<String>> {
        return localDataSource
            .getAllGroups()
    }

    override fun getAllDays(): Observable<List<String>> {
        return localDataSource
            .getAllDays()
    }

}