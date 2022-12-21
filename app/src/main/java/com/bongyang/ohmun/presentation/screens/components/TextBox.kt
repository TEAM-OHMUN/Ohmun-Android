package com.bongyang.ohmun.presentation.screens.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bongyang.ohmun.ui.theme.*

@Composable
fun TextBox(
    text: String,
    borderColor: Color,
    textColor: Color
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.5.dp,
                shape = RoundedCornerShape(size = MEDIUM_PADDING),
                color = if (borderColor == Color.Black) Color.White else borderColor
            )
            .width(80.dp)
            .padding(all = EXTRA_SMALL_PADDING)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InfoBoxPreview() {
    TextBox(
        text = "드라마",
        borderColor = ModernBlue,
        textColor = MaterialTheme.colors.titleColor
    )
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun InfoBoxDarkPreview() {
    TextBox(
        text = "느와르",
        borderColor = ModernBlue,
        textColor = MaterialTheme.colors.titleColor
    )
}