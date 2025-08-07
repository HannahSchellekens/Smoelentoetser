package nl.hannahschellekens.smoelentoetser.compose

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * https://composeicons.com
 *
 * @author Hannah Schellekens
 */
object ComposeIcons {

    val exit: ImageVector
        get() {
            if (_Exit_to_app != null) return _Exit_to_app!!

            _Exit_to_app = ImageVector.Builder(
                name = "Exit_to_app",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(200f, 840f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(120f, 760f)
                    verticalLineToRelative(-160f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(160f)
                    horizontalLineToRelative(560f)
                    verticalLineToRelative(-560f)
                    horizontalLineTo(200f)
                    verticalLineToRelative(160f)
                    horizontalLineToRelative(-80f)
                    verticalLineToRelative(-160f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(200f, 120f)
                    horizontalLineToRelative(560f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(840f, 200f)
                    verticalLineToRelative(560f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(760f, 840f)
                    close()
                    moveToRelative(220f, -160f)
                    lineToRelative(-56f, -58f)
                    lineToRelative(102f, -102f)
                    horizontalLineTo(120f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(346f)
                    lineTo(364f, 338f)
                    lineToRelative(56f, -58f)
                    lineToRelative(200f, 200f)
                    close()
                }
            }.build()

            return _Exit_to_app!!
        }

    private var _Exit_to_app: ImageVector? = null


    val send: ImageVector
        get() {
            if (_Send != null) return _Send!!

            _Send = ImageVector.Builder(
                name = "Send",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(120f, 800f)
                    verticalLineToRelative(-640f)
                    lineToRelative(760f, 320f)
                    close()
                    moveToRelative(80f, -120f)
                    lineToRelative(474f, -200f)
                    lineToRelative(-474f, -200f)
                    verticalLineToRelative(140f)
                    lineToRelative(240f, 60f)
                    lineToRelative(-240f, 60f)
                    close()
                    moveToRelative(0f, 0f)
                    verticalLineToRelative(-400f)
                    close()
                }
            }.build()

            return _Send!!
        }

    private var _Send: ImageVector? = null



    val arrowBack: ImageVector
        get() {
            if (_Arrow_back != null) return _Arrow_back!!

            _Arrow_back = ImageVector.Builder(
                name = "Arrow_back",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveToRelative(313f, -440f)
                    lineToRelative(224f, 224f)
                    lineToRelative(-57f, 56f)
                    lineToRelative(-320f, -320f)
                    lineToRelative(320f, -320f)
                    lineToRelative(57f, 56f)
                    lineToRelative(-224f, 224f)
                    horizontalLineToRelative(487f)
                    verticalLineToRelative(80f)
                    close()
                }
            }.build()

            return _Arrow_back!!
        }

    private var _Arrow_back: ImageVector? = null




    val group: ImageVector
        get() {
            if (_Groups != null) return _Groups!!

            _Groups = ImageVector.Builder(
                name = "Groups",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(0f, 720f)
                    verticalLineToRelative(-63f)
                    quadToRelative(0f, -43f, 44f, -70f)
                    reflectiveQuadToRelative(116f, -27f)
                    quadToRelative(13f, 0f, 25f, 0.5f)
                    reflectiveQuadToRelative(23f, 2.5f)
                    quadToRelative(-14f, 21f, -21f, 44f)
                    reflectiveQuadToRelative(-7f, 48f)
                    verticalLineToRelative(65f)
                    close()
                    moveToRelative(240f, 0f)
                    verticalLineToRelative(-65f)
                    quadToRelative(0f, -32f, 17.5f, -58.5f)
                    reflectiveQuadTo(307f, 550f)
                    reflectiveQuadToRelative(76.5f, -30f)
                    reflectiveQuadToRelative(96.5f, -10f)
                    quadToRelative(53f, 0f, 97.5f, 10f)
                    reflectiveQuadToRelative(76.5f, 30f)
                    reflectiveQuadToRelative(49f, 46.5f)
                    reflectiveQuadToRelative(17f, 58.5f)
                    verticalLineToRelative(65f)
                    close()
                    moveToRelative(540f, 0f)
                    verticalLineToRelative(-65f)
                    quadToRelative(0f, -26f, -6.5f, -49f)
                    reflectiveQuadTo(754f, 563f)
                    quadToRelative(11f, -2f, 22.5f, -2.5f)
                    reflectiveQuadToRelative(23.5f, -0.5f)
                    quadToRelative(72f, 0f, 116f, 26.5f)
                    reflectiveQuadToRelative(44f, 70.5f)
                    verticalLineToRelative(63f)
                    close()
                    moveToRelative(-455f, -80f)
                    horizontalLineToRelative(311f)
                    quadToRelative(-10f, -20f, -55.5f, -35f)
                    reflectiveQuadTo(480f, 590f)
                    reflectiveQuadToRelative(-100.5f, 15f)
                    reflectiveQuadToRelative(-54.5f, 35f)
                    moveTo(160f, 520f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(80f, 440f)
                    quadToRelative(0f, -34f, 23.5f, -57f)
                    reflectiveQuadToRelative(56.5f, -23f)
                    quadToRelative(34f, 0f, 57f, 23f)
                    reflectiveQuadToRelative(23f, 57f)
                    quadToRelative(0f, 33f, -23f, 56.5f)
                    reflectiveQuadTo(160f, 520f)
                    moveToRelative(640f, 0f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(720f, 440f)
                    quadToRelative(0f, -34f, 23.5f, -57f)
                    reflectiveQuadToRelative(56.5f, -23f)
                    quadToRelative(34f, 0f, 57f, 23f)
                    reflectiveQuadToRelative(23f, 57f)
                    quadToRelative(0f, 33f, -23f, 56.5f)
                    reflectiveQuadTo(800f, 520f)
                    moveToRelative(-320f, -40f)
                    quadToRelative(-50f, 0f, -85f, -35f)
                    reflectiveQuadToRelative(-35f, -85f)
                    quadToRelative(0f, -51f, 35f, -85.5f)
                    reflectiveQuadToRelative(85f, -34.5f)
                    quadToRelative(51f, 0f, 85.5f, 34.5f)
                    reflectiveQuadTo(600f, 360f)
                    quadToRelative(0f, 50f, -34.5f, 85f)
                    reflectiveQuadTo(480f, 480f)
                    moveToRelative(0f, -80f)
                    quadToRelative(17f, 0f, 28.5f, -11.5f)
                    reflectiveQuadTo(520f, 360f)
                    reflectiveQuadToRelative(-11.5f, -28.5f)
                    reflectiveQuadTo(480f, 320f)
                    reflectiveQuadToRelative(-28.5f, 11.5f)
                    reflectiveQuadTo(440f, 360f)
                    reflectiveQuadToRelative(11.5f, 28.5f)
                    reflectiveQuadTo(480f, 400f)
                    moveToRelative(0f, -40f)
                }
            }.build()

            return _Groups!!
        }

    private var _Groups: ImageVector? = null



    val playArrow: ImageVector
        get() {
            if (_Play_arrow != null) return _Play_arrow!!

            _Play_arrow = ImageVector.Builder(
                name = "Play_arrow",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(320f, 760f)
                    verticalLineToRelative(-560f)
                    lineToRelative(440f, 280f)
                    close()
                    moveToRelative(80f, -146f)
                    lineToRelative(210f, -134f)
                    lineToRelative(-210f, -134f)
                    close()
                }
            }.build()

            return _Play_arrow!!
        }

    private var _Play_arrow: ImageVector? = null



    val arrowForward: ImageVector
        get() {
            if (_Arrow_forward != null) return _Arrow_forward!!

            _Arrow_forward = ImageVector.Builder(
                name = "Arrow_forward",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(647f, 520f)
                    horizontalLineTo(160f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(487f)
                    lineTo(423f, 216f)
                    lineToRelative(57f, -56f)
                    lineToRelative(320f, 320f)
                    lineToRelative(-320f, 320f)
                    lineToRelative(-57f, -56f)
                    close()
                }
            }.build()

            return _Arrow_forward!!
        }

    private var _Arrow_forward: ImageVector? = null

    val done: ImageVector
        get() {
            if (_Done != null) return _Done!!

            _Done = ImageVector.Builder(
                name = "Done",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(382f, 720f)
                    lineTo(154f, 492f)
                    lineToRelative(57f, -57f)
                    lineToRelative(171f, 171f)
                    lineToRelative(367f, -367f)
                    lineToRelative(57f, 57f)
                    close()
                }
            }.build()

            return _Done!!
        }

    private var _Done: ImageVector? = null

    val x: ImageVector
        get() {
            if (_X != null) return _X!!

            _X = ImageVector.Builder(
                name = "X",
                defaultWidth = 16.dp,
                defaultHeight = 16.dp,
                viewportWidth = 16f,
                viewportHeight = 16f
            ).apply {
                path(
                    fill = SolidColor(Color.Black)
                ) {
                    moveTo(4.646f, 4.646f)
                    arcToRelative(0.5f, 0.5f, 0f, false, true, 0.708f, 0f)
                    lineTo(8f, 7.293f)
                    lineToRelative(2.646f, -2.647f)
                    arcToRelative(0.5f, 0.5f, 0f, false, true, 0.708f, 0.708f)
                    lineTo(8.707f, 8f)
                    lineToRelative(2.647f, 2.646f)
                    arcToRelative(0.5f, 0.5f, 0f, false, true, -0.708f, 0.708f)
                    lineTo(8f, 8.707f)
                    lineToRelative(-2.646f, 2.647f)
                    arcToRelative(0.5f, 0.5f, 0f, false, true, -0.708f, -0.708f)
                    lineTo(7.293f, 8f)
                    lineTo(4.646f, 5.354f)
                    arcToRelative(0.5f, 0.5f, 0f, false, true, 0f, -0.708f)
                }
            }.build()

            return _X!!
        }

    private var _X: ImageVector? = null

    val folderSupervised: ImageVector
        get() {
            if (_Folder_supervised != null) return _Folder_supervised!!

            _Folder_supervised = ImageVector.Builder(
                name = "Folder_supervised",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(160f, 720f)
                    verticalLineToRelative(-480f)
                    verticalLineToRelative(172f)
                    verticalLineToRelative(-12f)
                    close()
                    moveToRelative(0f, 80f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(80f, 720f)
                    verticalLineToRelative(-480f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(160f, 160f)
                    horizontalLineToRelative(240f)
                    lineToRelative(80f, 80f)
                    horizontalLineToRelative(320f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(880f, 320f)
                    verticalLineToRelative(131f)
                    quadToRelative(-18f, -13f, -38f, -22.5f)
                    reflectiveQuadTo(800f, 412f)
                    verticalLineToRelative(-92f)
                    horizontalLineTo(447f)
                    lineToRelative(-80f, -80f)
                    horizontalLineTo(160f)
                    verticalLineToRelative(480f)
                    horizontalLineToRelative(283f)
                    quadToRelative(3f, 21f, 9.5f, 41f)
                    reflectiveQuadToRelative(15.5f, 39f)
                    close()
                    moveToRelative(400f, 0f)
                    verticalLineToRelative(-22f)
                    quadToRelative(0f, -45f, 44f, -71.5f)
                    reflectiveQuadTo(720f, 680f)
                    reflectiveQuadToRelative(116f, 26.5f)
                    reflectiveQuadToRelative(44f, 71.5f)
                    verticalLineToRelative(22f)
                    close()
                    moveToRelative(160f, -160f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(640f, 560f)
                    reflectiveQuadToRelative(23.5f, -56.5f)
                    reflectiveQuadTo(720f, 480f)
                    reflectiveQuadToRelative(56.5f, 23.5f)
                    reflectiveQuadTo(800f, 560f)
                    reflectiveQuadToRelative(-23.5f, 56.5f)
                    reflectiveQuadTo(720f, 640f)
                }
            }.build()

            return _Folder_supervised!!
        }

    private var _Folder_supervised: ImageVector? = null
}