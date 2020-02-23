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
import dispatch.android.lifecycle.ViewLifecycleScopePolicy.*
import dispatch.core.test.*
import io.kotlintest.*
import io.kotlintest.matchers.types.*
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.*
import org.robolectric.*
import org.robolectric.annotation.*

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
@ExperimentalCoroutinesApi
internal class WithViewLifecycleScopeOnceTest {

  @JvmField
  @Rule
  val rule = TestCoroutineRule()

  @JvmField
  @Rule
  val instantTaskRule = InstantTaskExecutorRule()

  val fragmentLifecycleOwner = FakeLifecycleOwner(rule.dispatcherProvider.main)
  val viewLifecycleOwner = FakeLifecycleOwner(rule.dispatcherProvider.main)

  @Before
  fun setUp() {
    fragmentLifecycleOwner.start()
    viewLifecycleOwner.create()
  }

  @Test
  fun `when the Fragment lifecycle is only created, lambda should be invoked`() {

    val createdFragmentLifecycleOwner = FakeLifecycleOwner(rule.dispatcherProvider.main)
    createdFragmentLifecycleOwner.create()

    var invocations = 0

    val fragment =
      TestFragment(createdFragmentLifecycleOwner) { withViewLifecycleScope(policy = ONCE) { invocations++ } }

    invocations shouldBe 0

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    invocations shouldBe 0
  }

  @Test
  fun `when the livedata goes from null to non-null, lambda should be invoked`() {

    var invocations = 0

    val fragment =
      TestFragment(fragmentLifecycleOwner) { withViewLifecycleScope(policy = ONCE) { invocations++ } }

    invocations shouldBe 0

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    invocations shouldBe 1
  }

  @Test
  fun `when the livedata goes from null to non-null, lambda should only be invoked once`() {

    var invocations = 0

    val fragment =
      TestFragment(fragmentLifecycleOwner) { withViewLifecycleScope(policy = ONCE) { invocations++ } }

    invocations shouldBe 0

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    invocations shouldBe 1

    fragment.setFakeViewLifecycleOwner(null)
    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    invocations shouldBe 1

    fragment.setFakeViewLifecycleOwner(null)
    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    invocations shouldBe 1

  }

  @Test
  fun `when the livedata gets set to non-null, lambda should only be invoked once`() {

    var invocations = 0

    val fragment =
      TestFragment(fragmentLifecycleOwner) { withViewLifecycleScope(policy = ONCE) { invocations++ } }

    invocations shouldBe 0

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    invocations shouldBe 1

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    invocations shouldBe 1

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    invocations shouldBe 1

  }

  @Test
  fun `when the view lifecycle is destroyed, lambda should be cancelled`() {

    lateinit var job: Job

    val fragment = TestFragment(fragmentLifecycleOwner) {
      withViewLifecycleScope(policy = ONCE) {
        job = launch {
          // never completes, so the Job should be active until the scope is destroyed
          CompletableDeferred<Int>().await()
        }
      }
    }

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    viewLifecycleOwner.create()
    viewLifecycleOwner.destroy()

    job.isCancelled shouldBe true

  }

  @Test
  fun `when the fragment lifecycle is destroyed, lambda should not be cancelled`() {

    lateinit var job: Job

    val fragment = TestFragment(fragmentLifecycleOwner) {
      withViewLifecycleScope(policy = ONCE) {
        job = launch {
          // never completes, so the Job should be active until the scope is destroyed
          CompletableDeferred<Int>().await()
        }
      }
    }

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    fragmentLifecycleOwner.create()
    fragmentLifecycleOwner.destroy()

    job.isActive shouldBe true

  }

  @Test
  fun `the lambda's LifecycleScope should correspond to the view lifecycle`() = runBlocking {

    lateinit var job: Job

    val fragment = TestFragment(fragmentLifecycleOwner) {
      withViewLifecycleScope(policy = ONCE) {
        job = launch {

          lifecycle shouldBeSameInstanceAs viewLifecycleOwner.lifecycle

        }
      }
    }

    fragment.setFakeViewLifecycleOwner(viewLifecycleOwner)

    job.join()

  }
}
