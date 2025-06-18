package sm.mobile.androidnavigation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDb : RoomDatabase() {
    abstract fun getDao(): TodoDao

    companion object { //method di dalam companion object = method static
        private var db: TodoDb? = null //db jadi instance

        fun getInstance(context: Context): TodoDb { //menciptakan object db dan memeriksa apakah ada koneksi
            val conn = db
            if (conn!= null) return conn
            synchronized(this) {
                val conn = Room.
                databaseBuilder(context.applicationContext,
                    TodoDb::class.java,
                    "todo-db" //nama database
                ).build()
                this.db = conn
                return conn
            }
        }
    }
}