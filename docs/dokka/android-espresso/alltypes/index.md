

Tools to provide [Espresso](https://developer.android.com/training/testing/espresso/idling-resource) functionality for coroutines.

### All Types

| Name | Summary |
|---|---|
|

##### [dispatch.android.espresso.DefaultIdlingCoroutineScope](../dispatch.android.espresso/-default-idling-coroutine-scope.md)

Marker interface for an [IdlingCoroutineScope](../dispatch.android.espresso/-idling-coroutine-scope/index.md) which indicates that its [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) is [DispatcherProvider.default](https://rbusarow.github.io/Dispatch/core/dispatch.core/-dispatcher-provider/default.md)


|

##### [dispatch.android.espresso.IdlingCoroutineScope](../dispatch.android.espresso/-idling-coroutine-scope/index.md)

Special [CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html) with a [DispatcherProvider](https://rbusarow.github.io/Dispatch/core/dispatch.core/-dispatcher-provider/index.md) which is an [IdlingDispatcherProvider](../dispatch.android.espresso/-idling-dispatcher-provider/index.md).


|

##### [dispatch.android.espresso.IdlingDispatcher](../dispatch.android.espresso/-idling-dispatcher/index.md)

[IdlingResource](https://developer.android.com/reference/androidx/test/androidx/test/espresso/IdlingResource.html) helper for coroutines.  This class simply wraps a delegate [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)
and keeps a running count of all coroutines it creates, decrementing the count when they complete.


|

##### [dispatch.android.espresso.IdlingDispatcherProvider](../dispatch.android.espresso/-idling-dispatcher-provider/index.md)

[IdlingResource](https://developer.android.com/reference/androidx/test/androidx/test/espresso/IdlingResource.html) helper for coroutines.  This [DispatcherProvider](https://rbusarow.github.io/Dispatch/core/dispatch.core/-dispatcher-provider/index.md) implementation
utilizes an [IdlingDispatcher](../dispatch.android.espresso/-idling-dispatcher/index.md) for each [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html).


|

##### [dispatch.android.espresso.IdlingDispatcherProviderRule](../dispatch.android.espresso/-idling-dispatcher-provider-rule/index.md)

A JUnit 4 [TestRule](https://junit.org/junit4/javadoc/latest/org/junit/rules/TestRule.html) which creates a new [IdlingDispatcherProvider](../dispatch.android.espresso/-idling-dispatcher-provider/index.md) for each test,
registering all [IdlingDispatcher](../dispatch.android.espresso/-idling-dispatcher/index.md)s with [IdlingRegistry](https://developer.android.com/reference/androidx/test/androidx/test/espresso/IdlingRegistry.html) before `@Before` and unregistering them after `@After`.


|

##### [dispatch.android.espresso.IOIdlingCoroutineScope](../dispatch.android.espresso/-i-o-idling-coroutine-scope.md)

Marker interface for an [IdlingCoroutineScope](../dispatch.android.espresso/-idling-coroutine-scope/index.md) which indicates that its [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) is [DispatcherProvider.io](https://rbusarow.github.io/Dispatch/core/dispatch.core/-dispatcher-provider/io.md)


|

##### [dispatch.android.espresso.MainIdlingCoroutineScope](../dispatch.android.espresso/-main-idling-coroutine-scope.md)

Marker interface for an [IdlingCoroutineScope](../dispatch.android.espresso/-idling-coroutine-scope/index.md) which indicates that its [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) is [DispatcherProvider.main](https://rbusarow.github.io/Dispatch/core/dispatch.core/-dispatcher-provider/main.md)


|

##### [dispatch.android.espresso.MainImmediateIdlingCoroutineScope](../dispatch.android.espresso/-main-immediate-idling-coroutine-scope.md)

Marker interface for an [IdlingCoroutineScope](../dispatch.android.espresso/-idling-coroutine-scope/index.md) which indicates that its [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) is [DispatcherProvider.mainImmediate](https://rbusarow.github.io/Dispatch/core/dispatch.core/-dispatcher-provider/main-immediate.md)


|

##### [dispatch.android.espresso.UnconfinedIdlingCoroutineScope](../dispatch.android.espresso/-unconfined-idling-coroutine-scope.md)

Marker interface for an [IdlingCoroutineScope](../dispatch.android.espresso/-idling-coroutine-scope/index.md) which indicates that its [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html) is [DispatcherProvider.unconfined](https://rbusarow.github.io/Dispatch/core/dispatch.core/-dispatcher-provider/unconfined.md)

