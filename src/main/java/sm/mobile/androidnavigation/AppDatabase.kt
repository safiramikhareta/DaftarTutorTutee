package sm.mobile.androidnavigation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Tutor::class], version = 1, exportSchema = false)
@TypeConverters(UriTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tutorDao(): TutorDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tutor_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}