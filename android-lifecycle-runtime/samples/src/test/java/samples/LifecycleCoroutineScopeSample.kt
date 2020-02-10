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


package samples

import dispatch.android.lifecycle.*
import dispatch.core.*
import dispatch.core.test.*
import io.kotlintest.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
class LifecycleCoroutineScopeSample : CoroutineTest {

  override lateinit var testScope: TestProvidedCoroutineScope

  @BeforeEach
  fun beforeEach() {

    LifecycleScopeFactory.set { testScope }
  }

  @Sample
  fun lifecycleCoroutineScopeSample() = runBlocking {

    // This could be any LifecycleOwner -- Fragments, Activities, Services...
    class SomeFragment : Fragment() {

      init {

        // auto-created MainImmediateCoroutineScope which is lifecycle-aware
        lifecycleScope //...

        // active only when "resumed".  starts a fresh coroutine each time
        // this is a rough proxy for LiveData behavior
        lifecycleScope.launchEveryResume { }

        // active only when "started".  starts a fresh coroutine each time
        lifecycleScope.launchEveryStart { }

        // launch when created, automatically stop on destroy
        lifecycleScope.launchEveryCreate { }

        // it works as a normal CoroutineScope as well (because it is)
        lifecycleScope.launchMain { }

      }
    }
  }

  @Sample
  fun launchEveryCreateSample() = runBlocking {

    val channel = Channel<String>()
    val history = mutableListOf<String>()

    class SomeViewModel {
      val someFlow = flow {
        repeat(100) {
          emit(it)
        }
      }
    }

    class SomeFragment : Fragment() {

      val viewModel = SomeViewModel()

      init {
        lifecycleScope.launchEveryCreate {
          viewModel.someFlow.collect {
            channel.send("$it")
          }
        }
      }
    }

    val fragment = SomeFragment()

    history.add("creating")
    fragment.create()

    repeat(3) {
      history.add(channel.receive())
    }

    // destroying the lifecycle cancels the lifecycleScope
    history.add("destroying")
    fragment.destroy()

    history shouldBe listOf(
      "creating",
      "0",
      "1",
      "2",
      "destroying"
    )
  }

  @Sample
  fun launchEveryStartSample() = runBlocking {

    val channel = Channel<String>()
    val history = mutableListOf<String>()

    class SomeViewModel {
      val someFlow = flow {
        repeat(100) {
          emit(it)
        }
      }
    }

    class SomeFragment : Fragment() {

      val viewModel = SomeViewModel()

      init {
        lifecycleScope.launchEveryStart {
          viewModel.someFlow.collect {
            channel.send("$it")
          }
        }
      }
    }

    val fragment = SomeFragment()

    history.add("starting")
    fragment.start()

    repeat(3) {
      history.add(channel.receive())
    }

    // stopping the lifecycle cancels the existing Job
    history.add("stopping")
    fragment.stop()

    // starting the lifecycle creates a new Job
    history.add("starting")
    fragment.start()

    repeat(3) {
      history.add(channel.receive())
    }

    history.add("stopping")
    fragment.stop()

    history shouldBe listOf(
      "starting",
      "0",
      "1",
      "2",
      "stopping",
      "starting",
      "0",
      "1",
      "2",
      "stopping"
    )
  }

  @Sample
  fun launchEveryResumeSample() = runBlocking {

    val channel = Channel<String>()
    val history = mutableListOf<String>()

    class SomeViewModel {
      val someFlow = flow {
        repeat(100) {
          emit(it)
        }
      }
    }

    class SomeFragment : Fragment() {

      val viewModel = SomeViewModel()

      init {
        lifecycleScope.launchEveryResume {
          viewModel.someFlow.collect {
            channel.send("$it")
          }
        }
      }
    }

    val fragment = SomeFragment()

    history.add("resuming")
    fragment.resume()

    repeat(3) {
      history.add(channel.receive())
    }

    // pausing the lifecycle cancels the existing Job
    history.add("pausing")
    fragment.pause()

    // resuming the lifecycle creates a new Job
    history.add("resuming")
    fragment.resume()

    repeat(3) {
      history.add(channel.receive())
    }

    history.add("pausing")
    fragment.pause()

    history shouldBe listOf(
      "resuming",
      "0",
      "1",
      "2",
      "pausing",
      "resuming",
      "0",
      "1",
      "2",
      "pausing"
    )
  }
}
