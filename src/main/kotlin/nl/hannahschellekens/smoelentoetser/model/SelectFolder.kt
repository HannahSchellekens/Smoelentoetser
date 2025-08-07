package nl.hannahschellekens.smoelentoetser.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import nl.hannahschellekens.smoelentoetser.UserSettings
import nl.hannahschellekens.smoelentoetser.classes.isSmoelenmap
import java.io.File

/**
 * @author Hannah Schellekens
 */
open class SelectFolder(defaultFolder: String? = null) {

    var smoelenmap by mutableStateOf(defaultFolder ?: "")
    var folderStatus by mutableStateOf(FolderState.NOT_SELECTED)

    init {
        checkFolderStatus()
    }

    fun updateFolder(directoryPath: String?) {
        if (directoryPath.isNullOrBlank()) return

        smoelenmap = directoryPath
        UserSettings.smoelenmap = smoelenmap
        checkFolderStatus()
    }

    private fun checkFolderStatus() {
        val directory = File(smoelenmap)
        folderStatus = if (smoelenmap.isBlank()) {
            FolderState.NOT_SELECTED
        }
        else if (directory.exists().not()) {
            FolderState.NOT_EXISTS
        }
        else if (directory.isSmoelenmap()) {
            FolderState.VALID
        }
        else FolderState.INVALID_BUT_EXISTS
    }

    enum class FolderState(val description: String, val color: Color) {
        VALID("Smoelenmap geselecteerd!", Color.hsl(123f, 0.38f, 0.57f)),
        INVALID_BUT_EXISTS("Map is geen valide smoelenmap", Color.Red),
        NOT_EXISTS("Map bestaat niet", Color.Red),
        NOT_SELECTED("Geen smoelenmap geselecteerd", Color.Red),
    }
}