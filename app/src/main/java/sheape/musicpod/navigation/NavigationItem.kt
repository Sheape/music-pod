package sheape.musicpod.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

enum class NavigationItem() {
    HOME, LIBRARY, DOWNLOADS, STATISTICS
}

data class NavbarItem(
    val label: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)

object NavbarItems {
    val items = listOf(
        NavbarItem(NavigationItem.HOME.name, Icons.Outlined.Home, Icons.Filled.Home),
        NavbarItem(NavigationItem.LIBRARY.name, Icons.Outlined.Star, Icons.Filled.Star),
        NavbarItem(
            NavigationItem.DOWNLOADS.name,
            Icons.Outlined.KeyboardArrowDown,
            Icons.Filled.KeyboardArrowDown
        ),
        NavbarItem(NavigationItem.STATISTICS.name, Icons.Outlined.Check, Icons.Filled.Check)
    )
}

fun NavigationItem.getNavKey(): NavKey {
    return when(this) {
        NavigationItem.HOME -> Routes.Home
        NavigationItem.LIBRARY -> Routes.Library
        NavigationItem.DOWNLOADS -> Routes.Downloads
        NavigationItem.STATISTICS -> Routes.Statistics
    }
}

fun NavKey.getNavigationItem(): NavigationItem {
    return when(this) {
        Routes.Home -> NavigationItem.HOME
        Routes.Library -> NavigationItem.LIBRARY
        Routes.Downloads -> NavigationItem.DOWNLOADS
        Routes.Statistics -> NavigationItem.STATISTICS
        else -> throw RuntimeException("Invalid Navkey.")
    }
}