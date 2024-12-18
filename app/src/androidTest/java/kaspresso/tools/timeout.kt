package kaspresso.tools

import io.github.kakaocup.kakao.screen.Screen.Companion.idle

internal fun timeout (period: Long = 100, coll: Int = 10) {
    repeat(coll) {
        idle(period)
    }
}