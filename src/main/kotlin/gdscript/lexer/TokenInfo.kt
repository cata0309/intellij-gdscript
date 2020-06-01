package gdscript.lexer

import com.intellij.psi.tree.IElementType

class TokenInfo {
    var start = 0
    var end = 0
    var type: IElementType? = null

    fun updateData(tokenStart: Int, tokenEnd: Int, tokenType: IElementType?) {
        start = tokenStart
        end = tokenEnd
        type = tokenType
    }

    fun updateData(info: TokenInfo) {
        start = info.start
        end = info.end
        type = info.type
    }
}