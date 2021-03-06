[dispatcher-provider](../index.md) / [com.rickbusarow.dispatcherprovider](index.md) / [IOCoroutineScope](./-i-o-coroutine-scope.md)

# IOCoroutineScope

`interface IOCoroutineScope : `[`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html) [(source)](https://github.com/RBusarow/Dispatch/tree/master/dispatcher-provider/src/main/java/com/rickbusarow/dispatcherprovider/CoroutineScopes.kt#L33)

Marker interface which designates a [CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html) with a [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) of `io`.

`fun IOCoroutineScope(job: `[`Job`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)` = SupervisorJob(), dispatcherProvider: `[`DispatcherProvider`](-dispatcher-provider/index.md)` = DefaultDispatcherProvider()): `[`IOCoroutineScope`](./-i-o-coroutine-scope.md) [(source)](https://github.com/RBusarow/Dispatch/tree/master/dispatcher-provider/src/main/java/com/rickbusarow/dispatcherprovider/CoroutineScopes.kt#L91)

Factory function for an [IOCoroutineScope](./-i-o-coroutine-scope.md) with a [DispatcherProvider](-dispatcher-provider/index.md).
Dispatch defaults to the `io` property of the `DispatcherProvider`.

### Parameters

`job` - [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) to be used for the resulting `CoroutineScope`.  Uses a [SupervisorJob](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-supervisor-job.html) if one is not provided.

`dispatcherProvider` - [DispatcherProvider](-dispatcher-provider/index.md) to be used for the resulting `CoroutineScope`.  Uses a [DefaultDispatcherProvider](-default-dispatcher-provider/index.md) if one is not provided.

**See Also**

[CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)

`fun IOCoroutineScope(coroutineContext: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html)`): `[`IOCoroutineScope`](./-i-o-coroutine-scope.md) [(source)](https://github.com/RBusarow/Dispatch/tree/master/dispatcher-provider/src/main/java/com/rickbusarow/dispatcherprovider/CoroutineScopes.kt#L108)

Factory function for a [IOCoroutineScope](./-i-o-coroutine-scope.md) with a [DispatcherProvider](-dispatcher-provider/index.md).
Dispatch defaults to the `io` property of the `DispatcherProvider`.

### Parameters

`coroutineContext` - [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) to be used for the resulting `CoroutineScope`.
Any existing [ContinuationInterceptor](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-continuation-interceptor/index.html) will be overwritten.
If the `CoroutineContext` does not already contain a `DispatcherProvider`, a [DefaultDispatcherProvider](-default-dispatcher-provider/index.md) will be added.

**See Also**

[CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html)

