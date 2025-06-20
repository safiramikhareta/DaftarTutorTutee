package sm.mobile.androidnavigation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TutorRepository(private val tutorDao: TutorDao) {

    suspend fun insertTutorLocally(tutor: Tutor) {
        withContext(Dispatchers.IO) {
            tutorDao.insertTutor(tutor) // Simpan ke Room
        }
    }

    fun getAllTutors() = tutorDao.getAllTutors()

    suspend fun registerTutorToApiSuspend(tutor: Tutor): Response<Void> {
        return RetrofitClient.apiService.registerTutor(tutor)
    }
}