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

package com.maximcode.klogger

import com.maximcode.klogger.utils.Color
import com.maximcode.klogger.utils.colorize
import com.maximcode.klogger.utils.toStackTraceString
import org.junit.Assert.assertEquals
import org.junit.Test

class LogRecordTest {
    private val tag = "KLogger"
    private val message = "Test message"

    @Test
    fun `when priority INFO, tag should contain "I" and color should be black`() {
        val record = LogRecord.create(KLogger.INFO, tag, message, null)
        assertEquals("I/$tag", record.tag)
        assertEquals(Color.BLACK, record.color)
    }

    @Test
    fun `when priority VERBOSE, tag should contain "V" and color should be black`() {
        val record = LogRecord.create(KLogger.VERBOSE, tag, message, null)
        assertEquals("V/$tag", record.tag)
        assertEquals(Color.BLACK, record.color)
    }

    @Test
    fun `when priority ASSERT, tag should contain "A" and color should be red`() {
        val record = LogRecord.create(KLogger.ASSERT, tag, message, null)
        assertEquals("A/$tag", record.tag)
        assertEquals(Color.RED, record.color)
    }

    @Test
    fun `when priority WARN, tag should contain "W" and color should be blue`() {
        val record = LogRecord.create(KLogger.WARN, tag, message, null)
        assertEquals("W/$tag", record.tag)
        assertEquals(Color.BLUE, record.color)
    }

    @Test
    fun `when priority DEBUG, tag should contain "D" and color should be black`() {
        val record = LogRecord.create(KLogger.DEBUG, tag, message, null)
        assertEquals("D/$tag", record.tag)
        assertEquals(Color.BLACK, record.color)
    }

    @Test
    fun `when priority ERROR, tag should contain "E" and color should be red`() {
        val record = LogRecord.create(KLogger.ERROR, tag, message, null)
        assertEquals("E/$tag", record.tag)
        assertEquals(Color.RED, record.color)
    }

    @Test
    fun `when "toConsoleMessage()" called and throwable is null, should only print the message`() {
        val expected = "D/$tag: $message".colorize(Color.BLACK)
        val record = LogRecord.create(KLogger.DEBUG, tag, message, null)
        assertEquals(expected, record.toConsoleMessage())
    }

    @Test
    fun `when "toConsoleMessage()" called and message is null, should only print the throwable`() {
        val exception = RuntimeException(message)
        val expected = "D/$tag: ${exception.toStackTraceString()}".colorize(Color.BLACK)
        val record = LogRecord.create(KLogger.DEBUG, tag, null, exception)
        assertEquals(expected, record.toConsoleMessage())
    }

    @Test
    fun `when "toConsoleMessage()" called, should print the message and throwable`() {
        val exception = RuntimeException(message)
        val expected = "D/$tag: $message\n${exception.toStackTraceString()}".colorize(Color.BLACK)
        val record = LogRecord.create(KLogger.DEBUG, tag, message, exception)
        assertEquals(expected, record.toConsoleMessage())
    }
}