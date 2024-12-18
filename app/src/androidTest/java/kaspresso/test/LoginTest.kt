package kaspresso.test

import androidx.compose.ui.test.hasText
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.kakao.common.utilities.getResourceString
import kaspresso.screen.LoginScreen
import kaspresso.screen.MainScreen
import kaspresso.tools.hideKeyboard
import kaspresso.tools.passwordMask
import kaspresso.tools.randString
import org.junit.Test
import com.chesire.nekome.core.resources.R as Resource

@HiltAndroidTest
class LoginTest : BaseTest() {
    /**
     * Проверка поля ввода Почта
     */
    @Test
    fun checkingTheInputFieldMail() {
        val someText = "0123adsfABCDE!@#$%^&*()_+*().,"
        val longString = randString(150)
        before("01_Checking_the_input_field_Mail") { }
        .after { }
        .run {
            step("Стартовое отображение поля ввода") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        assertIsDisplayed()
                        assertIsNotFocused()
                        assertTextContains("")
                    }
                }
            }
            step("Заполнить поле ввода строкой $someText") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        performClick()
                        assertIsFocused()
                        performTextInput(someText)
                        hideKeyboard()
                        assertTextContains(someText)
                    }
                    userNameInput.performTextClearance()
                }
            }
            step("Заполнить поле ввода строкой в 150 символов") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        performClick()
                        performTextInput(longString)
                        hideKeyboard()
                        assertTextContains(longString)
                    }
                    userNameInput.performTextClearance()
                }
            }
        }
    }

    /**
     * Проверка поля ввода Пароль
     */
    @Test
    fun checkingThePasswordInputField() {
        val someText = "0123adsfABCDE!@#$%^&*()_+*().,"
        val passwordMaskSome = passwordMask(someText)
        val longString = randString(150)
        val passwordMaskLong = passwordMask(longString)
        before("02_Checking_the_Password_input_field") { }
        .after { }
        .run {
            step("Стартовое отображение поля ввода") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    passwordInput {
                        assertIsDisplayed()
                        assertIsNotFocused()
                        assertTextContains("")
                    }
                }
            }
            step("Заполнить поле ввода строкой $someText") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    passwordInput {
                        performClick()
                        assertIsFocused()
                        performTextInput(someText)
                        hideKeyboard()
                        assertTextContains(passwordMaskSome)
                    }
                    passwordInput.performTextClearance()
                }
            }
            step("Заполнить поле ввода строкой в 150 символов") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    passwordInput {
                        performClick()
                        performTextInput(longString)
                        hideKeyboard()
                        assertTextContains(passwordMaskLong)
                    }
                    passwordInput.performTextClearance()
                }
            }
            step("Проверка кнопки скрытия пароля") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    passwordInput {
                        performClick()
                        performTextInput(someText)
                        hideKeyboard()
                        assertTextContains(passwordMaskSome)
                    }
                    passwordVisible {
                        assertIsDisplayed()
                        performClick()
                    }
                    passwordInput.assertTextContains(someText)
                    passwordVisible {
                        assertIsDisplayed()
                        performClick()
                    }
                    passwordInput.assertTextContains(passwordMaskSome)
                    passwordInput.performTextClearance()
                }
            }
        }
    }

    /**
     * Проверка кнопки "Логин"
     */
    @Test
    fun checkingTheLoginButton() {
        val someText = "someText"
        val passwordMask = passwordMask(someText)
        before("03_Checking_the_Login_button") { }
        .after { }
        .run {
            step("Стартовое отображение кнопки") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    loginButton {
                        assertIsDisplayed()
                        assertTextContains("Login")
                        assertIsNotEnabled()
                    }
                }
            }
            step("Заполненo только поле ввода \"Kitsu email\"") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        performClick()
                        performTextInput(someText)
                        hideKeyboard()
                        assertTextContains(someText)
                    }
                    loginButton.assertIsNotEnabled()
                    userNameInput.performTextClearance()
                }
            }
            step("Заполненo только поле ввода \"Password\"") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    passwordInput {
                        performClick()
                        performTextInput(someText)
                        hideKeyboard()
                        assertTextContains(passwordMask)
                    }
                    loginButton.assertIsNotEnabled()
                    passwordInput.performTextClearance()
                }
            }
            step("Заполнены оба поля ввода") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        performClick()
                        performTextInput(someText)
                        hideKeyboard()
                        assertTextContains(someText)
                    }
                    passwordInput {
                        performClick()
                        performTextInput(someText)
                        hideKeyboard()
                        assertTextContains(passwordMask)
                    }
                    loginButton.assertIsEnabled()
                }
            }
        }
    }

    /**
     * Успешная авторизация (реальные данные)
     */
    @Test
    fun successfulAuthorization() {
        val login = "kap.ig.test0@gmail.com"
        val password = "password5"
        val passwordMask = passwordMask(password)
        before("04_Successful_authorization") { }
        .after { }
        .run {
            step("Проверка стартового состояния ключевых элементов") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        assertIsDisplayed()
                        assertTextContains("")
                    }
                    passwordInput{
                        assertIsDisplayed()
                        assertTextContains("")
                    }
                    loginButton{
                        assertIsDisplayed()
                        assertTextContains("Login")
                        assertIsNotEnabled()
                    }
                }
            }
            step("Заполнение полея ввода \"Kitsu email\"") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        performClick()
                        performTextInput(login)
                        hideKeyboard()
                        assertTextContains(login)
                    }
                }
            }
            step("Заполнение полея ввода \"Password\"") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    passwordInput {
                        performClick()
                        performTextInput(password)
                        hideKeyboard()
                        assertTextContains(passwordMask)
                    }
                }
            }
            step("Нажать кнопку \"Login\"") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    loginButton {
                        assertIsEnabled()
                        performClick()
                    }
                }
            }
            step("Проверка открытия главного экрана") {
                ComposeScreen.onComposeScreen<MainScreen>(composeTestRule) {
                    anime.assertIsDisplayed()
                    manga.assertIsDisplayed()
                    settings.assertIsDisplayed()
                }
            }
        }
    }

    /**
     * Oшибки в снекбаре
     */
    @Test
    fun snackbarBugs() {
        val login = "kap.ig.test0@gmail.com"
        val loginWrong = "kap.ig.test1@gmail.com"
        val password = "password5"
        val passwordWrong = "password6"
        val passwordMask = passwordMask(password)
        val passwordMaskWrong = passwordMask(password)
        before("05_Snackbar_bugs") { }
        .after { }
        .run {
            step("Несуществующий логин") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        performClick()
                        performTextInput(loginWrong)
                        hideKeyboard()
                        assertTextContains(loginWrong)
                    }
                    passwordInput {
                        performClick()
                        performTextInput(password)
                        hideKeyboard()
                        assertTextContains(passwordMask)
                    }
                    loginButton {
                        assertIsEnabled()
                        performClick()
                    }
                    snackbar.assertIsDisplayed()
                    composeTestRule.onNode(
                        hasText(getResourceString(Resource.string.login_error_generic)),
                        useUnmergedTree = true
                    )
                    userNameInput.performTextClearance()
                    passwordInput.performTextClearance()
                }
            }
            step("Неверный пароль") {
                ComposeScreen.onComposeScreen<LoginScreen>(composeTestRule) {
                    userNameInput {
                        performClick()
                        performTextInput(login)
                        hideKeyboard()
                        assertTextContains(login)
                    }
                    passwordInput {
                        performClick()
                        performTextInput(passwordWrong)
                        hideKeyboard()
                        assertTextContains(passwordMaskWrong)
                    }
                    loginButton {
                        assertIsEnabled()
                        performClick()
                    }
                    snackbar.assertIsDisplayed()
                    composeTestRule.onNode(
                        hasText(getResourceString(Resource.string.login_error_credentials)),
                        useUnmergedTree = true
                    )
                    userNameInput.performTextClearance()
                    passwordInput.performTextClearance()
                }
            }
        }
    }
}


