[dispatcher-provider-test](../index.md) / [com.rickbusarow.dispatcherprovider.test](index.md) / [runBlockingTestProvided](./run-blocking-test-provided.md)

# runBlockingTestProvided

`@ExperimentalCoroutinesApi fun runBlockingTestProvided(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html)` = EmptyCoroutineContext, testBody: suspend `[`TestCoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/-test-coroutine-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/RBusarow/Dispatch/tree/master/dispatcher-provider-test/src/main/java/com/rickbusarow/dispatcherprovider/test/Builders.kt#L69)

Delegates to [runBlockingTest](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/run-blocking-test.html), but injects a [DispatcherProvider](#) into the created [TestCoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/-test-coroutine-scope/index.html).

If the `context`'s [ContinuationInterceptor](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-continuation-interceptor/index.html) is not a [TestCoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/-test-coroutine-dispatcher/index.html),
then a new [TestCoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/-test-coroutine-dispatcher/index.html) will be created.

If the `context` does not contain a `DispatcherProvider`,
a [TestDispatcherProvider](-test-dispatcher-provider/index.md) will be created using the `TestCoroutineDispatcher`.

### Parameters

`context` - The base `CoroutineContext` which will be modified
to use a `TestCoroutineDispatcher` and `TestDispatcherProvider`.
[EmptyCoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-empty-coroutine-context/index.html) is used if one is not provided.

**See Also**

[runBlockingTest](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/run-blocking-test.html)

