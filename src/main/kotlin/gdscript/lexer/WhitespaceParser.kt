package gdscript.lexer

import gdscript.ScriptTokenType

class WhitespaceParser : TokenParser() {

    override fun hasToken(position: Int): Boolean {
        var position = position
        if (!Character.isWhitespace(buffer[position])) return false
        val start = position
        position++
        while (position < end && Character.isWhitespace(buffer[position])) {
            position++
        }
        tokenInfo.updateData(start, position, ScriptTokenType.WHITESPACE)
        return true
    }

}