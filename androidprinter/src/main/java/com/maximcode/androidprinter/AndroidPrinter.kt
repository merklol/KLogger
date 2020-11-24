package com.maximcode.androidprinter

import android.util.Log
import com.maximcode.core.KLogger
import com.maximcode.core.printers.Printer
import com.maximcode.core.rules.DebugRule
import com.maximcode.core.rules.Rule
import com.maximcode.core.tags.KLoggerTag
import com.maximcode.core.tags.Tag
import com.maximcode.core.utils.isNull
import com.maximcode.core.utils.toStackTraceString

/**
 * Implementation of [Printer] interface for Android applications.
 */
public class AndroidPrinter(override val rule: Rule = DebugRule()): Printer {
    override val tag: Tag
        get() = KLoggerTag(mutableListOf(
        AndroidPrinter::class.java.name,
        KLogger::class.java.name,
        KLoggerTag::class.java.name
    ), ClickableTemplate()
    )

    override fun log(priority: Int, message: String?, throwable: Throwable?) {
        if(rule.isPrintable(priority)) {
            when {
                message.isNullOrEmpty() -> Log.println(priority, tag.toString(), throwable!!.toStackTraceString())
                throwable.isNull() -> Log.println(priority, tag.toString(), message)
                else -> Log.println(priority, tag.toString(), "$message\n${throwable!!.toStackTraceString()}")
            }
        }
    }
}