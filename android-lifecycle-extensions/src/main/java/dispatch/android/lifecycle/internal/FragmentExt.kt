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

package dispatch.android.lifecycle.internal

import androidx.fragment.app.*
import androidx.lifecycle.*
import dispatch.android.lifecycle.*

internal fun Fragment.onNext(
  block: LifecycleCoroutineScope.() -> Unit
) {
  var invoked = false

  val observer = Observer { owner: LifecycleOwner? ->

    if (owner != null) {

      owner.lifecycleScope.block()
      invoked = true
    } else if (invoked) {
      viewLifecycleOwnerLiveData.removeObservers(this@onNext)
    }
  }

  viewLifecycleOwnerLiveData.observe(this@onNext, observer)
}

internal fun Fragment.onEvery(
  block: LifecycleCoroutineScope.() -> Unit
) {

  val observer = Observer { owner: LifecycleOwner? ->

    owner?.lifecycleScope?.block()
  }

  viewLifecycleOwnerLiveData.observe(this@onEvery, observer)
}
