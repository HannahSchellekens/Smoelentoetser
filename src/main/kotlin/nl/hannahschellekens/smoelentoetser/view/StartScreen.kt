package nl.hannahschellekens.smoelentoetser.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.hannahschellekens.smoelentoetser.compose.ComposeIcons
import nl.hannahschellekens.smoelentoetser.compose.hspace
import nl.hannahschellekens.smoelentoetser.compose.vspace
import nl.hannahschellekens.smoelentoetser.model.SelectFolder

@Composable
fun StartScreen(
    smoelenmapPath: String,
    folderState: SelectFolder.FolderState,
    onBrowseFolder: () -> Unit,
    onNextStep: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Smoelentoetser",
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp
        )
        Text(
            text = "Hannah Schellekens",
            fontSize = 20.sp,
            color = Color.LightGray
        )
        vspace(48.dp)

        // Select folder
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Selecteer Smoelenmap:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            hspace(24.dp)
            Button(onBrowseFolder) {
                Icon(
                    imageVector = ComposeIcons.folderSupervised,
                    contentDescription = "Bladeren",
                    modifier = Modifier.size(20.dp),
                )
            }
        }
        Text(
            text = smoelenmapPath,
            color = Color.DarkGray,
            fontSize = 16.sp,
        )
        vspace(16.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = if (folderState == SelectFolder.FolderState.VALID) {
                ComposeIcons.done
            }
            else ComposeIcons.x

            Icon(
                imageVector = icon,
                contentDescription = "mapstatus",
                modifier = Modifier.size(16.dp),
                tint = folderState.color
            )
            hspace(4.dp)
            Text(
                text = folderState.description.lowercase(),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = folderState.color
            )
        }
        vspace(32.dp)

        // Next step.
        Button(
            onNextStep,
            enabled = folderState == SelectFolder.FolderState.VALID,
        ) {
            Text(text = "Selecteer klassen")
            hspace(8.dp)
            Icon(
                imageVector = ComposeIcons.arrowForward,
                contentDescription = "Volgende",
                modifier = Modifier.size(16.dp),
            )
        }
    }
}