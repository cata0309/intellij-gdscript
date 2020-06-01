package gdscript.lexer

import kotlin.math.min

abstract class TokenParser {

    var buffer: CharSequence = ""
    var start = 0
    var end = 0
    val tokenInfo = TokenInfo()

    abstract fun hasToken(position: Int): Boolean

    fun charAt(position: Int) = buffer[min(position, buffer.length - 1)]

}