package kaspresso.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.rule.cleardata.ClearDatabaseRule
import com.adevinta.android.barista.rule.cleardata.ClearPreferencesRule
import com.chesire.nekome.core.preferences.ApplicationPreferences
import com.chesire.nekome.core.preferences.SeriesPreferences
import com.chesire.nekome.database.dao.SeriesDao
import com.chesire.nekome.database.dao.UserDao
import com.chesire.nekome.datasource.auth.local.AuthProvider
import com.chesire.nekome.helpers.createTestUser
import com.chesire.nekome.helpers.login
import com.chesire.nekome.helpers.logout
import com.chesire.nekome.helpers.reset
import com.chesire.nekome.ui.MainActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.components.composesupport.interceptors.behavior.impl.systemsafety.SystemDialogSafetySemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
open class BaseTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport(
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 10_000, intervalMs = 1_000)
        },
        lateComposeCustomize = { composeBuilder ->
            composeBuilder.semanticsBehaviorInterceptors = composeBuilder.semanticsBehaviorInterceptors.filter {
                it !is SystemDialogSafetySemanticsBehaviorInterceptor
            }.toMutableList()
        }
    )
) {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val clearDatabase = ClearDatabaseRule()

    @get:Rule
    val clearPreferences = ClearPreferencesRule()

    @Inject
    lateinit var authProvider: AuthProvider

    @Inject
    lateinit var series: SeriesDao

    @Inject
    lateinit var user: UserDao

    @Inject
    lateinit var applicationPreferences: ApplicationPreferences

    @Inject
    lateinit var seriesPreferences: SeriesPreferences

    /**
     * Flag for if the test should start with a logged in user.
     * Defaults to `true`, override to force the user to be logged out.
     */
    open val startLoggedIn: Boolean = true

    /**
     * Initial setup method.
     */
    @Before
    open fun setUp() {
        hiltRule.inject()

        runBlocking {
            applicationPreferences.reset()
            seriesPreferences.reset()
        }

        if (startLoggedIn) {
            authProvider.login()
            user.createTestUser()
        } else {
            authProvider.logout()
        }
    }

    /**
     * Launches the [Activity] using the [ActivityScenario].
     */
    protected fun launchActivity() {
        ActivityScenario.launch(MainActivity::class.java)
        // Not the nicest solution, but it keeps compose views a bit happier when they launch.
        Thread.sleep(200)
    }
}