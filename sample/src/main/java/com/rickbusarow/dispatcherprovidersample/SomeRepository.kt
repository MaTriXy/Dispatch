/*
 * Copyright (C) 2019-2020 Rick Busarow
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

package com.rickbusarow.dispatcherprovidersample

import com.rickbusarow.dispatcherprovider.*
import kotlinx.coroutines.*

/**
 * This would normally a singleton,
 * but we don't have a DI framework here, so we'll just _suspend_ disbelief.
 */
class SomeRepository(private val coroutineScope: IOCoroutineScope) {

  suspend fun getSomethingExpensive() = withIO {
    delay(5000)
    "suspend function is complete!"
  }

  fun getSomethingExpensiveUnstructured() = coroutineScope.asyncIO {
    delay(5000)
    "deferred function is complete!"
  }
}
