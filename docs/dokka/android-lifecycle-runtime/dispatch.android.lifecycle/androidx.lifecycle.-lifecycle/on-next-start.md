[android-lifecycle-runtime](../../index.md) / [dispatch.android.lifecycle](../index.md) / [androidx.lifecycle.Lifecycle](index.md) / [onNextStart](./on-next-start.md)

# onNextStart

`suspend fun <T> `[`Lifecycle`](https://developer.android.com/reference/androidx/androidx/lifecycle/Lifecycle.html)`.onNextStart(block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)`.() -> T): T?` [(source)](https://github.com/RBusarow/Dispatch/tree/master/android-lifecycle-runtime/src/main/java/dispatch/android/lifecycle/LifecycleSuspendExt.kt#L66)

Executes [block](on-next-start.md#dispatch.android.lifecycle$onNextStart(androidx.lifecycle.Lifecycle, kotlin.coroutines.SuspendFunction1((kotlinx.coroutines.CoroutineScope, dispatch.android.lifecycle.onNextStart.T)))/block) one time, the next time the [Lifecycle](https://developer.android.com/reference/androidx/androidx/lifecycle/Lifecycle.html)'s state is at least [Lifecycle.State.STARTED](https://developer.android.com/reference/androidx/androidx/lifecycle/Lifecycle/State.html#STARTED).

If the lifecycle is already in this state, [block](on-next-start.md#dispatch.android.lifecycle$onNextStart(androidx.lifecycle.Lifecycle, kotlin.coroutines.SuspendFunction1((kotlinx.coroutines.CoroutineScope, dispatch.android.lifecycle.onNextStart.T)))/block) will be executed immediately.

``` kotlin
runBlocking {

    class SomeFragment : Fragment() {

      var invocations = 0

      init {
        lifecycleScope.launchMainImmediate {
          lifecycle.onNextStart { invocations++ }
        }
      }
    }

    // current lifecycle state is INITIALIZED
    val fragment = SomeFragment()

    // nothing is invoked yet
    fragment.invocations shouldBe 0

    fragment.start()
    fragment.invocations shouldBe 1

    // crossing the threshold doesn't invoke the lambda again
    fragment.stop()
    fragment.start()
    fragment.invocations shouldBe 1
  }
```

**See Also**

[LifecycleCoroutineScope.launchEveryStart](../-lifecycle-coroutine-scope/launch-every-start.md)
