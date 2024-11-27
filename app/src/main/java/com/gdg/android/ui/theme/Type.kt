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
val pretendardThin = FontFamily(Font(R.font.pretendard_thin, FontWeight.Medium))

// Button
val button1Bold = TextStyle(
    fontFamily = pretendardBold,
    fontSize = 16.sp,
)
val button2Bold = TextStyle(
    fontFamily = pretendardBold,
    fontSize = 14.sp,
)
val button3Bold = TextStyle(
    fontFamily = pretendardBold,
    fontSize = 11.sp,
)
val button4Semi = TextStyle(
    fontFamily = pretendardSemiBold,
    fontSize = 12.sp,
)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
