/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea

import com.intellij.util.text.VersionComparatorUtil
import junit.framework.TestCase
import org.junit.Assert

class VersionComparatorForKotlinTest : TestCase() {
    fun testRcVersion1() {
        outdated("1.2.0-rc-39", "1.2.0-rc-40")
        outdated("1.2.0-rc-39", "1.2.0-rc-100")
        outdated("1.2.0-rc-39", "1.2.0")
        outdated("1.2.0-rc-39", "1.2.1-eap-1")
        outdated("1.2.0-rc-39", "1.2.1-rc-10")
        outdated("1.2.0-rc-39", "1.2.1")
        outdated("1.2.0-rc-39", "1.3.0")
        outdated("1.2.0-rc-39", "2.0.0")

        notOutdated("1.2.0-rc-39", "1.2.0-rc-39")
        notOutdated("1.2.0-rc-39", "1.2.0-rc-38")
        notOutdated("1.2.0-rc-39", "1.2.0-eap-100")
        notOutdated("1.2.0-rc-39", "1.2.0-eap-55")
        notOutdated("1.2.0-rc-39", "1.2.0-eap-22")
        notOutdated("1.2.0-rc-39", "1.1.60")
        notOutdated("1.2.0-rc-39", "1.1.60-rc-99")
        notOutdated("1.2.0-rc-39", "1.1.60-eap-99")
        notOutdated("1.2.0-rc-39", "1.1.60-eap-99")
        notOutdated("1.2.0-rc-39", "1.0.0")
    }

    private fun outdated(version1: String, version2: String) {
        Assert.assertTrue("Should be outdated: version=$version1, test version=$version2",
                          VersionComparatorUtil.compare(version2, version1) > 0)
    }

    private fun notOutdated(version1: String, version2: String) {
        Assert.assertTrue("Should NOT be outdated: version=$version1, test version=$version2",
                           VersionComparatorUtil.compare(version2, version1) <= 0)
    }
}