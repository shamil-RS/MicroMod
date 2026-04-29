# MicroMod — Advanced Compose Custom Layout System

**MicroMod** is an engineering project developed as part of the *Advanced Compose* course by Marcin Moskala, supported by **JetBrains**. The project focuses on an in-depth exploration of Jetpack Compose internal mechanisms, including rendering phases, custom measurement systems, and efficient component reuse.

## Tech Stack

| Category | Technology |
| :--- | :--- |
| **Language** | Kotlin 2.0+ |
| **UI Framework** | Jetpack Compose (Advanced Layouts) |
| **Build System** | Gradle + Convention Plugins (Kotlin DSL) |
| **Navigation** | Navigation3 (Experimental) |
| **Architecture** | Micro-modular Architecture |
| **Optimization** | Custom LazyLayout & SubcomposeLayout |

---

## Technical Highlights

* **Custom Infinite Grid**: A highly optimized artist grid built on top of the low-level `LazyLayout` API. Unlike standard high-level components, this project features a manual implementation of `LazyLayoutItemProvider` and a custom `MeasurePolicy` for granular control over composition and placement.
* **Performance & Stability**: Verified via Layout Inspector, the project achieves **zero recompositions** during active scrolling in any direction. This is accomplished by offloading calculation logic from the composition phase and leveraging efficient `SubcomposeLayoutState` management.
* **Navigation3**: Integration of the latest experimental navigation approach for declarative backstack and screen management.
* **Infrastructure**: Built on micro-modular principles using **Convention Plugins**. This ensures centralized dependency management and standardized build configurations via the `:build-logic` module.

---

## Project Structure

* `:build-logic` — Custom Kotlin DSL Gradle plugins for unified module configuration.
* `:feature` — Independent functional modules (Artist Grid, Detail View).
* `:core` — Design system components, networking layer, and shared utilities.
* `:app` — Aggregator module and navigation graph configuration.

---

## Core Implementation

The core of the project utilizes a custom `LazyLayout` to bypass unnecessary composition overhead:

```kotlin
@Composable
fun LazyLayout(
    itemProvider: () -> LazyLayoutItemProvider,
    modifier: Modifier = Modifier,
    prefetchState: LazyLayoutPrefetchState? = null,
    measurePolicy: LazyLayoutMeasurePolicy,
) {
    val currentItemProvider = rememberUpdatedState(itemProvider)

    LazySaveableStateHolderProvider { saveableStateHolder ->
        val itemContentFactory = remember {
            LazyLayoutItemContentFactory(saveableStateHolder) { currentItemProvider.value() }
        }
        val subcomposeLayoutState = remember {
            SubcomposeLayoutState(LazyLayoutItemReusePolicy(itemContentFactory))
        }
        
        SubcomposeLayout(
            subcomposeLayoutState,
            modifier.traversablePrefetchState(prefetchState),
            remember(itemContentFactory, measurePolicy) {
                { constraints ->
                    val scope = LazyLayoutMeasureScopeImpl(itemContentFactory, this)
                    with(measurePolicy) { scope.measure(constraints) }
                }
            },
        )
    }
}
```

## Layout Inspector Metrics

| Metric | Value | Result |
| :--- | :--- | :--- |
| **Recomposition Count** | 0 | Elimination of redundant composition phases during scroll |
| **Skipped Count** | Maximum | Optimal stability for all UI nodes |
| **Layout Phase** | Optimized | Minimal measurement passes and efficient node placement |

## Record Screen

https://github.com/user-attachments/assets/0f20df46-4128-4395-97d2-eab08b15b809
