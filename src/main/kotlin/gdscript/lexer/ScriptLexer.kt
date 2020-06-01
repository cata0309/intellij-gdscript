package gdscript.lexer

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import gdscript.ScriptTokenType
import gdscript.ScriptTokenType.UNKNOWN_CHARACTER
import gdscript.completion.sources.CompletionDictionary

class ScriptLexer : LexerBase() {

    private val parsers = listOf(
        TrieParser(setOf("\n"), ScriptTokenType.END_OF_LINE),
        WhitespaceParser(),
        TrieParser(CompletionDictionary.NON_PRIMITIVE_CLASS_NAMES, ScriptTokenType.CLASS_NAME),
        TrieParser(CompletionDictionary.KEYWORDS + CompletionDictionary.VARIABLE_KEYWORDS + CompletionDictionary.FUNCTION_NAMES, ScriptTokenType.KEYWORD),
        TrieParser(CompletionDictionary.LANGUAGE_CONSTANT_NAMES, ScriptTokenType.CONSTANT),
        TrieParser(setOf(","), ScriptTokenType.COMMA),
        TrieParser(setOf("->"), ScriptTokenType.ARROW),
        TrieParser(setOf(":"), ScriptTokenType.COLON),
        TrieParser(setOf("."), ScriptTokenType.DOT),
        TrieParser(setOf(";"), ScriptTokenType.SEMICOLON),
        PrefixToSuffixParser(setOf('\"'), setOf('\"', '\n'), ScriptTokenType.DOUBLE_QUOTED_STRING),
        PrefixToSuffixParser(setOf('\''), setOf('\'', '\n'), ScriptTokenType.SINGLE_QUOTED_STRING),
        IdentifierParser(),
        NumberParser(),
        PrefixToSuffixParser(setOf('#'), setOf('\n'), ScriptTokenType.LINE_COMMENT),
        TrieParser(setOf("{"), ScriptTokenType.BRACE_LEFT),
        TrieParser(setOf("}"), ScriptTokenType.BRACE_RIGHT),
        TrieParser(setOf("("), ScriptTokenType.PARENTH_LEFT),
        TrieParser(setOf(")"), ScriptTokenType.PARENTH_RIGHT),
        TrieParser(setOf("["), ScriptTokenType.BRACKET_LEFT),
        TrieParser(setOf("]"), ScriptTokenType.BRACKET_RIGHT)
    )
    private lateinit var buffer: CharSequence
    private var start = 0
    private var end = 0
    private var tokenStart = 0
    private var tokenEnd = 0
    private var tokenType: IElementType? = null
    private var position = 0

    override fun start(buffer: CharSequence, start: Int, end: Int, state: Int) {
        this.buffer = buffer
        this.start = start
        this.end = end
        position = start
        for (tokenParser in parsers) {
            tokenParser.buffer = buffer
            tokenParser.start = start
            tokenParser.end = end
        }
        advance()
    }

    override fun advance() {
        if (position >= end) {
            tokenStart = position
            tokenEnd = position
            tokenType = null
            return
        }
        var found = false
        for (parser in parsers) {
            if (parser.hasToken(position)) {
                tokenStart = parser.tokenInfo.start
                tokenEnd = parser.tokenInfo.end
                tokenType = parser.tokenInfo.type
                found = true
                break
            }
        }
        if (!found) {
            tokenStart = position
            tokenEnd = position + 1
            tokenType = UNKNOWN_CHARACTER
        }
        position = tokenEnd
    }

    override fun getState(): Int = 0

    override fun getTokenType(): IElementType? = tokenType

    override fun getTokenStart(): Int = tokenStart

    override fun getTokenEnd(): Int = tokenEnd

    override fun getBufferSequence(): CharSequence = buffer

    override fun getBufferEnd(): Int = end

}