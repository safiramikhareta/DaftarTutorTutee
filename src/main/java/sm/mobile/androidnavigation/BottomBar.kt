package sm.mobile.androidnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)

val bottomNavItems = listOf(
    BottomNavItem("Beranda", Icons.Default.Home, "home"),
    BottomNavItem("Belajar", Icons.Default.School, "learn"),
    BottomNavItem("Cari Tutor", Icons.Default.Search, "form"),
    BottomNavItem("Daftar Kelas", Icons.Default.List, "classes")
)

@Composable
fun BottomBar(selectedRoute: String, onItemClick: (String) -> Unit) {
    NavigationBar(
        containerColor = DeepBlue, // ⬅️ latar belakang biru tua
        tonalElevation = 0.dp // ⬅️ menghilangkan shadow
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = selectedRoute == item.route,
                onClick = { onItemClick(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = Color.White // ⬅️ ikon putih
                    )
                },
                label = {
                    Text(
                        item.label,
                        color = Color.White // ⬅️ teks putih
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                    indicatorColor = DeepBlue // ⬅️ hindari warna berubah saat dipilih
                )
            )
        }
    }
}