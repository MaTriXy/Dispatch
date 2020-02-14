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

import androidx.fragment.app.*
import androidx.fragment.app.testing.*
import dispatch.core.test.*
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.*
import org.robolectric.*
import org.robolectric.annotation.*

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = "../../../AndroidManifest.xml")
internal class WithViewLifecycleScopeTest {

  @JvmField
  @Rule
  val rule = TestCoroutineRule()




  @Test
  fun clickFullscreen_ShouldDelegateToViewModel() {
    FragmentScenario.launchInContainer(TestFragment::class.java, null,
      object : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
          return TestFragment()
        }
      })

  }
}

@ExperimentalCoroutinesApi
class TestFragment : Fragment() {

  var invocations = 0

  init {

    withViewLifecycleScope {

      invocations++
    }
  }
}
