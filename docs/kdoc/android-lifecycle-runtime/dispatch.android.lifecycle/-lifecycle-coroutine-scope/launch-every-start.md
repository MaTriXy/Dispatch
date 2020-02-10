[android-lifecycle-runtime](../../index.md) / [dispatch.android.lifecycle](../index.md) / [LifecycleCoroutineScope](index.md) / [launchEveryStart](./launch-every-start.md)

# launchEveryStart

`fun launchEveryStart(block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) [(source)](https://github.com/RBusarow/Dispatch/tree/master/android-lifecycle-runtime/src/main/java/dispatch/android/lifecycle/LifecycleCoroutineScope.kt#L82)

Lifecycle-aware function for launching a coroutine any time the [Lifecycle.State](https://developer.android.com/reference/androidx/androidx/lifecycle/Lifecycle/State.html)
is at least [Lifecycle.State.STARTED](https://developer.android.com/reference/androidx/androidx/lifecycle/Lifecycle/State.html#STARTED).

[block](launch-every-start.md#dispatch.android.lifecycle.LifecycleCoroutineScope$launchEveryStart(kotlin.coroutines.SuspendFunction1((kotlinx.coroutines.CoroutineScope, kotlin.Unit)))/block) is executed using the receiver [CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)'s [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) as a parent,
but always executes using [Dispatchers.Main](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-main.html) as its [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html).

Execution of [block](launch-every-start.md#dispatch.android.lifecycle.LifecycleCoroutineScope$launchEveryStart(kotlin.coroutines.SuspendFunction1((kotlinx.coroutines.CoroutineScope, kotlin.Unit)))/block) is cancelled when the receiver [CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html) is cancelled,
or when [lifecycle](#)'s [Lifecycle.State](https://developer.android.com/reference/androidx/androidx/lifecycle/Lifecycle/State.html) drops below [Lifecycle.State.STARTED](https://developer.android.com/reference/androidx/androidx/lifecycle/Lifecycle/State.html#STARTED).

``` kotlin
runBlocking {

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
```
