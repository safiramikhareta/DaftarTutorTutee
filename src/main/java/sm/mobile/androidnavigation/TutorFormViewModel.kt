package sm.mobile.androidnavigation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TutorFormViewModel(private val repository: TutorRepository) : ViewModel() {

    private val _formState = MutableStateFlow(TutorFormState())
    val formState: StateFlow<TutorFormState> = _formState

    val allTutors: StateFlow<List<Tutor>> = repository.getAllTutors()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateField(field: String, value: String) {
        _formState.value = when (field) {
            "name" -> _formState.value.copy(name = value)
            "email" -> _formState.value.copy(email = value)
            "age" -> _formState.value.copy(age = value)
            "major" -> _formState.value.copy(major = value)
            "skill" -> _formState.value.copy(skill = value)
            "experience" -> _formState.value.copy(experience = value)
            else -> _formState.value
        }
    }

    fun setCvUri(uri: Uri) {
        _formState.value = _formState.value.copy(cvUploaded = uri)
    }

    fun saveTutor() {
        val state = _formState.value
        val tutor = Tutor(
            name = state.name,
            email = state.email,
            age = state.age,
            major = state.major,
            skill = state.skill,
            experience = state.experience,
            cvUploaded = state.cvUploaded
        )
        viewModelScope.launch {
            repository.insertTutor(tutor)
        }
    }
}

data class TutorFormState(
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val major: String = "",
    val skill: String = "",
    val experience: String = "",
    val cvUploaded: Uri? = null
)