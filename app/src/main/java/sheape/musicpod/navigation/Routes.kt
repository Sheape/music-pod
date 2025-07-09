package sheape.musicpod.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object RootGraph: NavKey

    @Serializable
    data object Home: NavKey

    @Serializable
    data object Library: NavKey

    @Serializable
    data object Downloads: NavKey

    @Serializable
    data object Statistics: NavKey
}