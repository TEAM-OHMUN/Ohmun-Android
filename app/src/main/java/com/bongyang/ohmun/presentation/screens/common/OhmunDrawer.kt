package com.bongyang.ohmun.presentation.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bongyang.ohmun.R
import com.bongyang.ohmun.ui.theme.OhmunTheme

private val screens = listOf("책", "음악", "영화 & 드라마", "프로필")

@Composable
fun OhmunDrawer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_ohmun_drawer),
            contentDescription = stringResource(R.string.cd_drawer)
        )
        Spacer(modifier = Modifier.height(8.dp))
        for (screen in screens) {
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Preview
@Composable
fun CraneDrawerPreview() {
    OhmunTheme {
        OhmunDrawer()
    }
}