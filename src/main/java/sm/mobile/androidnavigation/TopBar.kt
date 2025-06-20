@file:OptIn(ExperimentalMaterial3Api::class)

package sm.mobile.androidnavigation

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(title: String, onBackClick: (() -> Unit)? = null) {
    TopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = DeepBlue // Biru tua
        ),
        scrollBehavior = null,
        modifier = androidx.compose.ui.Modifier.height(56.dp)
    )
}