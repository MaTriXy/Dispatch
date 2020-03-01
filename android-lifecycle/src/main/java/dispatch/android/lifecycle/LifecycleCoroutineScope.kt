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
import dispatch.android.lifecycle.internal.*
import dispatch.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * [MainImmediateCoroutineScope] instance which is tied to a [Lifecycle].
 *
 * The [CoroutineScope] provides lifecycle-aware [launch] functions
 * which will automatically start upon reaching their associated [Lifecycle.Event],
 * then automatically cancel upon the [lifecycle] dropping below that state.  Reaching
 * that state again will start a new [Job].
 *
 * @sample samples.LifecycleCoroutineScopeSample.lifecycleCoroutineScopeSample
 * @param lifecycle the lifecycle to which this [MainImmediateCoroutineScope] is linked.
 */
class LifecycleCoroutineScope(
  val lifecycle: Lifecycle,
  private val coroutineScope: MainImmediateCoroutineScope
) : MainImmediateCoroutineScope by coroutineScope {

  /**
   * Lifecycle-aware function for launching a coroutine any time the [Lifecycle.State]
   * is at least [Lifecycle.State.CREATED].
   *
   * [block] is executed using the receiver [LifecycleCoroutineScope]'s [Job] as a parent,
   * but always executes using [Dispatchers.Main] as its [CoroutineDispatcher].
   *
   * Execution of [block] is cancelled when the receiver [LifecycleCoroutineScope] is cancelled,
   * or when [lifecycle]'s [Lifecycle.State] drops below [Lifecycle.State.CREATED].
   *
   * @param minimumStatePolicy *optional* - the way this [Job] will behave when passing below the minimum
   * state or re-entering.  Uses [MinimumStatePolicy.RESTART_EVERY] by default.  Note that for a normal Lifecycle,
   * there is no returning from below a [CREATED][Lifecycle.State.CREATED] state,
   * so the [minimumStatePolicy][MinimumStatePolicy] is largely irrelevant.
   *
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnCreateOnceSample
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnCreateRestartingSample
   */
  fun launchOnCreate(
    minimumStatePolicy: MinimumStatePolicy = MinimumStatePolicy.RESTART_EVERY,
    block: suspend LifecycleCoroutineScope.() -> Unit
  ): Job = launchOn(Lifecycle.State.CREATED, minimumStatePolicy, block)

  /**
   * Lifecycle-aware function for launching a coroutine any time the [Lifecycle.State]
   * is at least [Lifecycle.State.STARTED].
   *
   * [block] is executed using the receiver [LifecycleCoroutineScope]'s [Job] as a parent,
   * but always executes using [Dispatchers.Main] as its [CoroutineDispatcher].
   *
   * Execution of [block] is cancelled when the receiver [LifecycleCoroutineScope] is cancelled,
   * or when [lifecycle]'s [Lifecycle.State] drops below [Lifecycle.State.STARTED].
   *
   * @param minimumStatePolicy *optional* - the way this [Job] will behave when passing below the minimum
   * state or re-entering.  Uses [MinimumStatePolicy.RESTART_EVERY] by default.
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnStartOnceSample
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnStartRestartingSample
   */
  fun launchOnStart(
    minimumStatePolicy: MinimumStatePolicy = MinimumStatePolicy.RESTART_EVERY,
    block: suspend LifecycleCoroutineScope.() -> Unit
  ): Job = launchOn(Lifecycle.State.STARTED, minimumStatePolicy, block)

  /**
   * Lifecycle-aware function for launching a coroutine any time the [Lifecycle.State]
   * is at least [Lifecycle.State.RESUMED].
   *
   * [block] is executed using the receiver [LifecycleCoroutineScope]'s [Job] as a parent,
   * but always executes using [Dispatchers.Main] as its [CoroutineDispatcher].
   *
   * Execution of [block] is cancelled when the receiver [LifecycleCoroutineScope] is cancelled,
   * or when [lifecycle]'s [Lifecycle.State] drops below [Lifecycle.State.RESUMED].
   *
   * @param minimumStatePolicy *optional* - the way this [Job] will behave when passing below the minimum
   * state or re-entering.  Uses [MinimumStatePolicy.RESTART_EVERY] by default.
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnResumeOnceSample
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnResumeRestartingSample
   */
  fun launchOnResume(
    minimumStatePolicy: MinimumStatePolicy = MinimumStatePolicy.RESTART_EVERY,
    block: suspend LifecycleCoroutineScope.() -> Unit
  ): Job = launchOn(Lifecycle.State.RESUMED, minimumStatePolicy, block)

  /**
   * Lifecycle-aware function for collecting a [Flow] while time the [Lifecycle.State]
   * is at least [Lifecycle.State.CREATED].
   *
   * [action] is executed using this [LifecycleCoroutineScope] to create a coroutine,
   * using [Dispatchers.Main] as its [LifecycleCoroutineScope].
   *
   * Execution of [action] and collection are cancelled when this [LifecycleCoroutineScope] is cancelled,
   * or when [lifecycle]'s [Lifecycle.State] drops below [Lifecycle.State.CREATED].
   *
   * @param minimumStatePolicy *optional* - the way this [Job] will behave when passing below the minimum
   * state or re-entering.  Uses [MinimumStatePolicy.RESTART_EVERY] by default.
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnResumeOnceSample
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnResumeRestartingSample
   */
  inline fun <T> Flow<T>.collectedWhileCreated(
    minimumStatePolicy: MinimumStatePolicy = MinimumStatePolicy.RESTART_EVERY,
    crossinline action: suspend (value: T) -> Unit = {}
  ) {
    launchOnCreate(minimumStatePolicy) {
      collect { value ->
        action(value)
      }
    }
  }

  /**
   * Lifecycle-aware function for collecting a [Flow] while time the [Lifecycle.State]
   * is at least [Lifecycle.State.CREATED].
   *
   * [action] is executed using this [LifecycleCoroutineScope] to create a coroutine,
   * using [Dispatchers.Main] as its [LifecycleCoroutineScope].
   *
   * Execution of [action] and collection are cancelled when this [LifecycleCoroutineScope] is cancelled,
   * or when [lifecycle]'s [Lifecycle.State] drops below [Lifecycle.State.CREATED].
   *
   * @param minimumStatePolicy *optional* - the way this [Job] will behave when passing below the minimum
   * state or re-entering.  Uses [MinimumStatePolicy.RESTART_EVERY] by default.
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnResumeOnceSample
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnResumeRestartingSample
   */
  inline fun <T> Flow<T>.collectedWhileStarted(
    minimumStatePolicy: MinimumStatePolicy = MinimumStatePolicy.RESTART_EVERY,
    crossinline action: suspend (value: T) -> Unit = {}
  ) {
    launchOnStart(minimumStatePolicy) {
      collect { value ->
        action(value)
      }
    }
  }

  /**
   * Lifecycle-aware function for collecting a [Flow] while time the [Lifecycle.State]
   * is at least [Lifecycle.State.CREATED].
   *
   * [action] is executed using this [LifecycleCoroutineScope] to create a coroutine,
   * using [Dispatchers.Main] as its [LifecycleCoroutineScope].
   *
   * Execution of [action] and collection are cancelled when this [LifecycleCoroutineScope] is cancelled,
   * or when [lifecycle]'s [Lifecycle.State] drops below [Lifecycle.State.CREATED].
   *
   * @param minimumStatePolicy *optional* - the way this [Job] will behave when passing below the minimum
   * state or re-entering.  Uses [MinimumStatePolicy.RESTART_EVERY] by default.
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnResumeOnceSample
   * @sample samples.LifecycleCoroutineScopeLaunchOnSample.launchOnResumeRestartingSample
   */
  inline fun <T> Flow<T>.collectedWhileResumed(
    minimumStatePolicy: MinimumStatePolicy = MinimumStatePolicy.RESTART_EVERY,
    crossinline action: suspend (value: T) -> Unit = {}
  ) {
    launchOnResume(minimumStatePolicy) {
      collect { value ->
        action(value)
      }
    }
  }

  /**
   * Describes the way a [LifecycleCoroutineScope] [Job] will behave if the [lifecycle] passes below the minimum state
   * before said [Job] has completed.
   */
  enum class MinimumStatePolicy {
    /**
     * When using `CANCEL`, a coroutine will be created the first time the [lifecycle] meets the minimum state,
     * and cancelled upon dropping below it.
     * Subsequently meeting the minimum state again will not resume the coroutine or create a new one.
     *
     * @see launchOnCreate
     * @see launchOnStart
     * @see launchOnResume
     */
    CANCEL,
    /**
     * When using `RESTART_EVERY`, a coroutine will be created every time the [lifecycle] meets the minimum state,
     * and will be cancelled upon dropping below it.
     * Subsequently meeting the minimum state again will create a new coroutine.
     *
     * Note that **state is not retained** when dropping below a minimum state.
     *
     * @see launchOnCreate
     * @see launchOnStart
     * @see launchOnResume
     */
    RESTART_EVERY
  }
}
