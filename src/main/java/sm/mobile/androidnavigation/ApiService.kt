package sm.mobile.androidnavigation

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("tutors")
    suspend fun registerTutor(@Body tutor: Tutor): Response<Void>
}