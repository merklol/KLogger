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

import com.maximcode.core.printers.Printer

object KLogger {
    @Volatile
    private lateinit var printer: Printer
    /**
     * Priority constant for the log method
     */
    const val VERBOSE = 2

    /**
     * Priority constant for the log method
     */
    const val DEBUG = 3

    /**
     * Priority constant for the log method
     */
    const val INFO = 4

    /**
     * Priority constant for the log method
     */
    const val WARN = 5

    /**
     * Priority constant for the log method
     */
    const val ERROR = 6

    /**
     * Priority constant for the log method
     */
    const val ASSERT = 7

    fun init(e: Printer) {
        when {
            !KLogger::printer.isInitialized -> synchronized(this) {
                printer = e
            }
            else -> throw RuntimeException("A KLogger instance already exists.")
        }
    }

    /**
     * Print a verbose log message to the console.
     */
    fun verbose(message: String) = printer.log(VERBOSE, message, null)

    /**
     * Print a verbose log message and exception to the console.
     */
    fun verbose(message: String, throwable: Throwable) = printer.log(VERBOSE, message, throwable)

    /**
     * Print a debug log message to the console.
     */
    fun debug(message: String) = printer.log(DEBUG, message, null)

    /**
     * Print a debug log message and exception to the console.
     */
    fun debug(message: String, throwable: Throwable) = printer.log(DEBUG, message, throwable)

    /**
     * Print an info log message to the console.
     */
    fun info(message: String) = printer.log(INFO, message, null)

    /**
     * Print an info log message and exception to the console.
     */
    fun info(message: String, throwable: Throwable) = printer.log(INFO, message, throwable)

    /**
     * Print a warn log message to the console.
     */
    fun warn(message: String) = printer.log(WARN, message, null)

    /**
     * Print a warn exception to the console.
     */
    fun warn(throwable: Throwable) = printer.log(WARN, null, throwable)

    /**
     * Print a warn log message and exception to the console.
     */
    fun warn(message: String, throwable: Throwable) = printer.log(WARN, message, throwable)

    /**
     * Print an error log message to the console.
     */
    fun error(message: String) = printer.log(ERROR, message, null)

    /**
     * Print an error log message and exception to the console.
     */
    fun error(message: String, throwable: Throwable) = printer.log(ERROR, message, throwable)

    /**
     * Print an assert log message to the console.
     */
    fun wtf(message: String) = printer.log(ASSERT, message, null)

    /**
     * Print an assert exception to the console.
     */
    fun wtf(throwable: Throwable) = printer.log(ASSERT, null, throwable)

    /**
     * Print an assert log message and exception to the console.
     */
    fun wtf(message: String, throwable: Throwable) = printer.log(ASSERT, message, throwable)
}
