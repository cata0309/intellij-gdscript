package gdscript.lexer

import com.intellij.psi.tree.IElementType
import kotlin.math.min

class PrefixToSuffixParser(
    private val prefixes: Set<Char>,
    private val suffixes: Set<Char>,
    private val resultToken: IElementType
) : TokenParser() {

    override fun hasToken(position: Int): Boolean {
        if (charAt(position) in prefixes) {
            var current = position
            do {
                current += 1
                if (charAt(current) in suffixes) {
                    val max = min(current + 1, buffer.length)
                    tokenInfo.updateData(position, max, resultToken)
                    return true
                }
            } while (current <= end)
        }
        return false
    }

}