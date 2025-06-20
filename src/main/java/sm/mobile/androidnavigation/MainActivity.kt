package sm.mobile.androidnavigation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import sm.mobile.androidnavigation.ui.theme.AndroidNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = AppDatabase.getDatabase(applicationContext).tutorDao()
        val repository = TutorRepository(dao)
        val factory = TutorViewModelFactory(repository)

        setContent {
            AndroidNavigationTheme {
                val viewModel: TutorFormViewModel = ViewModelProvider(
                    this,
                    factory
                )[TutorFormViewModel::class.java]

                val navController = rememberNavController()

                val cvLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    uri?.let {
                        viewModel.setCvUri(it)
                    }
                }

                AppNavGraph(
                    navController = navController,
                    viewModel = viewModel,
                    onUploadCv = { cvLauncher.launch("application/pdf") }
                )
            }
        }
    }
}