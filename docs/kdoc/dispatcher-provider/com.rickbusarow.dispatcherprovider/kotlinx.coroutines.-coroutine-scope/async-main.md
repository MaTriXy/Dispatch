[dispatcher-provider](../../index.md) / [com.rickbusarow.dispatcherprovider](../index.md) / [kotlinx.coroutines.CoroutineScope](index.md) / [asyncMain](./async-main.md)

# asyncMain

`fun <T> `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)`.asyncMain(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html)` = EmptyCoroutineContext, start: `[`CoroutineStart`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-start/index.html)` = CoroutineStart.DEFAULT, block: suspend `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)`.() -> T): `[`Deferred`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/index.html)`<T>` [(source)](https://github.com/RBusarow/Dispatch/tree/master/dispatcher-provider/src/main/java/com/rickbusarow/dispatcherprovider/Builders.kt#L249)

Creates a coroutine and returns its future result as an implementation of [Deferred](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/index.html).

Extracts the [DispatcherProvider](../-dispatcher-provider/index.md) from the [CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html) receiver, then uses its **default** [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)
property (`coroutineContext.dispatcherProvider.main`) to call `async(...)`.

The `main` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.

**See Also**

[async](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html)

