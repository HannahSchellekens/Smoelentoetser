package nl.hannahschellekens.smoelentoetser.classes

import coil3.BitmapImage
import coil3.ImageLoader
import coil3.PlatformContext
import nl.hannahschellekens.smoelentoetser.compose.readImage
import nl.hannahschellekens.smoelentoetser.model.PracticeSession
import java.io.File

fun File.isSmoelenmap(
    classSeparator: String = "_",
    imageTypes: Set<String> = setOf("png", "jpg"),
): Boolean {
    val correct = listFiles()?.count {
        if (it.isDirectory) return@count false
        if (it.extension.lowercase() !in imageTypes) return@count false

        // Check if there is a class identifier.
        val name = it.nameWithoutExtension
        val klas = name.split(classSeparator)
        if (klas.firstOrNull()?.isBlank() == true) return@count false

        // Check if there is at least a second name.
        if (klas.size == 1) return@count false

        return@count true
    }

    return (correct ?: 0) >= 1
}

fun File.extractGroups(
    classSeparator: String = "_",
    imageTypes: Set<String> = setOf("png", "jpg"),
): List<String> {
    return listFiles()?.mapNotNull {
        if (it.isDirectory) return@mapNotNull null
        if (it.extension.lowercase() !in imageTypes) return@mapNotNull null

        val name = it.nameWithoutExtension
        name.split(classSeparator).firstOrNull()
    }?.distinct()?.sorted() ?: emptyList()
}

fun File.readPeople(
    eligibleGroups: Set<String>,
    classSeparator: String = "_",
    imageTypes: Set<String> = setOf("png", "jpg"),
): List<PracticeSession.Person> {
    return listFiles()?.mapNotNull {
        if (it.isDirectory) return@mapNotNull null
        if (it.extension.lowercase() !in imageTypes) return@mapNotNull null

        val name = it.nameWithoutExtension
        val fileNameParts = name.split(classSeparator)
        if (fileNameParts.size <= 1) return@mapNotNull  null
        val group = fileNameParts.firstOrNull() ?: return@mapNotNull null
        if (group !in eligibleGroups) return@mapNotNull null

        val personNameParts = fileNameParts[1].split(" ")
        val firstName = personNameParts.firstOrNull()?.replace("+", " ") ?: return@mapNotNull null
        val lastName = personNameParts.subList(1, personNameParts.size).joinToString(" ")

        val portrait = it.readImage()

        PracticeSession.Person(firstName, lastName, group, portrait)
    } ?: emptyList()
}