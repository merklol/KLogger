package com.maximcode.androidprinter

import android.util.Log
import com.maximcode.klogger.KLogger
import com.maximcode.klogger.printers.Printer
import com.maximcode.klogger.rules.DebugRule
import com.maximcode.klogger.rules.Rule
import com.maximcode.klogger.tags.KLoggerTag
import com.maximcode.klogger.tags.Tag
import com.maximcode.klogger.utils.isNull
import com.maximcode.klogger.utils.toStackTraceString

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