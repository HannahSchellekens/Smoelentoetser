package nl.hannahschellekens.smoelentoetser

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreen {

    @Serializable
    data class SelectFolder(val defaultPath: String? = null) : AppScreen

    @Serializable
    data class SelectGroups(val smoelenmap: String) : AppScreen

    @Serializable
    data class PracticeSession(val folderPath: String, val groups: List<String>) : AppScreen
}