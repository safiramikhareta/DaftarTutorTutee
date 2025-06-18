package sm.mobile.androidnavigation

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tutors")
data class Tutor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val age: String,
    val major: String,
    val skill: String,
    val experience: String,
    val cvUploaded: Uri? = null
)