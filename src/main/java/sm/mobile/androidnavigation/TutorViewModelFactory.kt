package sm.mobile.androidnavigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TutorViewModelFactory(
    private val repository: TutorRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TutorFormViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TutorFormViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}