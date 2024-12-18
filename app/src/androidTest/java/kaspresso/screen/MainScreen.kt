package kaspresso.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import rescourses.C

class MainScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(testTag = C.Tag.main_screen_root) }
    ) {
        val snackbar: KNode = child {
            hasTestTag(testTag = C.Tag.main_screen_snackbar)
        }
        val anime: KNode = child {
            hasTestTag(testTag = C.Tag.main_screen_anime)
        }
        val manga: KNode = child {
            hasTestTag(testTag = C.Tag.main_screen_manga)
        }
        val searchInput: KNode = child {
            hasTestTag(testTag = C.Tag.main_screen_search_input)
        }
        val settings: KNode = child {
            hasTestTag(testTag = C.Tag.main_screen_settings)
        }
    }
