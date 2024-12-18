package kaspresso.test

import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.kakao.common.utilities.getResourceString
import kaspresso.scenario.Login
import kaspresso.screen.MainScreen
import org.junit.Test
import com.chesire.nekome.core.resources.R as Resource

@HiltAndroidTest
class MainTest : BaseTest() {

    /**
     * Проверка навигационной панели
     */
    @Test
    fun navigationbar() {
        before("10_navigationbar") { }
        .after { }
        .run {
            scenario(Login(composeTestRule))
            step("Наличи элеметов на экране") {
                ComposeScreen.onComposeScreen<MainScreen>(composeTestRule) {
                    anime {
                        assertIsDisplayed()
                        assertIsSelected()
                        assertTextContains(getResourceString(Resource.string.nav_anime))
                    }
                    manga {
                        assertIsDisplayed()
                        assertIsNotSelected()
                        assertTextContains(getResourceString(Resource.string.nav_manga))
                    }
                    settings {
                        assertIsDisplayed()
                        assertIsNotSelected()
                        assertTextContains(getResourceString(Resource.string.nav_settings))
                    }
                }
            }
            step("Включение кнопки Manga") {
                ComposeScreen.onComposeScreen<MainScreen>(composeTestRule) {
                    manga.performClick()
                    anime {
                        assertIsDisplayed()
                        assertIsNotSelected()
                        assertTextContains(getResourceString(Resource.string.nav_anime))
                    }
                    manga {
                        assertIsDisplayed()
                        assertIsSelected()
                        assertTextContains(getResourceString(Resource.string.nav_manga))
                    }
                    settings {
                        assertIsDisplayed()
                        assertIsNotSelected()
                        assertTextContains(getResourceString(Resource.string.nav_settings))
                    }
                }
                step("Включение кнопки Settings") {
                    ComposeScreen.onComposeScreen<MainScreen>(composeTestRule) {
                        settings.performClick()
                        anime {
                            assertIsDisplayed()
                            assertIsNotSelected()
                            assertTextContains(getResourceString(Resource.string.nav_anime))
                        }
                        manga {
                            assertIsDisplayed()
                            assertIsNotSelected()
                            assertTextContains(getResourceString(Resource.string.nav_manga))
                        }
                        settings {
                            assertIsDisplayed()
                            assertIsSelected()
                            assertTextContains(getResourceString(Resource.string.nav_settings))
                        }
                    }
                }
                step("Включение кнопки Anime") {
                    ComposeScreen.onComposeScreen<MainScreen>(composeTestRule) {
                        anime.performClick()
                        anime {
                            assertIsDisplayed()
                            assertIsSelected()
                            assertTextContains(getResourceString(Resource.string.nav_anime))
                        }
                        manga {
                            assertIsDisplayed()
                            assertIsNotSelected()
                            assertTextContains(getResourceString(Resource.string.nav_manga))
                        }
                        settings {
                            assertIsDisplayed()
                            assertIsNotSelected()
                            assertTextContains(getResourceString(Resource.string.nav_settings))
                        }
                    }
                }
            }
        }
    }
}


