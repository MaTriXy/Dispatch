/*
 * Copyright (C) 2020 Rick Busarow
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rickbusarow.dispatcherprovider

import dispatch.core.*
import kotlinx.coroutines.*
import kotlin.coroutines.*

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [DispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **default** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The *default* property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith("withDefault(context, block)", "dispatch.core.withDefault"),
  level = DeprecationLevel.WARNING
)
public suspend fun <T> withDefault(
  context: CoroutineContext = EmptyCoroutineContext,
  block: suspend CoroutineScope.() -> T
): T = withDefault(context, block)

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [DispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **io** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The `io` property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith("withIO(context, block)", "dispatch.core.withIO"),
  level = DeprecationLevel.WARNING
)
public suspend fun <T> withIO(
  context: CoroutineContext = EmptyCoroutineContext,
  block: suspend CoroutineScope.() -> T
): T = withIO(context, block)

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [DispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **main** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The `main` property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith("withMain(context, block)", "dispatch.core.withMain"),
  level = DeprecationLevel.WARNING
)
public suspend fun <T> withMain(
  context: CoroutineContext = EmptyCoroutineContext,
  block: suspend CoroutineScope.() -> T
): T = withMain(context, block)

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [DispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **mainImmediate** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The `mainImmediate` property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "withMainImmediate(context, block)",
    "dispatch.core.withMainImmediate"
  ),
  level = DeprecationLevel.WARNING
)
public suspend fun <T> withMainImmediate(
  context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T
): T = withMainImmediate(context, block)

/**
 * Calls the specified suspending block with a given coroutine context, suspends until it completes, and returns
 * the result.
 *
 * Extracts the [DispatcherProvider] from the `coroutineContext` of the current coroutine,
 * then uses its **unconfined** [CoroutineDispatcher] property to call `withContext(theDispatcher)`,
 * and returns the result.
 *
 * The `unconfined` property always corresponds to the `DispatcherProvider` of the current coroutine.
 *
 * @see withContext
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "withUnconfined(context, block)",
    "dispatch.core.withUnconfined"
  ),
  level = DeprecationLevel.WARNING
)
public suspend fun <T> withUnconfined(
  context: CoroutineContext = EmptyCoroutineContext,
  block: suspend CoroutineScope.() -> T
): T = withUnconfined(context, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.default`) to call `launch(...)`.
 *
 * The `default` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith("launchDefault(context, start, block)", "dispatch.core.launchDefault"),
  level = DeprecationLevel.WARNING
)
public fun CoroutineScope.launchDefault(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> Unit
): Job = launchDefault(context, start, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.io`) to call `launch(...)`.
 *
 * The `io` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith("launchIO(context, start, block)", "dispatch.core.launchIO"),
  level = DeprecationLevel.WARNING
)
public fun CoroutineScope.launchIO(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> Unit
): Job = launchIO(context, start, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.main`) to call `launch(...)`.
 *
 * The `main` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith("launchMain(context, start, block)", "dispatch.core.launchMain"),
  level = DeprecationLevel.WARNING
)
public fun CoroutineScope.launchMain(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> Unit
): Job = launchMain(context, start, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.mainImmediate`) to call `launch(...)`.
 *
 * The `mainImmediate` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "launchMainImmediate(context, start, block)",
    "dispatch.core.launchMainImmediate"
  ),
  level = DeprecationLevel.WARNING
)
public fun CoroutineScope.launchMainImmediate(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> Unit
): Job = launchMainImmediate(context, start, block)

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.unconfined`) to call `launch(...)`.
 *
 * The `unconfined` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see launch
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "launchUnconfined(context, start, block)",
    "dispatch.core.launchUnconfined"
  ),
  level = DeprecationLevel.WARNING
)
public fun CoroutineScope.launchUnconfined(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> Unit
): Job = launchUnconfined(context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.default`) to call `async(...)`.
 *
 * The `default` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "asyncDefault(context, start, block)",
    "dispatch.core.asyncDefault"
  ),
  level = DeprecationLevel.WARNING
)
public fun <T> CoroutineScope.asyncDefault(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> T
): Deferred<T> = asyncDefault(context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.io`) to call `async(...)`.
 *
 * The `io` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "asyncIO(context, start, block)",
    "dispatch.core.asyncIO"
  ),
  level = DeprecationLevel.WARNING
)
public fun <T> CoroutineScope.asyncIO(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> T
): Deferred<T> = asyncIO(context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.main`) to call `async(...)`.
 *
 * The `main` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "asyncMain(context, start, block)",
    "dispatch.core.asyncMain"
  ),
  level = DeprecationLevel.WARNING
)
public fun <T> CoroutineScope.asyncMain(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> T
): Deferred<T> = asyncMain(context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.mainImmediate`) to call `async(...)`.
 *
 * The `mainImmediate` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "asyncMainImmediate(context, start, block)",
    "dispatch.core.asyncMainImmediate"
  ),
  level = DeprecationLevel.WARNING
)
public fun <T> CoroutineScope.asyncMainImmediate(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> T
): Deferred<T> = asyncMainImmediate(context, start, block)

/**
 * Creates a coroutine and returns its future result as an implementation of [Deferred].
 *
 * Extracts the [DispatcherProvider] from the [CoroutineScope] receiver, then uses its **default** [CoroutineDispatcher]
 * property (`coroutineContext.dispatcherProvider.unconfined`) to call `async(...)`.
 *
 * The `unconfined` property always corresponds to the `DispatcherProvider` of the current `CoroutineScope`.
 *
 * @see async
 */
@Deprecated(
  message = "This function was moved.  This will be removed with the 1.0.0 release.",
  replaceWith = ReplaceWith(
    "asyncUnconfined(context, start, block)",
    "dispatch.core.asyncUnconfined"
  ),
  level = DeprecationLevel.WARNING
)
public fun <T> CoroutineScope.asyncUnconfined(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> T
): Deferred<T> = asyncUnconfined(context, start, block)

