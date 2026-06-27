package nl.hannahschellekens.smoelentoetser.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.openFilePicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.hannahschellekens.smoelentoetser.compose.ComposeIcons
import nl.hannahschellekens.smoelentoetser.compose.hspace
import nl.hannahschellekens.smoelentoetser.compose.vspace
import nl.hannahschellekens.smoelentoetser.import.PdfImporter
import java.io.File

@Composable
fun MagisterImportDialog(
    smoelenmap: String,
    onDismiss: () -> Unit,
    onImportSuccess: () -> Unit
) {
    var groupName by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                Text(
                    "Importeer fotolijst uit Magister",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Selecteer een klas en print een fotolijst in Magister. Importeer deze PDF.",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

        },
        text = {
            OutlinedTextField(
                value = groupName,
                onValueChange = { groupName = it },
                label = { Text("Naam van de klas (bijv. ha1e)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    coroutineScope.launch {
                        // 1. Open de File Picker
                        val file = FileKit.openFilePicker(
                            title = "Selecteer Magister PDF"
                        )

                        if (file != null) {
                            // 2. Run de zware import op de IO thread
                            withContext(Dispatchers.IO) {
                                val importer = PdfImporter(
                                    pdfFile = File(file.absolutePath()),
                                    outputDirectory = File(smoelenmap),
                                    groupName = groupName
                                )
                                importer.importPdf()
                            }

                            // 3. Sluit de popup en ververs de UI
                            onDismiss()
                            onImportSuccess()
                        }
                    }
                },
                enabled = groupName.isNotBlank(), // Knop is pas klikbaar als er een naam is
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            ) {
                Icon(
                    imageVector = ComposeIcons.fileImport,
                    contentDescription = "Kies PDF & importeer",
                    modifier = Modifier.size(20.dp),
                )
                hspace(8.dp)
                Text("Kies PDF & importeer")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Annuleren")
            }
        }
    )
}