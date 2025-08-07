package nl.hannahschellekens.smoelentoetser.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.hannahschellekens.smoelentoetser.compose.ComposeIcons
import nl.hannahschellekens.smoelentoetser.compose.hspace
import nl.hannahschellekens.smoelentoetser.compose.vspace
import nl.hannahschellekens.smoelentoetser.model.GroupSelection
import nl.hannahschellekens.smoelentoetser.model.SelectFolder

@Composable
fun GroupSelectionScreen(
    groupSelection: GroupSelection,
    toggleGroup: (String) -> Unit,
    toggleAll: () -> Unit,
    onPreviousStep: () -> Unit,
    onNextStep: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Select groups
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Selecteer Klassen:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        vspace(8.dp)
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            repeat(groupSelection.groupNames.size) { index ->
                val groupName = groupSelection.groupNames[index]
                val color = if (index in groupSelection.selectedIndices) {
                    ButtonDefaults.buttonColors()
                }
                else ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)

                Button(
                    colors = color,
                    modifier = Modifier.padding(4.dp),
                    onClick = { toggleGroup(groupName) }
                ) {
                    Text(text = groupName)
                }
            }
        }
        vspace(8.dp)
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            onClick = toggleAll
        ) {
            Icon(
                imageVector = ComposeIcons.group,
                contentDescription = "Alle klassen selecteren",
                modifier = Modifier.size(20.dp),
            )
            hspace(8.dp)
            Text(text = "Alle klassen")
        }
        vspace(48.dp)

        // Next step.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onPreviousStep,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.LightGray,
                    contentColor = Color.DarkGray
                )
            ) {
                Icon(
                    imageVector = ComposeIcons.arrowForward,
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp).scale(-1f, 1f),
                )
                hspace(4.dp)
                Text(text = "Terug")
            }
            hspace(12.dp)
            Button(
                onNextStep,
                enabled = groupSelection.selectedIndices.isNotEmpty(),
            ) {
                Text(text = "Start met oefenen ")
                hspace(4.dp)
                Icon(
                    imageVector = ComposeIcons.playArrow,
                    contentDescription = "Volgende",
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}