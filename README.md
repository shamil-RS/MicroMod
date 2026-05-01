# MicroMod — Advanced Compose Custom Layout System

**MicroMod** is a project showing some important concepts in Jetpack Compose: 
 - How to define **Custom Infinite Grid** based on low-level `LazyLayout` API (see `core/designsystem/src/main/kotlin/layout/CustomLazyLayout.kt`). Unlike standard high-level components, this project features a manual implementation of `LazyLayoutItemProvider` and a custom `MeasurePolicy` for granular control over composition and placement. Verified via Layout Inspector, the project achieves **zero recompositions** during active scrolling in any direction. This is accomplished by offloading calculation logic from the composition phase and leveraging efficient `SubcomposeLayoutState` management.
 - How to properly implement micro-modular principles using **Convention Plugins**. This ensures centralized dependency management and standardized build configurations via the `:build-logic` module.
 - How to use Nav3 with a single view model (see `app/src/main/java/com/example/micromod/Nav.kt`).

This project was developed as part of the *Advanced Compose* course by Marcin Moskala, supported by **JetBrains**.

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

## Project Structure

* `:build-logic` — Custom Kotlin DSL Gradle plugins for unified module configuration.
* `:feature` — Independent functional modules (Artist Grid, Detail View).
* `:core` — Design system components, networking layer, and shared utilities.
* `:app` — Aggregator module and navigation graph configuration.

---

## Record Screen Recomposition

https://github.com/user-attachments/assets/0f20df46-4128-4395-97d2-eab08b15b809

## Record Screen APP

https://github.com/user-attachments/assets/b1d54c8f-921f-413e-a553-ee3911ef9404
