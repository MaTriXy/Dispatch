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

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlin.coroutines.*

suspend fun <T> Lifecycle.whenStateAtLeast(
  minState: Lifecycle.State,
  block: suspend CoroutineScope.() -> T
) = withContext(Dispatchers.Main.immediate) {
  val job = coroutineContext[Job] ?: error("when[State] methods should have a parent job")
  val dispatcher = PausingDispatcher()
  val controller =
    LifecycleController(this@whenStateAtLeast, minState, dispatcher.dispatchQueue, job)
  try {
    withContext(dispatcher, block)
  } finally {
    controller.finish()
  }
}

/**
 * A [CoroutineDispatcher] implementation that maintains a dispatch queue to be able to pause
 * execution of coroutines.
 *
 * @see [DispatchQueue] and [Lifecycle.whenStateAtLeast] for details.
 */
internal class PausingDispatcher : CoroutineDispatcher() {
  /**
   * helper class to maintain state and enqueued continuations.
   */
  @JvmField
  internal val dispatchQueue = DispatchQueue()

  @ExperimentalCoroutinesApi
  override fun dispatch(context: CoroutineContext, block: Runnable) {
    dispatchQueue.runOrEnqueue(block)
  }
}
