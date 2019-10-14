package script.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType.BASIC
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.util.ProcessingContext
import script.psi.elements.PsiInvoke
import script.psi.elements.PsiType
import script.psi.elements.PsiValue


class CompletionContributor : com.intellij.codeInsight.completion.CompletionContributor() {

    init {
        extend(BASIC, INSIDE_TYPE, provideAll(PRIMITIVE_TYPES))
        extend(BASIC, INSIDE_TYPE, provideAll(OBJECT_TYPES))
        extend(BASIC, INSIDE_INVOKE, provideAll(FUNCTION_CALLS))
        extend(BASIC, INSIDE_INVOKE, provideAll(CONSTRUCTOR_CALLS))
        extend(BASIC, INSIDE_VALUE, provide(SELF))
        extend(BASIC, INSIDE_VALUE, provide(TRUE))
        extend(BASIC, INSIDE_VALUE, provide(FALSE))
        extend(BASIC, INSIDE_VALUE, provide(NULL))
        extend(BASIC, INSIDE_VALUE, provideAll(SINGLETONS))
        extend(BASIC, INSIDE_VALUE, provideAll(CONSTANTS))
        extend(BASIC, INSIDE_VALUE, provideAll(FUNCTION_CALLS))
        extend(BASIC, INSIDE_VALUE, provideAll(CONSTRUCTOR_CALLS))
        extend(BASIC, AFTER_NEWLINE, provide(VAR))
        extend(BASIC, AFTER_NEWLINE, provide(CONST))
        extend(BASIC, AFTER_NEWLINE, provide(FOR))
        extend(BASIC, AFTER_NEWLINE, provide(WHILE))
        extend(BASIC, AFTER_NEWLINE, provide(IF))
        extend(BASIC, AFTER_NEWLINE, provide(ELIF))
        extend(BASIC, AFTER_NEWLINE, provide(ELSE))
        extend(BASIC, AFTER_NEWLINE, provide(RETURN))
        extend(BASIC, AFTER_NEWLINE, provide(FUNC))
        extend(BASIC, AFTER_NEWLINE, provide(EXPORT))
        extend(BASIC, AFTER_NEWLINE, provide(STATIC))
        extend(BASIC, AFTER_NEWLINE, provide(ENUM))
        extend(BASIC, AFTER_NEWLINE, provide(SIGNAL))
        extend(BASIC, AFTER_NEWLINE, provide(CLASS))
        extend(BASIC, AFTER_NEWLINE, provide(EXTENDS))
        extend(BASIC, AFTER_NEWLINE, provide(CLASS_NAME))
        extend(BASIC, AFTER_NEWLINE, provide(MATCH))
        extend(BASIC, AFTER_NEWLINE, provide(TOOL))
        extend(BASIC, AFTER_NEWLINE, provide(PASS))
        extend(BASIC, AFTER_EXPORT_KEYWORD, provide(VAR))
        extend(BASIC, AFTER_STATIC_KEYWORD, provide(FUNC))
    }

    private fun provide(lookup: LookupElement) =
        provideAll(listOf(lookup))

    private fun provideAll(lookups: List<LookupElement>) =
        object : CompletionProvider<CompletionParameters>() {
            override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
                result.addAllElements(lookups)
            }
        }

    private companion object {

        private val INSIDE_TYPE = psiElement().inside(PsiType::class.java)
        private val INSIDE_INVOKE = psiElement().inside(PsiInvoke::class.java)
        private val INSIDE_VALUE = psiElement().inside(PsiValue::class.java)
        private val AFTER_NEWLINE = psiElement().afterLeaf("\n")
        private val AFTER_EXPORT_KEYWORD = psiElement().afterLeaf("export")
        private val AFTER_STATIC_KEYWORD = psiElement().afterLeaf("static")

    }

}
