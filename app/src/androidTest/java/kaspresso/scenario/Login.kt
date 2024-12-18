package kaspresso.scenario

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.chesire.nekome.ui.MainActivity
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import io.github.kakaocup.compose.node.element.ComposeScreen
import kaspresso.screen.LoginScreen
import kaspresso.screen.MainScreen

internal class Login<T>(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    private val login: String = "kap.ig.test0@gmail.com",
    private val password: String = "password5",

) : BaseScenario<T>() {
    override val steps: TestContext<T>.() -> Unit = {
        step("Авторизация  $login с паролем $password") {
            ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                userNameInput.performTextInput(login)
                passwordInput.performTextInput(password)
                loginButton.performClick()
            }
            ComposeScreen.onComposeScreen<MainScreen>(composeTestRule) {
                anime.assertIsDisplayed()
                manga.assertIsDisplayed()
                settings.assertIsDisplayed()
            }
        }
    }
}