package kaspresso.tools

internal fun passwordMask(string: String): String =
    StringBuilder().apply {
        repeat(string.length) { append('•') }
    }.toString()

fun randString(length: Int): String =
    StringBuilder().apply {
        repeat(length) {
            append(CharRange('a', 'z').random())
        }
    }.toString()