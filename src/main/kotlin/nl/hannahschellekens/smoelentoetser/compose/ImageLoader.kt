package nl.hannahschellekens.smoelentoetser.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import org.jetbrains.skia.Image
import java.io.File

fun File.readImage(): ImageBitmap {
    return Image.makeFromEncoded(readBytes()).toComposeImageBitmap()
}

// https://github.com/JetBrains/compose-multiplatform-core/pull/1457
@Composable
internal fun painterResource(
    resourcePath: String
): Painter = when (resourcePath.substringAfterLast(".")) {
    else -> rememberBitmapResource(resourcePath)
}

// https://github.com/JetBrains/compose-multiplatform-core/pull/1457
@Composable
internal fun rememberBitmapResource(path: String): Painter {
    return remember(path) { BitmapPainter(readResourceBytes(path)!!.decodeToImageBitmap()) }
}

// https://github.com/JetBrains/compose-multiplatform-core/pull/1457
private object ResourceLoader
private fun readResourceBytes(resourcePath: String) =
    ResourceLoader.javaClass.classLoader.getResourceAsStream(resourcePath)?.readAllBytes()