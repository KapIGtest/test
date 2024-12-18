package kaspresso.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import rescourses.C

class SearchScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<SearchScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(testTag = C.Tag.search_screen_root) }
    ) {
        val searchGroup: KNode = child {
            hasTestTag(testTag = C.Tag.search_screen_search_group)
        }
        val searchInput: KNode = child {
            hasTestTag(testTag = C.Tag.search_screen_search_input)
        }
        val searchButton: KNode = child {
            hasTestTag(testTag = C.Tag.search_screen_search_button)
        }
        val anime: KNode = child {
            hasTestTag(testTag = C.Tag.search_screen_anime)
        }
        val manga: KNode = child {
            hasTestTag(testTag = C.Tag.search_screen_manga)
        }
        val snackbar: KNode = child {
            hasTestTag(testTag = C.Tag.search_screen_snackbar)
        }
    }
