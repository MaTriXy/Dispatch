[android-lifecycle](./index.md)

[CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html) functionality linked with an [Android Lifecycle](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html).

## Contents

* [Types](#types)
* [Member functions](#member-functions)
* [Extension functions](#extension-functions)

## Types

| **Name**       | **Description**
| -------------  | --------------- |
| [LifecycleCoroutineScope](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/index.html) | [MainCoroutineScope](https://rbusarow.github.io/Dispatch/core//dispatch.core/-main-coroutine-scope.html) with a [Lifecycle](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html), capable of automatically cancelling and restarting coroutines along with that lifecycle.
| [MinimumStatePolicy](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/-minimum-state-policy/index.html) | Defines the behavior of a [Lifecycle](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html)-aware [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) when it passes below its minimum [Lifecycle.State](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html)

## Member functions

| **Name**          | **Description**
| -------------     | --------------- |
| [launchOnCreate](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/launch-on-create.html)  | Creates a coroutine tied to a [Lifecycle](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html) which will automatically enact a [MinimumStatePolicy](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/-minimum-state-policy/index.html) upon dropping below [Lifecycle.State.CREATED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#CREATED)
| [launchOnStart](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/launch-on-start.html)   | Creates a coroutine tied to a [Lifecycle](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html) which will automatically enact a [MinimumStatePolicy](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/-minimum-state-policy/index.html) upon dropping below [Lifecycle.State.STARTED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#STARTED)
| [launchOnResume](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/launch-on-resume.html) | Creates a coroutine tied to a [Lifecycle](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html) which will automatically enact a [MinimumStatePolicy](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/-lifecycle-coroutine-scope/-minimum-state-policy/index.html) upon dropping below [Lifecycle.State.RESUMED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#RESUMED)

## Extension functions

[LifecycleOwner](https://developer.android.com/reference/androidx/lifecycle/LifecycleOwner.html) extension suspending functions:

| **Name**                                     | **Description**
| -------------------                          | ---------------
| [onNextCreate](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle-owner/on-next-create.html)  | Executes code one time upon reaching a state of [Lifecycle.State.CREATED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#CREATED)
| [onNextStart](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle-owner/on-next-start.html)    | Executes code one time upon reaching a state of [Lifecycle.State.STARTED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#STARTED)
| [onNextResume](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle-owner/on-next-resume.html)  | Executes code one time upon reaching a state of [Lifecycle.State.RESUMED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#RESUMED)

[Lifecycle](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.html) extension suspending functions:

| **Name**                                | **Description**
| -------------------                     | ---------------
| [onNextCreate](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle/on-next-create.html)  | Executes code one time upon reaching a state of [Lifecycle.State.CREATED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#CREATED)
| [onNextStart](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle/on-next-start.html)    | Executes code one time upon reaching a state of [Lifecycle.State.STARTED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#STARTED)
| [onNextResume](https://rbusarow.github.io/Dispatch/android-lifecycle//dispatch.android.lifecycle/androidx.lifecycle.-lifecycle/on-next-resume.html)  | Executes code one time upon reaching a state of [Lifecycle.State.RESUMED](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State.html#RESUMED)

### Packages

| Name | Summary |
|---|---|
| [dispatch.android.lifecycle](dispatch.android.lifecycle/index.md) |  |

### Index

[All Types](alltypes/index.md)