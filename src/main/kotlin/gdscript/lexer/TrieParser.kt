package gdscript.lexer

import com.intellij.psi.tree.IElementType
import kotlin.math.min

class TrieParser(
    words: Set<String>,
    private val type: IElementType
) : TokenParser() {

    private val trieRoot = Node()

    init {
        for (word in words) {
            var currentNode = trieRoot
            for (char in word) {
                if (currentNode.children[char] == null) {
                    currentNode.children[char] = Node()
                }
                currentNode = currentNode.children[char]!!
            }
            currentNode.word = word
        }
    }

    override fun hasToken(position: Int): Boolean {
        var current = position
        var node = trieRoot
        do {
            val char = buffer[min(current, buffer.length - 1)]
            val nextNode = node.children[char]
                ?: break
            node = nextNode
            current += 1
        } while (current <= end)
        if (node.word != null) {
            tokenInfo.updateData(position, current, type)
            return true
        }
        return false
    }

    private data class Node(
        var word: String? = null,
        val children: MutableMap<Char, Node> = mutableMapOf()
    )

}
