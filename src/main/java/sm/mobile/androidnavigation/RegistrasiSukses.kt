package sm.mobile.androidnavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistrasiSukses(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = "Daftar Tutor", onBackClick = onBack)
        },
        bottomBar = {
            BottomBar(selectedRoute = "form", onItemClick = {})
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = DeepBlue,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Sudah Terdaftar!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DeepBlue
            )
        }
    }
}