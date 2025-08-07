package nl.hannahschellekens.smoelentoetser.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

/**
 * @author Hannah Schellekens
 */
open class PracticeSession(
    val people: List<Person>,
) {

    private val queue = ArrayDeque(people).apply { shuffle() }
    private var endIndex = queue.size - 1
    private var totalEntryCount = queue.size

    var feedbackMessage by mutableStateOf<String?>(null)
    var previousCorrect by mutableStateOf(false)
    var portrait by mutableStateOf<ImageBitmap?>(null)
    var nameHint by mutableStateOf("")
    var groupHint by mutableStateOf("")
    var totalNames by mutableStateOf(totalEntryCount)
    var doneEntryCount by mutableStateOf(0)
    var questionType by mutableStateOf(QuestionType.OPEN)
    val behaviours = mutableStateSetOf(Behaviour.REPEAT_MISTAKES)
    val hints = mutableStateSetOf(Hint.GROUP)

    val isDone: Boolean
        get() = totalNames == doneEntryCount

    val progress: Float
        get() = doneEntryCount.toFloat() / totalNames.toFloat()

    val specialCharacters: List<Char>
        get() = people.flatMap { person ->
            val name = if (Behaviour.LAST_NAMES in behaviours) {
                "${person.firstName} ${person.lastName}"
            }
            else person.firstName
            name.lowercase().filter { it !in 'a'..'z' && it != ' ' && it != '-' }.toList()
        }.distinct().sorted()

    private val correct = HashSet<Person>()
    private val incorrect = HashSet<Person>()

    val percentCorrect: Double
        get() = correct.size.toDouble() / people.size.toDouble()

    init {
        check(people.isNotEmpty()) { "Session must have at least 1 student." }
        updateCurrentPerson()
    }

    fun toggleLastNames() {
        if (Behaviour.LAST_NAMES in behaviours) {
            behaviours.remove(Behaviour.LAST_NAMES)
        }
        else {
            behaviours += Behaviour.LAST_NAMES
        }
        updateCurrentPerson()
    }

    fun toggleGroupHint() {
        if (Hint.GROUP in hints) {
            hints.remove(Hint.GROUP)
        }
        else {
            hints += Hint.GROUP
        }
        updateCurrentPerson()
    }

    fun toggleShuffle() {
        if (Hint.SHUFFLE in hints) {
            hints.remove(Hint.SHUFFLE)
        }
        else {
            hints += Hint.SHUFFLE
        }
        updateCurrentPerson()
    }

    fun toggleThirtyPercent() {
        if (Hint.MULTIPLE_LETTERS_30 in hints) {
            hints.remove(Hint.MULTIPLE_LETTERS_30)
        }
        else {
            hints += Hint.MULTIPLE_LETTERS_30
        }
        updateCurrentPerson()
    }

    fun toggleFirstLetter() {
        if (Hint.FIRST_LETTER in hints) {
            hints.remove(Hint.FIRST_LETTER)
        }
        else {
            hints += Hint.FIRST_LETTER
        }
        updateCurrentPerson()
    }

    private fun addCorrect(person: Person) {
        if (person !in correct && person !in incorrect) {
            correct += person
        }
    }

    private fun addIncorrect(person: Person) {
        if (person !in correct && person !in incorrect) {
            incorrect += person
        }
    }

    fun submitName(input: String) {
        val sanitized = input.trim().lowercase()
        val person = queue.first()
        val expected = expectedName(person)
        previousCorrect = sanitized == expected.lowercase()

        feedbackMessage = if (previousCorrect) "Dat was inderdaad $expected" else "Incorrect, dat was $expected"

        doneEntryCount++
        if (previousCorrect) {
            addCorrect(person)
            endIndex--
            queue.removeFirst()
        }
        else {
            addIncorrect(person)
            totalNames += 2
            queue.addLast(person)
            queue.removeFirst()

            val candidateInsert = Random.nextInt(3, 6)
            val insertLocation = max(1, min(endIndex, candidateInsert))
            queue.add(insertLocation, person)
        }

        updateCurrentPerson()
    }

    private fun updateCurrentPerson() {
        if (queue.isEmpty()) return

        val person = queue.first()

        portrait = person.image
        nameHint = fullNameHint(person)
        groupHint = groupHint(person)
    }

    private fun expectedName(person: Person): String {
        return if (Behaviour.LAST_NAMES in behaviours) {
            "${person.firstName} ${person.lastName}"
        }
        else person.firstName
    }

    private fun groupHint(person: Person): String {
        return if (Hint.GROUP in hints) {
            "(${person.group})"
        }
        else ""
    }

    private fun fullNameHint(person: Person): String {
        return nameHint(person.firstName) + if (Behaviour.LAST_NAMES in behaviours) {
             " ${nameHint(person.lastName)}"
        }
        else ""
    }

    private fun nameHint(name: String): String {
        if (name.isBlank()) return name

        val baseName = name.replace("+", " ")

        // Shuffle letters.
        // Remove caps to prevent hints.
        if (Hint.SHUFFLE in hints) {
            val startIndex = if (Hint.FIRST_LETTER in hints) 1 else 0
            val prefix = if (Hint.FIRST_LETTER in hints) baseName.first().toString() else ""
            return prefix + baseName.substring(startIndex).toList().shuffled().joinToString("").lowercase()
        }

        // Reveal ~30% of the letters.
        val base = if (Hint.MULTIPLE_LETTERS_30 in hints) {
            buildString {
                baseName.forEach { letter ->
                    append(if (Random.nextDouble() < 0.3) letter else "ˍ")
                }
            }
        }
        else "ˍ".repeat(baseName.length)

        // Check if the first letter must be shown
        return if (Hint.FIRST_LETTER in hints) {
            baseName.first() + base.substring(1)
        }
        else base
    }

    data class Person(
        val firstName: String,
        val lastName: String,
        val group: String,
        val image: ImageBitmap,
    )

    enum class Hint(val description: String) {
        FIRST_LETTER("Eerste letter"),
        MULTIPLE_LETTERS_30("30%"),
        SHUFFLE("Shuffle"),
        GROUP("Klas")
    }

    enum class Behaviour(val description: String) {
        REPEAT_MISTAKES("Fouten herhalen"),
        LAST_NAMES("Achternamen"),
        ALMOST("Bijna goed")
    }

    enum class QuestionType(val description: String) {
        OPEN("Open"),
        MULTIPLE_CHOICE("Meerkeuze")
    }
}