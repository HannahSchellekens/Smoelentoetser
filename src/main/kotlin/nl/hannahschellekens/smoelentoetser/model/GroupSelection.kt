package nl.hannahschellekens.smoelentoetser.model

import androidx.compose.runtime.mutableStateSetOf

/**
 * @author Hannah Schellekens
 */
open class GroupSelection(val groupNames: List<String>) {

    val selectedIndices = mutableStateSetOf<Int>()

    val selectedGroups: List<String>
        get() = ArrayList<String>(selectedIndices.size).apply {
            selectedIndices.forEach { this += groupNames[it] }
        }

    fun toggleGroup(name: String) {
        val index = groupNames.indexOf(name)

        if (index in selectedIndices) {
            selectedIndices.remove(index)
        }
        else selectedIndices.add(index)
    }

    fun toggleAll() {
        if (selectedIndices.size == groupNames.size) {
            selectedIndices.clear()
        }
        else {
            repeat(groupNames.size) {
                selectedIndices.add(it)
            }
        }
    }
}