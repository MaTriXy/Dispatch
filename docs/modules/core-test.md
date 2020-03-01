# Module core-test

Test helpers for the [dispatch-core] module.  Most of the tools you need to automatically handle [DispatcherProvider] in your tests.  (see [dispatch-core-test-junit4] or [dispatch-core-test-junit5] for the rest.)

## Contents
<!--- TOC -->

* [TestDispatcherProvider](#testdispatcherprovider)
  * [Constructor with default arguments](#constructor-with-default-arguments)
  * [Single-arg factory](#single-arg-factory)
  * [Basic TestDispatcherProvider](#basic-testdispatcherprovider)
* [TestProvidedCoroutineScope](#testprovidedcoroutinescope)
* [Builders](#builders)
  * [Minimum Gradle Config](#minimum-gradle-config)

<!--- END -->

## TestDispatcherProvider

Testing version of the [DispatcherProvider] with three main styles of creation:

### Constructor with default arguments

[(link)][TestDispatcherProvider]

Each property becomes its own [TestCoroutineDispatcher] by default, but may be replaced by any [CoroutineDispatcher].

``` kotlin
val customTestDispatcherProvider = TestDispatcherProvider(
  default = newSingleThreadContext("default"),
  io = newSingleThreadContext("io"),
  main = newSingleThreadContext("main"),
  mainImmediate = newSingleThreadContext("main immediate"),
  unconfined = newSingleThreadContext("unconfined")
)

val defaultTetsDispatcherProvider = TestDispatcherProvider()
```
### Single-arg factory

[(link)][TestDispatcherProvider]

Another option is to pass a single [CoroutineDispatcher], which is then used to populate all fields.

``` kotlin
val dispatcher = newSingleThreadContext("custom")

val dispatcherProvider = TestDispatcherProvider(dispatcher)

dispatcherProvider.default shouldBe myDispatcher
dispatcherProvider.io shouldBe myDispatcher
dispatcherProvider.main shouldBe myDispatcher
dispatcherProvider.mainImmediate shouldBe myDispatcher
dispatcherProvider.unconfined shouldBe myDispatcher
```

### Basic TestDispatcherProvider

[(link)][TestDispatcherProvider]

Sometimes we want to have the normal dispatch behaviors of a production environment,
just without the awkward mechanics of [Dispatchers.setMain].

This is essentially [DefaultDispatcherProvider] except with a [single-threaded executor][newSingleThreadContext] handling the "main" thread.

``` kotlin
fun TestBasicDispatcherProvider(): TestDispatcherProvider {

  val mainThread = newSingleThreadContext("main thread proxy")

  return TestDispatcherProvider(
    default = Dispatchers.Default,
    io = Dispatchers.IO,
    main = mainThread,
    mainImmediate = mainThread,
    unconfined = Dispatchers.Unconfined
  )
}
```

## TestProvidedCoroutineScope

[(link)][TestProvidedCoroutineScope]

A polymorphic [CoroutineScope] which implements all the type-safe versions from [dispatch-core], as well as [TestCoroutineScope].

This type may be injected anywhere, regardless of the requirement.

## Builders

Sometimes, instead of explicitly creating a [CoroutineScope] object, we prefer to just use a coroutineScope builder function within a function.

``` kotlin
@Test
fun some_test() = runBlockingProvided {
  someSuspendFunction()
}

@Test
fun some_test() = runBlockingTestProvided {
  someSuspendFunctionWithADelay()
}
```

| **Name**                   | **Description**
| -------------------        | ---------------
| [runBlockingProvided]      | Uses [runBlocking], but injects a [DispatcherProvider] into its [CoroutineScope].  Use this function if you want normal dispatch behavior.
| [runBlockingTestProvided]  | Uses [runBlockingTest], but injects a [DispatcherProvider] into its [TestCoroutineScope].  Use this function if you want the explicit time control of [runBlockingTest].


### Minimum Gradle Config
Click to expand a field.

&nbsp;<details open> <summary> <b>Groovy</b> </summary>

Add to your module's `build.gradle`:

``` groovy
repositories {
  mavenCentral()
}

dependencies {

  // core
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3"
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3"
  implementation "com.rickbusarow.dispatch:dispatch-core:1.0.0-beta03"

  testImplementation "com.rickbusarow.dispatch:dispatch-core-test:1.0.0-beta03"
  testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3"
}
```

</details>


&nbsp;<details> <summary> <b>Kotlin Gradle DSL</b> </summary>

Add to your module's `build.gradle.kts`:

``` kotlin
repositories {
  mavenCentral()
}

dependencies {

  // core
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
  implementation("com.rickbusarow.dispatch:dispatch-core:1.0.0-beta03")

  testImplementation("com.rickbusarow.dispatch:dispatch-core-test:1.0.0-beta03")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3")
}
```

</details>


<!--- MODULE core-->
<!--- INDEX  -->
[DispatcherProvider]: https://rbusarow.github.io/Dispatch/core//dispatch.core/-dispatcher-provider/index.html
[DefaultDispatcherProvider]: https://rbusarow.github.io/Dispatch/core//dispatch.core/-default-dispatcher-provider/index.html
<!--- MODULE core-test-->
<!--- INDEX  -->
[TestDispatcherProvider]: https://rbusarow.github.io/Dispatch/core-test//dispatch.core.test/-test-dispatcher-provider/index.html
[TestProvidedCoroutineScope]: https://rbusarow.github.io/Dispatch/core-test//dispatch.core.test/-test-provided-coroutine-scope/index.html
[runBlockingProvided]: https://rbusarow.github.io/Dispatch/core-test//dispatch.core.test/run-blocking-provided.html
[runBlockingTestProvided]: https://rbusarow.github.io/Dispatch/core-test//dispatch.core.test/run-blocking-test-provided.html
<!--- END -->

[CoroutineDispatcher]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html

[CoroutineScope]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/coroutine-scope.html

[dispatch-core-test-junit4]: https://rbusarow.github.io/Dispatch/core-test-junit4//index.html
[dispatch-core-test-junit5]: https://rbusarow.github.io/Dispatch/core-test-junit5//index.html

[dispatch-core]: https://rbusarow.github.io/Dispatch/core//index.html

[Dispatchers.setMain]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/kotlinx.coroutines.-dispatchers/set-main.html
[newSingleThreadContext]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/new-single-thread-context.html

[runBlocking]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html
[runBlockingTest]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/run-blocking-test.html

[TestCoroutineDispatcher]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/-test-coroutine-dispatcher/index.html
[TestCoroutineScope]: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/-test-coroutine-scope/index.html
