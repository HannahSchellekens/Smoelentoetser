package nl.hannahschellekens.smoelentoetser

import java.util.prefs.Preferences

/**
 * @author Hannah Schellekens
 */
object UserSettings {

    private val preferences = Preferences.userRoot().node(this.javaClass.name)

    var smoelenmap: String?
        get() = preferences.get("smoelenmap", "")
        set(value) {
            preferences.put("smoelenmap", value)
        }
}