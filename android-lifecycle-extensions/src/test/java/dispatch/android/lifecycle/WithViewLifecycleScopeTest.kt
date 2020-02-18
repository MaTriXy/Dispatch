/*
 * Copyright (C) 2020 Rick Busarow
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dispatch.android.lifecycle

import androidx.arch.core.executor.testing.*
import androidx.lifecycle.*
import dispatch.core.test.*
import io.kotlintest.*
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.*
import org.robolectric.*
import org.robolectric.annotation.*

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
@ExperimentalCoroutinesApi
internal class WithViewLifecycleScopeTest {

  @JvmField
  @Rule
  val rule = TestCoroutineRule()

  @JvmField
  @Rule
  val instantTaskRule = InstantTaskExecutorRule()

  @Test
  fun `lambda should be invoked when the livedata is set to non-null`() {

    val fragmentLifecycleOwner = FakeLifecycleOwner(rule.dispatcherProvider.main)
    val viewLifecycleOwner = FakeLifecycleOwner(rule.dispatcherProvider.main)

    val fragment = TestFragment(fragmentLifecycleOwner)

    fragment.invocations shouldBe 0

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    fragment.invocations shouldBe 1

  }
}

@ExperimentalCoroutinesApi
class TestFragment(
  fragmentLifecycleOwner: LifecycleOwner
) : FakeFragment(fragmentLifecycleOwner) {

  var invocations = 0
    private set

  init {
    withViewLifecycleScope {
      invocations++
    }
  }
}
