@file:OptIn(ExperimentalMaterial3Api::class)

package sm.mobile.androidnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun RegistrasiTutor(
    viewModel: TutorFormViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onSubmitSuccess: () -> Unit,
    onUploadCv: () -> Unit,
    onBack: () -> Unit
) {
    val formState by viewModel.formState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Tutor", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = DeepBlue)
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { BottomBar(selectedRoute = "form", onItemClick = {}) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Yuk Daftar Jadi Tutor",
                color = DeepBlue,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.name,
                onValueChange = { viewModel.updateField("name", it) },
                label = { Text("Nama*") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = formState.email,
                onValueChange = { viewModel.updateField("email", it) },
                label = { Text("E-mail*") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = formState.age,
                onValueChange = { viewModel.updateField("age", it) },
                label = { Text("Umur*") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = formState.major,
                onValueChange = { viewModel.updateField("major", it) },
                label = { Text("Jurusan/Prodi*") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = formState.skill,
                onValueChange = { viewModel.updateField("skill", it) },
                label = { Text("Bidang Keahlian*") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = formState.experience,
                onValueChange = { viewModel.updateField("experience", it) },
                label = { Text("Pengalaman*") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.DarkGray, RoundedCornerShape(8.dp)) // // Ubah jadi dark gray
                    .clickable { onUploadCv() },
                contentAlignment = Alignment.Center
            ) {
                if (formState.cvUploaded != null) {
                    Text("CV sudah dipilih", color = Color.White)
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Upload,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text("Upload CV", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (
                        formState.name.isNotBlank() &&
                        formState.email.isNotBlank() &&
                        formState.age.isNotBlank() &&
                        formState.major.isNotBlank() &&
                        formState.skill.isNotBlank() &&
                        formState.experience.isNotBlank() &&
                        formState.cvUploaded != null
                    ) {
                        viewModel.saveTutor() // // Simpan ke database Room
                        onSubmitSuccess()
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Semua field wajib diisi dan CV harus diunggah!")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DeepBlue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Simpan", color = Color.White)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
