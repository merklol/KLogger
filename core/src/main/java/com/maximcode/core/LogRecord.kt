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

package com.maximcode.core

import com.maximcode.core.utils.Color
import com.maximcode.core.utils.colorize
import com.maximcode.core.utils.isNull
import com.maximcode.core.utils.toStackTraceString

/**
 * Wrapper for log messages logged to its destination.
 */
class LogRecord private constructor(
    val tag: String = "",
    val message: String? = null,
    val throwable: Throwable? = null,
    val color: Color = Color.BLACK) {

    /**
     * Convert [LogRecord] to a log message that prints to the console.
     */
    fun toConsoleMessage(): String {
        return when {
            message.isNullOrBlank() -> "$tag: ${throwable!!.toStackTraceString()}".colorize(color)
            throwable.isNull() -> "$tag: $message".colorize(color)
            else -> "$tag: $message\n${throwable!!.toStackTraceString()}".colorize(color)
        }
    }

    companion object Factory {
        /**
         * Creates a new instance of [LogRecord].
         */
        fun create(priority: Int, tag: String, message: String?, throwable: Throwable?): LogRecord {
            return when (priority) {
                KLogger.VERBOSE -> LogRecord("V/$tag", message, throwable, Color.BLACK)
                KLogger.DEBUG -> LogRecord("D/$tag", message, throwable, Color.BLACK)
                KLogger.INFO -> LogRecord("I/$tag", message, throwable, Color.BLACK)
                KLogger.WARN -> LogRecord("W/$tag", message, throwable, Color.BLUE)
                KLogger.ERROR -> LogRecord("E/$tag", message, throwable, Color.RED)
                KLogger.ASSERT -> LogRecord("A/$tag", message, throwable, Color.RED)
                else -> LogRecord()
            }
        }
    }
}