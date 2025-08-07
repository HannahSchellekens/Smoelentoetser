package nl.hannahschellekens.smoelentoetser.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.utf16CodePoint
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import io.github.vinceglb.confettikit.compose.ConfettiKit
import io.github.vinceglb.confettikit.core.Party
import io.github.vinceglb.confettikit.core.emitter.Emitter
import nl.hannahschellekens.smoelentoetser.classes.readPeople
import nl.hannahschellekens.smoelentoetser.compose.ColorGradient
import nl.hannahschellekens.smoelentoetser.compose.ComposeIcons
import nl.hannahschellekens.smoelentoetser.compose.hspace
import nl.hannahschellekens.smoelentoetser.compose.vspace
import nl.hannahschellekens.smoelentoetser.model.PracticeSession
import java.io.File
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Composable
fun PracticeSessionScreen(
    directoryPath: String,
    selectedGroups: Set<String>,
    onReturn: () -> Unit,
    modifier: Modifier = Modifier
) {
    check(selectedGroups.isNotEmpty()) { "Must select at least 1 group" }

    val spacing = 18.dp
    val session = remember { PracticeSession(File(directoryPath).readPeople(selectedGroups)) }

    @Composable
    fun checkButtonColour(hint: PracticeSession.Hint): ButtonColors {
        return if (hint in session.hints) {
            ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        }
        else ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
    }

    @Composable
    fun checkButtonColour(behaviour: PracticeSession.Behaviour): ButtonColors {
        return if (behaviour in session.behaviours) {
            ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        }
        else ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
    }

    @Composable
    fun composeButtonCheckIcon() {
        Icon(
            imageVector = ComposeIcons.done,
            contentDescription = "Checkmark",
            modifier = Modifier.size(20.dp),
        )
        hspace(8.dp)
    }

    @Composable
    fun composeButtonIcon(hint: PracticeSession.Hint) {
        if (hint in session.hints) {
            composeButtonCheckIcon()
        }
    }

    @Composable
    fun composeButtonIcon(behaviour: PracticeSession.Behaviour) {
        if (behaviour in session.behaviours) {
            composeButtonCheckIcon()
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Progress bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = onReturn,
            ) {
                Icon(
                    imageVector = ComposeIcons.exit,
                    contentDescription = "Terug naar menu",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Gray
                )
            }
            hspace(12.dp)

            LinearProgressIndicator(
                session.progress,
                modifier = Modifier.fillMaxWidth(0.6666666f)
            )

            hspace(12.dp)
            Text(
                text = "${session.doneEntryCount} van ${session.totalNames}",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
            )
        }
        vspace(8.dp)

        // Done!
        if (session.isDone) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "\uD83C\uDF89 Gefeliciteerd, je bent klaar! \uD83C\uDF89",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        brush = Brush.linearGradient(ColorGradient.rainbow, tileMode = TileMode.Repeated)
                    ),
                    modifier = Modifier.padding(32.dp),
                )

                val correct = (session.percentCorrect * 100).roundToInt()
                Text(
                    "Je had $correct% goed",
                    color = Color.Gray,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                vspace(24.dp)

                Button(
                    onClick = onReturn,
                ) {
                    Icon(
                        imageVector = ComposeIcons.exit,
                        contentDescription = "Terug naar menu",
                        modifier = Modifier.size(20.dp),
                    )
                    hspace(6.dp)
                    Text(text = "Terug naar menu")
                }

                ConfettiKit(
                    modifier = Modifier.fillMaxSize(),
                    parties = listOf(
                        Party(emitter = Emitter(duration = 250.milliseconds).perSecond(correct + 25))
                    )
                )
            }

            return
        }

        // Input field
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Portrait
            session.portrait?.let {
                Image(
                    it,
                    "Portret student",
                    modifier = Modifier.padding(12.dp)
                )
            }

            // Input fields
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth().padding(6.dp)
            ) {
                // Hints
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(8.dp)
                ) {
                    // Naam hint
                    Text(
                        text = session.nameHint,
                        fontSize = 24.sp,
                        color = Color.Gray
                    )
                    hspace(20.dp)

                    // Klassenhint
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = session.groupHint,
                            fontSize = 16.sp,
                            color = Color.Gray,
                        )
                    }
                }

                // Input field
                val textFieldfocus = remember { FocusRequester() }
                val submitField = rememberTextFieldState(initialText = "")

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().padding(8.dp).focusGroup()
                ) {
                    OutlinedTextField(
                        state = submitField,
                        lineLimits = TextFieldLineLimits.SingleLine,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        modifier = Modifier
                            .focusable()
                            .focusRequester(textFieldfocus)
                            .fillMaxWidth()
                            .onKeyEvent { event ->
                                // The only way to check for an enter key for some reason.
                                if (event.utf16CodePoint == 10 && session.isDone.not()) {
                                    session.submitName(submitField.text.toString())
                                    submitField.clearText()
                                    textFieldfocus.requestFocus()
                                    true
                                }
                                else false
                            },
                    )
                }

                FlowRow(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().padding(8.dp).focusGroup()
                ) {
                    Button(
                        enabled = session.isDone.not(),
                        onClick = {
                            if (session.isDone.not()) {
                                session.submitName(submitField.text.toString())
                                submitField.clearText()
                                textFieldfocus.requestFocus()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = ComposeIcons.send,
                            contentDescription = "Beantwoorden",
                            modifier = Modifier.size(20.dp),
                        )
                        Text("Antwoorden")
                    }

                    hspace(16.dp)

                    // Input feedback
                    if (session.doneEntryCount >= 1) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        ) {
                            val icon = if (session.previousCorrect) ComposeIcons.done else ComposeIcons.x
                            val color = if (session.previousCorrect) Color.hsl(123f, 0.38f, 0.57f) else Color.Red

                            Icon(
                                imageVector = icon,
                                contentDescription = "Status",
                                modifier = Modifier.size(18.dp),
                                tint = color
                            )
                            hspace(8.dp)
                            Text(
                                text = session.feedbackMessage ?: "",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = color
                            )
                        }
                    }

                    val specialChars = session.specialCharacters
                    if (specialChars.isNotEmpty()) {
                        Divider(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
                    }

                    specialChars.forEach { char ->
                        Button(
                            onClick = {
                                submitField.setTextAndPlaceCursorAtEnd("${submitField.text}$char")
                                textFieldfocus.requestFocus()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                            modifier = Modifier.padding(end = 4.dp)
                        ) {
                            Text("$char", fontSize = 16.sp)
                        }
                    }
                }

                // Option bar
                Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp)
                ) {
                    // Hint: Show Group
                    Button(
                        onClick = session::toggleGroupHint,
                        colors = checkButtonColour(PracticeSession.Hint.GROUP)
                    ) {
                        composeButtonIcon(PracticeSession.Hint.GROUP)
                        Text(text = "Klas")
                    }
                    hspace(6.dp)

                    // Hint: First Letter
                    Button(
                        onClick = session::toggleFirstLetter,
                        colors = checkButtonColour(PracticeSession.Hint.FIRST_LETTER)
                    ) {
                        composeButtonIcon(PracticeSession.Hint.FIRST_LETTER)
                        Text(text = "1e letter")
                    }
                    hspace(6.dp)

                    // Hint: 30%
                    Button(
                        onClick = session::toggleThirtyPercent,
                        colors = checkButtonColour(PracticeSession.Hint.MULTIPLE_LETTERS_30)
                    ) {
                        composeButtonIcon(PracticeSession.Hint.MULTIPLE_LETTERS_30)
                        Text(text = "30%")
                    }
                    hspace(6.dp)

                    // Hint: Shuffle
                    Button(
                        onClick = session::toggleShuffle,
                        colors = checkButtonColour(PracticeSession.Hint.SHUFFLE)
                    ) {
                        composeButtonIcon(PracticeSession.Hint.SHUFFLE)
                        Text(text = "Shuffle")
                    }
                }

                // Option bar.
                Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp)
                ) {
                    // Option: Include last names.
                    Button(
                        onClick = session::toggleLastNames,
                        colors = checkButtonColour(PracticeSession.Behaviour.LAST_NAMES)
                    ) {
                        composeButtonIcon(PracticeSession.Behaviour.LAST_NAMES)
                        Text(text = "Achternamen")
                    }
                }
            }
        }
    }
}