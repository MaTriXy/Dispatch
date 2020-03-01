[core-test-junit5](../../index.md) / [dispatch.core.test](../index.md) / [CoroutineTest](index.md) / [runBlockingTest](./run-blocking-test.md)

# runBlockingTest

`open fun runBlockingTest(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html)` = EmptyCoroutineContext, testBody: suspend `[`TestCoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/-test-coroutine-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/RBusarow/Dispatch/tree/master/core-test-junit5/src/main/java/dispatch/core/test/CoroutineTest.kt#L66)

Convenience function for invoking [runBlockingTestProvided](https://rbusarow.github.io/Dispatch/core-test/dispatch.core.test/run-blocking-test-provided.md) without an additional import.
