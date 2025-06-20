@file:OptIn(ExperimentalMaterial3Api::class)

package sm.mobile.androidnavigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListTutorScreen(
    tutorList: List<Tutor>,
    onDaftarClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Daftar Tutor",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = DeepBlue
                )
            )
        },
        bottomBar = {
            BottomBar(selectedRoute = "form", onItemClick = {})
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Text(
                "List Tutor",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Button(
                onClick = onDaftarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DeepBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Yuk Daftar Tutor", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tutorList) { tutor ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = LightGrayCard),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Tutor Icon",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                tutor.name,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                tutor.skill,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}