package com.gdg.android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gdg.android.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val pretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val pretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
val pretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))
val pretendardMedium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium))
val kjcBold = FontFamily(Font(R.font.kimjungchulmyungjo_bold, FontWeight.Bold))
val kjcRegular = FontFamily(Font(R.font.kimjungchulmyungjo_regular, FontWeight.Medium))
val kjcLight = FontFamily(Font(R.font.kimjungchulmyungjo_light, FontWeight.Light))
// Button
val button1Bold = TextStyle(
    fontFamily = kjcBold,
    fontSize = 16.sp,
)
val button2Bold = TextStyle(
    fontFamily = kjcBold,
    fontSize = 14.sp,
)
val button3Bold = TextStyle(
    fontFamily = kjcBold,
    fontSize = 11.sp,
)

val text1Light = TextStyle(
    fontFamily = kjcLight,
    fontSize = 16.sp
)
val text2Light = TextStyle(
    fontFamily = kjcLight,
    fontSize = 14.sp
)
val text3Lightm = TextStyle(
    fontFamily = kjcLight,
    fontSize = 10.sp
)

val text1Regular = TextStyle(
    fontFamily = kjcRegular,
    fontSize = 16.sp
)
val text2Regular = TextStyle(
    fontFamily = kjcRegular,
    fontSize = 14.sp
)
val text3Regular = TextStyle(
    fontFamily = kjcRegular,
    fontSize = 10.sp
)