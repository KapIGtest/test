package kaspresso.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import rescourses.C

class LoginScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<LoginScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(testTag = C.Tag.login_screen_root) }
    ) {
    val loginButton: KNode = child {
        hasTestTag(testTag = C.Tag.login_screen_login_button)
    }
    val userNameInput: KNode = child {
        hasTestTag(testTag = C.Tag.login_screen_username_input)
    }
    val passwordInput: KNode = child {
        hasTestTag(testTag = C.Tag.login_screen_password_input)
    }
    val passwordVisible: KNode = child {
        hasTestTag(testTag = C.Tag.login_screen_password_visible_button)
    }
    val snackbar: KNode = child {
        hasTestTag(testTag = C.Tag.login_screen_snackbar)
    }
}
