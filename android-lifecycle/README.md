# Module android-lifecycle

## Contents
<!--- TOC -->

* [Types](#types)
* [Member functions](#member-functions)
* [Extension functions](#extension-functions)

<!--- END -->

``` kotlin
class SomeFragment(
private val coroutineScope: MainCoroutineScope
) : Fragment() {

  private val lifecycleScope = LifecycleCoroutineScope(lifecycle, coroutineScope)
  
  init {
    lifecycleScope.launchWhenResumed {
      //...
    }
  }
}
```

[CoroutineScope] functionality linked with an [Android Lifecycle].

## Types

| **Name**       | **Description**
| -------------  | --------------- |
| [LifecycleCoroutineScope] | [MainCoroutineScope] with a [Lifecycle], capable of automatically cancelling and restarting coroutines along with that lifecycle.
| [MinimumStatePolicy] | Defines the behavior of a [Lifecycle]-aware [Job] when it passes below its minimum [Lifecycle.State]

## Member functions

| **Name**          | **Description**
| -------------     | --------------- |
| [launchOnCreate]  | Creates a coroutine tied to a [Lifecycle] which will automatically enact a [MinimumStatePolicy] upon dropping below [Lifecycle.State.CREATED]
| [launchOnStart]   | Creates a coroutine tied to a [Lifecycle] which will automatically enact a [MinimumStatePolicy] upon dropping below [Lifecycle.State.STARTED]
| [launchOnResume]  | Creates a coroutine tied to a [Lifecycle] which will automatically enact a [MinimumStatePolicy] upon dropping below [Lifecycle.State.RESUMED]

## Extension functions

[LifecycleOwner] extension suspending functions:

| **Name**                                     | **Description**
| -------------------                          | ---------------
| [onNextCreate][LifecycleOwner.onNextCreate]  | Executes code one time upon reaching a state of [Lifecycle.State.CREATED]
| [onNextStart][LifecycleOwner.onNextStart]    | Executes code one time upon reaching a state of [Lifecycle.State.STARTED]
| [onNextResume][LifecycleOwner.onNextResume]  | Executes code one time upon reaching a state of [Lifecycle.State.RESUMED]

[Lifecycle] extension suspending functions:

| **Name**                                | **Description**
| -------------------                     | ---------------
| [onNextCreate][Lifecycle.onNextCreate]  | Executes code one time upon reaching a state of [Lifecycle.State.CREATED]
| [onNextStart][Lifecycle.onNextStart]    | Executes code one time upon reaching a state of [Lifecycle.State.STARTED]
| [onNextResume][Lifecycle.onNextResume]  | Executes code one time upon reaching a state of [Lifecycle.State.RESUMED]


<!--- MODULE core-->
<!--- INDEX  -->
[MainCoroutineScope]: https://rbusarow.github.io/Dispatch/core//dispatch.core/-main-coroutine-scope.html
<!--- MODULE core-test-->
<!--- INDEX  -->
<!--- MODULE core-test-junit4-->
<!--- INDEX  -->
<!--- MODULE core-test-junit5-->
<!--- INDEX  -->
<!--- MODULE extensions-->
<!--- INDEX  -->
<!--- MODULE android-espresso-->
<!--- INDEX  -->
<!--- MODULE android-lifecycle-->
<!--- INDEX  -->
[LifecycleCoroutineScope]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/index.html
[MinimumStatePolicy]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/-minimum-state-policy/index.html
[launchOnCreate]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/launch-on-create.html
[launchOnStart]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/launch-on-start.html
[launchOnResume]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/launch-on-resume.html
[LifecycleOwner.onNextCreate]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle-owner/on-next-create.html
[LifecycleOwner.onNextStart]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle-owner/on-next-start.html
[LifecycleOwner.onNextResume]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle-owner/on-next-resume.html
[Lifecycle.onNextCreate]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle/on-next-create.html
[Lifecycle.onNextStart]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle/on-next-start.html
[Lifecycle.onNextResume]: https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle/on-next-resume.html
<!--- MODULE android-lifecycle-extensions-->
<!--- INDEX  -->
<!--- MODULE android-viewmodel-->
<!--- INDEX  -->
<!--- END -->

<!-- kotlinx.coroutines -->
[launch]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html
[Job]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html
[CoroutineScope]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html
[async]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html
[Deferred]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/index.html
[runBlocking]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html
[CoroutineDispatcher]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html
[Dispatchers.Default]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-default.html
[Dispatchers.Unconfined]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-unconfined.html
[NonCancellable]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-non-cancellable.html
[CoroutineExceptionHandler]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-exception-handler/index.html
[delay]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/delay.html
[yield]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/yield.html
[withContext]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/with-context.html
[withTimeout]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/with-timeout.html
[withTimeoutOrNull]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/with-timeout-or-null.html
[awaitAll]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/await-all.html
[joinAll]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/join-all.html
[suspendCancellableCoroutine]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/suspend-cancellable-coroutine.html
[Job.join]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/join.html
[Job.onJoin]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/on-join.html
[Job.isCompleted]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/is-completed.html
[Deferred.await]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
[Deferred.onAwait]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/on-await.html
<!-- kotlinx.coroutines.sync -->
[kotlinx.coroutines.sync.Mutex]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.sync/-mutex/index.html
[kotlinx.coroutines.sync.Mutex.lock]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.sync/-mutex/lock.html
[kotlinx.coroutines.sync.Mutex.onLock]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.sync/-mutex/on-lock.html
[kotlinx.coroutines.sync.Mutex.tryLock]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.sync/-mutex/try-lock.html
<!-- kotlinx.coroutines.channels -->
[kotlinx.coroutines.channels.produce]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/produce.html
[kotlinx.coroutines.channels.ReceiveChannel]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-receive-channel/index.html
[kotlinx.coroutines.channels.ProducerScope]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-producer-scope/index.html
[kotlinx.coroutines.channels.Channel]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-channel/index.html
[kotlinx.coroutines.channels.SendChannel.send]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-send-channel/send.html
[kotlinx.coroutines.channels.ReceiveChannel.receive]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-receive-channel/receive.html
[kotlinx.coroutines.channels.SendChannel]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-send-channel/index.html
[kotlinx.coroutines.channels.SendChannel.onSend]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-send-channel/on-send.html
[kotlinx.coroutines.channels.SendChannel.offer]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-send-channel/offer.html
[kotlinx.coroutines.channels.ReceiveChannel.onReceive]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-receive-channel/on-receive.html
[kotlinx.coroutines.channels.ReceiveChannel.poll]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-receive-channel/poll.html
[kotlinx.coroutines.channels.receiveOrNull]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/receive-or-null.html
[kotlinx.coroutines.channels.onReceiveOrNull]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/on-receive-or-null.html
<!-- kotlinx.coroutines.selects -->
[kotlinx.coroutines.selects.select]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.selects/select.html
[kotlinx.coroutines.selects.SelectBuilder.onTimeout]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.selects/-select-builder/on-timeout.html


<!--AndroidX Lifecycle-->

[b/146370660]: https://issuetracker.google.com/issues/146370660

[Lifecycle.State]: https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html
[Lifecycle.State.CREATED]: https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#CREATED
[Lifecycle.State.STARTED]: https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#STARTED
[Lifecycle.State.RESUMED]: https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#RESUMED

[Android Lifecycle]: https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html
[Lifecycle]: https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html
[LifecycleOwner]: https://developer.android.com/reference/androidx/lifecycle/LifecycleOwner.html

[androidx-lifecycle-runtime-ktx]: https://developer.android.com/jetpack/androidx/releases/lifecycle
[androidx.lifecycle.lifecycleScope]: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-master-dev:lifecycle/lifecycle-runtime-ktx/src/main/java/androidx/lifecycle/Lifecycle.kt;l=44
[PausingDispatcher]: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-master-dev:lifecycle/lifecycle-runtime-ktx/src/main/java/androidx/lifecycle/PausingDispatcher.kt
