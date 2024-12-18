package kaspresso.tools

import androidx.test.espresso.Espresso

internal fun hideKeyboard() {
    Espresso.closeSoftKeyboard()
}