package sheape.musicpod.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import sheape.musicpod.components.SimpleSearchBar


@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Routes.Home)
    var selectedNavigation by rememberSaveable { mutableStateOf(NavigationItem.HOME) }

    NavigationScaffold(
        selected = selectedNavigation,
        onClick = {
            selectedNavigation = it
            if (backStack.last() != it.getNavKey())
                backStack.add(it.getNavKey())
        },
        topBarContent = {
            if (backStack.last() == Routes.Home)
                SimpleSearchBar()
            else
                Text("Hi, I am currently here in: ${selectedNavigation.name}")
        },
        content = {
            NavDisplay(
                backStack = backStack,
                onBack = {
                    backStack.removeLastOrNull()
                    selectedNavigation = backStack.last().getNavigationItem()
                },
                entryDecorators = listOf(
                    rememberSavedStateNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                    rememberSceneSetupNavEntryDecorator()
                ),
                entryProvider = { key ->
                    when (key) {
                        is Routes.Home, Routes.Library, Routes.Downloads, Routes.Statistics -> {
                            NavEntry(key) {}
                        }

                        else -> throw RuntimeException("Invalid Navkey.")
                    }
                }
            )
        }
    )
}