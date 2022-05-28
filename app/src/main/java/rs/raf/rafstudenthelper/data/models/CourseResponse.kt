package rs.raf.rafstudenthelper.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseResponse (
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
)