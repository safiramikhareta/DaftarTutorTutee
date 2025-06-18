package sm.mobile.androidnavigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import sm.mobile.androidnavigation.ui.theme.Typography

private val LightColorScheme = lightColorScheme(
    primary = DeepBlue,            // warna utama (biru tua)
    onPrimary = Color.White,       // teks di atas primary
    background = Color.White,      // warna latar
    onBackground = Color.Black,    // teks di atas latar
    surface = LightGrayCard,       // latar permukaan seperti kartu
    onSurface = Color.Black        // teks di atas permukaan
)

@Composable
fun AndroidNavigationTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}