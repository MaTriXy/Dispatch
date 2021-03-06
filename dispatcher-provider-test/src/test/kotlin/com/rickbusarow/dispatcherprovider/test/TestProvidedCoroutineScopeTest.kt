/*
 * Copyright (C) 2019-2020 Rick Busarow
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

package com.rickbusarow.dispatcherprovider.test

import com.rickbusarow.dispatcherprovider.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.amshove.kluent.*
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
internal class TestProvidedCoroutineScopeTest {

  val dispatcher = TestCoroutineDispatcher()
  val provider = TestDispatcherProvider(dispatcher)

  @Nested
  inner class `marker interfaces` {

    val scope = TestProvidedCoroutineScope()

    @Test
    fun `TestProvidedCoroutineScope should implement DefaultCoroutineScope`() {

      scope.shouldBeTypeOf<DefaultCoroutineScope>()
    }

    @Test
    fun `TestProvidedCoroutineScope should implement IOCoroutineScope`() {

      scope.shouldBeTypeOf<IOCoroutineScope>()
    }

    @Test
    fun `TestProvidedCoroutineScope should implement MainCoroutineScope`() {

      scope.shouldBeTypeOf<MainCoroutineScope>()
    }

    @Test
    fun `TestProvidedCoroutineScope should implement MainImmediateCoroutineScope`() {

      scope.shouldBeTypeOf<MainImmediateCoroutineScope>()
    }

    @Test
    fun `TestProvidedCoroutineScope should implement UnconfinedCoroutineScope`() {

      scope.shouldBeTypeOf<UnconfinedCoroutineScope>()
    }
  }

  @Nested
  inner class `test provided CoroutineScope impl` {

    @Test
    fun `scope implementation should contain DispatcherProvider property`() {

      val scope = TestProvidedCoroutineScopeImpl(provider)

      scope.coroutineContext.dispatcherProvider shouldBe provider
    }

    @Test
    fun `DispatcherProvider property should override context arg`() {

      val scope = TestProvidedCoroutineScopeImpl(
        dispatcherProvider = provider,
        context = TestDispatcherProvider()
      )

      scope.coroutineContext.dispatcherProvider shouldBe provider
    }

  }

  @Nested
  inner class `factory function` {

    @Test
    fun `scope should contain DispatcherProvider property`() {

      val scope = TestProvidedCoroutineScope(dispatcherProvider = provider)

      scope.coroutineContext.dispatcherProvider shouldBe provider
    }

    @Test
    fun `dispatcher arg should be used to create default DispatcherProvider`() {

      val scope = TestProvidedCoroutineScope(dispatcher = dispatcher)

      scope.dispatcherProvider.default shouldBe dispatcher
      scope.dispatcherProvider.io shouldBe dispatcher
      scope.dispatcherProvider.main shouldBe dispatcher
      scope.dispatcherProvider.mainImmediate shouldBe dispatcher
      scope.dispatcherProvider.unconfined shouldBe dispatcher
    }

    @Test
    fun `DispatcherProvider arg should override context arg`() {

      val scope = TestProvidedCoroutineScope(
        dispatcherProvider = provider,
        context = TestDispatcherProvider()
      )

      scope.coroutineContext.dispatcherProvider shouldBe provider
    }
  }
}
