package sm.mobile.androidnavigation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collect
import sm.mobile.androidnavigation.ui.theme.AndroidNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = AppDatabase.getDatabase(applicationContext).tutorDao()
        val repository = TutorRepository(dao)
        val factory = TutorViewModelFactory(repository)

        setContent {
            AndroidNavigationTheme {
                val viewModel: TutorFormViewModel = ViewModelProvider(this, factory)[TutorFormViewModel::class.java]
                var currentScreen by remember { mutableStateOf("list") }

                val cvLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    uri?.let {
                        viewModel.setCvUri(it)
                    }
                }

                val tutorList by viewModel.allTutors.collectAsState(initial = emptyList())

                when (currentScreen) {
                    "list" -> ListTutorScreen(
                        tutorList = tutorList,
                        onDaftarClick = {
                            currentScreen = "form"
                        }
                    )

                    "form" -> RegistrasiTutor(
                        viewModel = viewModel,
                        onSubmitSuccess = {
                            currentScreen = "success"
                        },
                        onUploadCv = {
                            cvLauncher.launch("application/pdf")
                        },
                        onBack = {
                            currentScreen = "list"
                        }
                    )

                    "success" -> RegistrasiSukses(
                        onBack = {
                            currentScreen = "list"
                        }
                    )
                }
            }
        }
    }
}