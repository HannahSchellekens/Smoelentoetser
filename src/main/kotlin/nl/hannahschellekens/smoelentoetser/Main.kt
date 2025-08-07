package nl.hannahschellekens.smoelentoetser

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.openDirectoryPicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.hannahschellekens.smoelentoetser.classes.extractGroups
import nl.hannahschellekens.smoelentoetser.compose.painterResource
import nl.hannahschellekens.smoelentoetser.model.GroupSelection
import nl.hannahschellekens.smoelentoetser.model.SelectFolder
import nl.hannahschellekens.smoelentoetser.view.GroupSelectionScreen
import nl.hannahschellekens.smoelentoetser.view.PracticeSessionScreen
import nl.hannahschellekens.smoelentoetser.view.StartScreen
import java.io.File

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = AppScreen.SelectFolder()
        ) {
            composable<AppScreen.SelectFolder> { input ->
                val selectFolderDefaults: AppScreen.SelectFolder = input.toRoute()
                val defaultFolder = selectFolderDefaults.defaultPath

                val selectFolder = remember { SelectFolder(UserSettings.smoelenmap) }
                StartScreen(
                    selectFolder.smoelenmap,
                    selectFolder.folderStatus,
                    onBrowseFolder = {
                        CoroutineScope(Dispatchers.Main).launch {
                            val directory = FileKit.openDirectoryPicker()
                            selectFolder.updateFolder(directory?.absolutePath())
                        }
                    },
                    onNextStep = {
                        navController.navigate(AppScreen.SelectGroups(
                            selectFolder.smoelenmap
                        ))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<AppScreen.SelectGroups> { input ->
                val selectFolder: AppScreen.SelectGroups = input.toRoute()
                val groups = File(selectFolder.smoelenmap).extractGroups()
                val groupSelection = remember { GroupSelection(groups) }
                GroupSelectionScreen(
                    groupSelection,
                    groupSelection::toggleGroup,
                    groupSelection::toggleAll,
                    onPreviousStep = {
                        navController.navigate(AppScreen.SelectFolder(selectFolder.smoelenmap))
                    },
                    onNextStep = {
                        navController.navigate(AppScreen.PracticeSession(
                            selectFolder.smoelenmap,
                            groupSelection.selectedGroups
                        ))
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable<AppScreen.PracticeSession> { input ->
                val inputData: AppScreen.PracticeSession = input.toRoute()
                PracticeSessionScreen(
                    directoryPath = inputData.folderPath,
                    selectedGroups = inputData.groups.toSet(),
                    onReturn = {
                        navController.navigate(AppScreen.SelectGroups(
                            inputData.folderPath
                        ))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

fun main() = application {
    val windowState = rememberWindowState(
        width = 880.dp,
        height = 720.dp,
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "Smoelentoetser",
        state = windowState,
        icon = painterResource("icon.png")
    ) {
        App()
    }
}
