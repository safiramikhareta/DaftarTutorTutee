package sm.mobile.androidnavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: TutorFormViewModel,
    onUploadCv: () -> Unit
) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListTutorScreen(
                tutorList = viewModel.allTutors.collectAsState().value,
                onDaftarClick = {
                    navController.navigate("form")
                }
            )
        }

        composable("form") {
            RegistrasiTutor(
                viewModel = viewModel,
                onSubmitSuccess = {
                    navController.navigate("success") {
                        popUpTo("form") { inclusive = true }
                    }
                },
                onUploadCv = {
                    onUploadCv() // handled via activity launcher from MainActivity
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("success") {
            RegistrasiSukses(
                onBack = {
                    navController.navigate("list") {
                        popUpTo("success") { inclusive = true }
                    }
                }
            )
        }
    }
}