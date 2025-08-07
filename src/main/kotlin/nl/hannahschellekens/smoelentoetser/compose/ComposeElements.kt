package nl.hannahschellekens.smoelentoetser.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun vspace(heightDp: Dp) {
    Spacer(Modifier.height(heightDp))
}

@Composable
fun hspace(widthDp: Dp) {
    Spacer(Modifier.width(widthDp))
}