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
import androidx.fragment.app.*
import androidx.lifecycle.*
import dispatch.core.test.*
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.*
import org.robolectric.*
import org.robolectric.annotation.*
import kotlin.properties.*

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
  fun clickFullscreen_ShouldDelegateToViewModel() {
    println("hello")

    TestFragment(FakeLifecycleOwner(rule.dispatcherProvider.main))

  }
}

@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
class TestFragment(
  private val fragmentLifecycleOwner: FakeLifecycleOwner
) : Fragment(), LifecycleOwner by fragmentLifecycleOwner {

  var invocations = 0
    private set

  init {

    withViewLifecycleScope {

      invocations++
    }
  }

  var fakeViewLifecycleOwner by Delegates.observable<FakeLifecycleOwner?>(null) { _, _, new ->
    fakeViewLifecycleOwnerLiveData.value = new
  }

  private val fakeViewLifecycleOwnerLiveData: MutableLiveData<FakeLifecycleOwner?> =
    MutableLiveData(fakeViewLifecycleOwner)

  override fun getViewLifecycleOwner(): LifecycleOwner = fakeViewLifecycleOwner!!

  override fun getViewLifecycleOwnerLiveData() =
    fakeViewLifecycleOwnerLiveData as LiveData<LifecycleOwner>

  override fun getLifecycle(): Lifecycle = fragmentLifecycleOwner.lifecycle
}

