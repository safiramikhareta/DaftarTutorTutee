package sm.mobile.androidnavigation

class TutorRepository(private val tutorDao: TutorDao) {
    suspend fun insertTutor(tutor: Tutor) = tutorDao.insertTutor(tutor)
    fun getAllTutors() = tutorDao.getAllTutors()
}