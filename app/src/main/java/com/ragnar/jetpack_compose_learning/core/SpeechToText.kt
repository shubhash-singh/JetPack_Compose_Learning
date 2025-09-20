package com.ragnar.jetpack_compose_learning.core

object SpeechToText {
    public fun getLanguageFromDropdown(language: String): String {
        return language
    }

    fun setMessageForText(language: String): String {
        return "Selected language is: $language"
    }
}