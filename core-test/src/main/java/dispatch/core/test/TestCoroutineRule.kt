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

package dispatch.core.test

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.rules.*
import org.junit.runner.*
import kotlin.coroutines.*

/**
 * A basic JUnit 4 [TestRule] which creates a new [TestProvidedCoroutineScope] for each test,
 * sets [Dispatchers.Main], and calls [cleanupTestCoroutines] afterwards.
 *
 * The rule itself implements [TestProvidedCoroutineScope], so it can be used as follows:
 *
 * ```
 * class SomeTest {
 *
 *   @JvmField @Rule val testScope = TestCoroutineRule()
 *
 *   @Test
 *   fun testThings() = runBlocking {
 *     testScope.launch { ... }
 *   }
 * }
 * ```
 *
 * ### Before the test:
 * * [Dispatchers.Main] is set to the [TestCoroutineDispatcher] used by the [CoroutineContext].
 *
 * ### After the test:
 * * [cleanupTestCoroutines] is called to ensure there are no leaking coroutines.  Any unfinished coroutine
 * will throw an [UncompletedCoroutinesError].
 * * [Dispatchers.Main] is reset via [Dispatchers.resetMain].
 *
 * ### Requires JUnit 4.
 * ```
 * dependencies {
 *   testImplementation "junit:junit:4.12"
 *   -- or --
 *   testImplementation "org.junit.vintage:junit-vintage-engine:5.5.1"
 * }
 * ```
 * @param factory *optional* factory for a custom [TestProvidedCoroutineScope].  If a factory is not provided,
 * the resultant scope uses the same [TestCoroutineDispatcher] for each property in its [TestDispatcherProvider]
 *
 * @see TestRule
 * @see TestCoroutineScope
 * @see TestProvidedCoroutineScope
 */
@ExperimentalCoroutinesApi
class TestCoroutineRule(
  factory: () -> TestProvidedCoroutineScope = { TestProvidedCoroutineScope() }
) : TestWatcher(), TestProvidedCoroutineScope by factory() {

  val dispatcher = coroutineContext[ContinuationInterceptor] as TestCoroutineDispatcher

  override fun starting(description: Description?) {
    Dispatchers.setMain(dispatcher)
  }

  override fun finished(description: Description?) {
    cleanupTestCoroutines()
    Dispatchers.resetMain()
  }
}
