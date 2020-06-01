package gdscript.lexer

import gdscript.ScriptTokenType

class IdentifierParser : TokenParser() {

    override fun hasToken(position: Int): Boolean {
        var current = position
        if (!Character.isJavaIdentifierStart(charAt(current)))
            return false
        val start = current
        current++
        while (current < end) {
            if (!Character.isJavaIdentifierPart(charAt(current)))
                break
            current++
        }
        tokenInfo.updateData(start, current, ScriptTokenType.IDENTIFIER)
        return true
    }

}