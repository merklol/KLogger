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

package com.maximcode.klogger.tags

import com.maximcode.klogger.templates.Template
import com.maximcode.klogger.SimpleClassName

/**
 * Default implementation of [Tag] interface.
 */
class KLoggerTag(private val exclusionList: List<String>, private val template: Template): Tag {
    override fun toString() = run {
        Throwable().stackTrace.first { it.className !in exclusionList }.let {
            template.apply(
                SimpleClassName(it.className).toString(),
                it.lineNumber.toString(),
                it.methodName
            )
        }
    }
}