package gdscript

import com.intellij.psi.tree.IElementType

object ScriptTokenType {

    val KEYWORD = IElementType("KEYWORD", ScriptLanguage)

    val CLASS_NAME = IElementType("CLASS_NAME", ScriptLanguage)

    val DOUBLE_QUOTED_STRING = IElementType("DOUBLE_QUOTED_STRING", ScriptLanguage)
    val SINGLE_QUOTED_STRING = IElementType("SINGLE_QUOTED_STRING", ScriptLanguage)

    val NUMBER = IElementType("NUMBER", ScriptLanguage)
    val IDENTIFIER = IElementType("IDENTIFIER", ScriptLanguage)
    val CONSTANT = IElementType("CONSTANT", ScriptLanguage)
    val LINE_COMMENT = IElementType("LINE_COMMENT", ScriptLanguage)
    val WHITESPACE = IElementType("WHITESPACE", ScriptLanguage)

    val END_OF_LINE = IElementType("END_OF_LINE", ScriptLanguage)

    val COMMA = IElementType("COMMA", ScriptLanguage)
    val ARROW = IElementType("ARROW", ScriptLanguage)
    val COLON = IElementType("COLON", ScriptLanguage)
    val SEMICOLON = IElementType("SEMICOLON", ScriptLanguage)
    val DOT = IElementType("DOT", ScriptLanguage)

    val BRACE_LEFT = IElementType("BRACE_LEFT", ScriptLanguage)
    val BRACE_RIGHT = IElementType("BRACE_RIGHT", ScriptLanguage)
    
    val BRACKET_LEFT = IElementType("BRACKET_LEFT", ScriptLanguage)
    val BRACKET_RIGHT = IElementType("BRACKET_RIGHT", ScriptLanguage)
    
    val PARENTH_LEFT = IElementType("PARENTH_LEFT", ScriptLanguage)
    val PARENTH_RIGHT = IElementType("PARENTH_RIGHT", ScriptLanguage)
    
    val UNKNOWN_CHARACTER = IElementType("UNKNOWN_CHARACTER", ScriptLanguage)

}

/*
package gdscript

import gdscript.ScriptLexer.*
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory.createTokenSet

object ScriptTokenSet {

    val KEYWORDS = create(
        IF,
        ELIF,
        ELSE,
        NETWORK_MODIFIER,
        MATCH,
        EXPORT,
        ONREADY,
        VAR,
        SETGET,
        CONST,
        STATIC,
        FUNC,
        FOR,
        WHILE,
        CLASS,
        EXTENDS,
        CLASS_NAME,
        ENUM,
        RETURN,
        SIGNAL,
        NOT,
        CAST_OPERATOR,
        KEYWORD_OPERATOR,
        KEYWORD_VALUE,
        KEYWORD_FLOW,
        PRIMITIVE,
        FUNCTION_IDENTIFIER
    )
    val WHITESPACES = create(WHITESPACE)
    val LINE_COMMENTS = create(LINE_COMMENT)
    val IDENTIFIERS = create(IDENTIFIER)
    val NODES = create(NODE)
    val COMMAS = create(COMMA)
    val SEMICOLONS = create(SEMICOLON)
    val COLONS = create(COLON)
    val DOTS = create(DOT)
    val NUMBERS = create(NUMBER)
    val STRINGS = create(STRING)
    val RESOURCES = create(RESOURCE, USER_RESOURCE)
    val CONSTANTS = create(CONSTANT_IDENTIFIER)
    val CLASSES = create(CLASS_IDENTIFIER)
    val OPERATION_SIGNS = create(
        ARROW,
        MINUS,
        OPERATOR,
        NOT_BITWISE,
        NOT_BOOLEAN
    )
    val BRACES = create(
        BRACE_OPEN,
        BRACE_CLOSE
    )
    val PARENTHESES = create(
        PARENTHES_OPEN,
        PARENTHES_CLOSE
    )
    val BRACKETS = create(
        BRACKET_OPEN,
        BRACKET_CLOSE
    )

    private fun create(vararg token: Int) =
        createTokenSet(ScriptLanguage, *token)!!

}

 */