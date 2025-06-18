package sm.mobile.androidnavigation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTutor(tutor: Tutor)

    @Query("SELECT * FROM tutors ORDER BY id DESC")
    fun getAllTutors(): Flow<List<Tutor>>
}