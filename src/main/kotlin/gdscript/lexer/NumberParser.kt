package gdscript.lexer

import gdscript.ScriptTokenType

class NumberParser() : TokenParser() {

    override fun hasToken(position: Int): Boolean {
        var current = position
        val start = current
        if (!Character.isDigit(charAt(current)))
            return false
        current++
        while (current < end) {
            if (!Character.isDigit(charAt(current)))
                break
            current++
        }
        if (charAt(current) == '.') {
            val dotPosition = current
            current++
            if (!Character.isDigit(charAt(current))) {
                current = dotPosition
            } else {
                while (current < end) {
                    if (!Character.isDigit(charAt(current)))
                        break
                    current++
                }
                val finalChar = charAt(current)
                if (!isTail(finalChar) && Character.isLetter(finalChar)) {
                    current = dotPosition
                }
            }
        }
        if (charAt(current) == '-' && charAt(current + 1) == 'e')
            current += 2
        while (Character.isDigit(charAt(current)) && current < end)
            current++
        tokenInfo.updateData(start, current, ScriptTokenType.NUMBER)
        return true
    }

    private fun isTail(char: Char): Boolean =
        Character.isDigit(char) || "-e".indexOf(char) >= 0

}