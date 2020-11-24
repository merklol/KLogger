package com.maximcode.androidprinter

import android.os.Build
import com.maximcode.core.KLogger
import com.maximcode.core.templates.Template

/**
 * Implementation of [Template] interface for Android applications.
 */
public class ClickableTemplate: Template {
    override fun apply(vararg args: String): String {
        val (className, lineNumber, methodName) = args
        return "${KLogger::class.java.simpleName}: (${shrinkClassName(className)}.kt:$lineNumber).$methodName()"
    }

    private fun shrinkClassName(className: String) = when {
        className.length <= 23 || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> className
        else -> className.substring(0, 23)
    }
}