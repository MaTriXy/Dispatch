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

import androidx.annotation.*
import androidx.lifecycle.*
import kotlinx.coroutines.*

/**
 * Attaches to a lifecycle and controls the [DispatchQueue]'s execution.
 */
@MainThread
internal class LifecycleController(
  private val lifecycle: Lifecycle,
  private val minState: Lifecycle.State,
  private val dispatchQueue: DispatchQueue,
  parentJob: Job
) {
  private val observer = LifecycleEventObserver { source, _ ->
    when {
      source.lifecycle.currentState == Lifecycle.State.DESTROYED -> handleDestroy(parentJob)
      source.lifecycle.currentState < minState                   -> dispatchQueue.pause()
      else                                                       -> dispatchQueue.resume()
    }
  }

  init {
    // If Lifecycle is already destroyed (e.g. developer leaked the lifecycle), we won't get
    // an event callback so we need to check for it before registering
    // see: b/128749497 for details.
    if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
      handleDestroy(parentJob)
    } else {
      lifecycle.addObserver(observer)
    }
  }

  @Suppress("NOTHING_TO_INLINE") // avoid unnecessary method
  private inline fun handleDestroy(parentJob: Job) {
    parentJob.cancel()
    finish()
  }

  /**
   * Removes the observer and also marks the [DispatchQueue] as finished so that any remaining
   * runnables can be executed.
   */
  @MainThread
  fun finish() {
    lifecycle.removeObserver(observer)
    dispatchQueue.finish()
  }
}
