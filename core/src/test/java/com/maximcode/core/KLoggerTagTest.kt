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

import com.maximcode.core.printers.ConsolePrinter
import com.maximcode.core.tags.KLoggerTag
import com.maximcode.core.templates.BaseTemplate
import org.junit.Assert.assertEquals
import org.junit.Test

class KLoggerTagTest {
    private val exclusionList = mutableListOf(
        ConsolePrinter::class.java.name,
        KLogger::class.java.name,
        KLoggerTag::class.java.name,
    )
    @Test
    fun `when toString() called, should return this class name + line number + this method name`() {
        val tag = KLoggerTag(exclusionList, BaseTemplate())
        val result = tag.toString()
        assertEquals(
            "KLogger: KLoggerTagTest.kt:42.when toString() called, " +
                    "should return this class name + line number + this method name()", result
        )
    }
}