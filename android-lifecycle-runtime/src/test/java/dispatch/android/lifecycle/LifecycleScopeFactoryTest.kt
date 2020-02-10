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

import dispatch.core.*
import dispatch.internal.test.*
import io.kotlintest.*
import io.kotlintest.matchers.types.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*
import kotlin.coroutines.*

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class LifecycleScopeFactoryTest {

  val job = Job()
  val dispatcher = newSingleThreadContext("single thread dispatcher")
  val dispatcherProvider = DispatcherProvider()
  val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
  val coroutineName = CoroutineName("name")

  val originContext = job + dispatcher + dispatcherProvider + exceptionHandler + coroutineName

  val mainDispatcher = newSingleThreadContext("main dispatcher")

  @BeforeAll
  fun beforeAll() {
    Dispatchers.setMain(mainDispatcher)
  }

  @AfterEach
  fun afterEach() {
    LifecycleScopeFactory.reset()
  }

  @AfterAll
  fun afterAll() {
    Dispatchers.resetMain()
  }

  @Test
  fun `default factory should be a default MainImmediateCoroutineScope`() = runBlockingTest {

    val scope = LifecycleScopeFactory.create()

    scope.coroutineContext[DispatcherProvider]!!.shouldBeTypeOf<DefaultDispatcherProvider>()

    scope.coroutineContext[Job]!!.shouldBeSupervisorJob()

    scope.coroutineContext[ContinuationInterceptor] shouldBe Dispatchers.Main

    scope.shouldBeInstanceOf<MainImmediateCoroutineScope>()
  }

  @Test
  fun `a custom factory should be used after being set`() = runBlockingTest {

    LifecycleScopeFactory.set { MainImmediateCoroutineScope(originContext) }

    val scope = LifecycleScopeFactory.create()

    scope.coroutineContext shouldEqualFolded originContext + mainDispatcher
  }

  @Test
  fun `reset after setting a custom factory should return to the default`() = runBlockingTest {

    LifecycleScopeFactory.set { MainImmediateCoroutineScope(originContext) }

    val custom = LifecycleScopeFactory.create()

    custom.coroutineContext shouldEqualFolded originContext + mainDispatcher

    LifecycleScopeFactory.reset()

    val default = LifecycleScopeFactory.create()

    default.coroutineContext[DispatcherProvider]!!.shouldBeTypeOf<DefaultDispatcherProvider>()

    default.coroutineContext[Job]!!.shouldBeSupervisorJob()

    default.coroutineContext[ContinuationInterceptor] shouldBe Dispatchers.Main

    default.shouldBeInstanceOf<MainImmediateCoroutineScope>()
  }

}