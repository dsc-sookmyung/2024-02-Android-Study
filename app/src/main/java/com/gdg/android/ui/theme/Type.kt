import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gdg.android.R

// Define font families
val pretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val pretendardLight = FontFamily(Font(R.font.pretendard_light, FontWeight.Light))
val pretendardMedium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium))

val bodyLarge = TextStyle(
        fontFamily = pretendardMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
val bodyMedium = TextStyle(
        fontFamily = pretendardLight,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )
val titleLarge = TextStyle(
        fontFamily = pretendardBold,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
val buttontext = TextStyle(
        fontFamily = pretendardBold,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 4.sp
    )
