/*
 * MIT License
 *
 * Copyright (c) 2020 Maxim Smolyakov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.maximcode.klogger.printers

import com.maximcode.klogger.KLogger
import com.maximcode.klogger.LogRecord
import com.maximcode.klogger.rules.DebugRule
import com.maximcode.klogger.rules.Rule
import com.maximcode.klogger.tags.KLoggerTag
import com.maximcode.klogger.tags.Tag
import com.maximcode.klogger.templates.BaseTemplate

/**
 * Implementation of [Printer] interface for console applications.
 */
class ConsolePrinter(override val rule: Rule = DebugRule()): Printer {
    override val tag: Tag
        get() = KLoggerTag(
            mutableListOf(
                ConsolePrinter::class.java.name,
                KLogger::class.java.name,
                KLoggerTag::class.java.name,
                String::class.java.name,
                StringBuilder::class.java.name
            ), BaseTemplate()
        )

    override fun log(priority: Int, message: String?, throwable: Throwable?) {
        if(rule.isPrintable(priority)) {
            println(
                LogRecord
                .create(priority, tag.toString(), message, throwable)
                .toConsoleMessage()
            )
        }
    }
}