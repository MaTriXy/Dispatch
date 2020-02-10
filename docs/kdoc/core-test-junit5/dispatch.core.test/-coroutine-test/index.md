[core-test-junit5](../../index.md) / [dispatch.core.test](../index.md) / [CoroutineTest](./index.md)

# CoroutineTest

`@ExperimentalCoroutinesApi @ExtendWith([NormalClass(value=dispatch/core/test/TestCoroutineExtension)]) interface CoroutineTest` [(source)](https://github.com/RBusarow/Dispatch/tree/master/core-test-junit5/src/main/java/dispatch/core/test/CoroutineTest.kt#L48)

Convenience interface which tags a test class with a JUnit 5 extension.  This creates a new instance
of [testScope](test-scope.md) before each test, optionally using a custom [testScopeFactory](test-scope-factory.md).

### Before Each:

* A new [TestProvidedCoroutineScope](#) is created using [testScopeFactory](test-scope-factory.md).
* [Dispatchers.Main](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-main.html) is set to the [TestCoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/-test-coroutine-dispatcher/index.html) used by the [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html).

### After Each:

* [cleanupTestCoroutines](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/-test-coroutine-scope/cleanup-test-coroutines.html) is called to ensure there are no leaking coroutines.  Any unfinished coroutine
will throw an [UncompletedCoroutinesError](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/-uncompleted-coroutines-error/index.html).
* [Dispatchers.Main](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-main.html) is reset via [Dispatchers.resetMain](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/reset-main.html).

### Requires JUnit 5.

``` groovy
dependencies {
  testImplementation "org.junit.jupiter:junit-jupiter:5.5.1"
}
```

``` kotlin
@ExperimentalCoroutinesApi
class CoroutineTestSample : CoroutineTest {

  override lateinit var testScope: TestProvidedCoroutineScope

  lateinit var someClass: SomeClass

  @BeforeEach
  fun beforeEach() {
    someClass = SomeClass(testScope)
  }

  @Test
  fun someSample() = runBlocking {

    someClass.someFunction().await() shouldBe someValue

  }

}
```

``` kotlin
@ExperimentalCoroutinesApi
class CoroutineTestWithFactorySample : CoroutineTest {

  val customScope = TestProvidedCoroutineScope(
    context = CoroutineName("custom name")
  )

  override val testScopeFactory = { customScope }

  override lateinit var testScope: TestProvidedCoroutineScope

  lateinit var someClass: SomeClass

  @BeforeEach
  fun beforeEach() {
    someClass = SomeClass(testScope)
  }

  @Test
  fun someSample() = runBlocking {

    someClass
      .coroutineScope
      .coroutineContext[CoroutineName]!!
      .name shouldBe "custom name"
  }

}
```

**See Also**

[TestCoroutineExtension](../-test-coroutine-extension/index.md)

### Properties

| Name | Summary |
|---|---|
| [testScope](test-scope.md) | `abstract var testScope: TestProvidedCoroutineScope` |
| [testScopeFactory](test-scope-factory.md) | `open val testScopeFactory: () -> TestProvidedCoroutineScope` |

### Functions

| Name | Summary |
|---|---|
| [runBlockingTest](run-blocking-test.md) | `open fun runBlockingTest(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html)` = EmptyCoroutineContext, testBody: suspend `[`TestCoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.test/-test-coroutine-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |