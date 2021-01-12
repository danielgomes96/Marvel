# Marvel 


## Resume 
The application accesses Marvel's API and displays a list of all the company's comics.

## Screens 
<p align="center">
 <img src="/pictures/splash_dark_theme_screen.jpg" width=20% height=20%>
 <img src="/pictures/splash_default_theme_screen.jpg" width=20% height=20%>
</p>
<p align="center">
 <img src="/pictures/comic_list_dark_theme_screen.jpg" width=20% height=20%>
 <img src="/pictures/comic_list_default_theme_screen.jpg" width=20% height=20%>
</p>
<p align="center">
 <img src="/pictures/comic_detail_dark_theme_screen.jpg" width=20% height=20%>
 <img src="/pictures/comic_detail_default_theme_screen.jpg" width=20% height=20%>
</p>
<p align="center">
 <img src="/pictures/error_screen.jpg" width=15% height=15%>
 <img src="/pictures/error_screen_animated.jpg" width=15% height=15%>
 <img src="/pictures/loading_screen.jpg" width=15% height=15%>
 <img src="/pictures/connected_snackbar_screen.jpg" width=15% height=15%>
 <img src="/pictures/disconnected_snackbar_screen.jpg" width=15% height=15%>
</p>

## Automation
Ktlint - the task validates whether the code standard complies with the lint. Use the `./gradlew ktlint` command to validate.

KtlintFormat - this task modifies the code so that it follows the lint pattern. Use the `./gradlew ktlintFormat` command to adjust the code.

## Continuous Integration
**GitHub CI**

CI tool that allows the creation of customized workflows for repositories on GitHub.

**Workflows**

In this application, two workflows were created. The first for `Main` branche and the second for` featureas e fixes` branches. The following are descriptions of the workflows.

Main - unit tests, Ktlint and APK generation.

Featureas e Fixes - unit tests and Ktlint.

## Architecture
I tried to follow the concepts of Clean Architecture, so I divided the project into:

* app module: module that is started when user opens the application. It contains the SplashActivity, first screen of the app;

* feature-modules: contains the presentation layer of each feature (ex: feature-comic);

* data module: the data layer contains all the code necessary to retrieve the data, whether from the local database or the service;

* domain module: the business logic layer contains entities, use cases and interfaces to communicate with the data module;

* core module: it has some implementations that are used by the rest of the application (ex: connectivity handler, request handler, etc.);

* extension module: this module has extensions that can be reused throughout the project;

* resource module: where colors, themes, fonts and icons are located. In this way, the entire application uses the same resources to maintain a standard in the project (facilitating the implementation of a Design System);

* base module: contains the base classes that are used by the application;

* ui-component module: The UI components were created to facilitate its reuse and to have a standard in the application (facilitating the implementation of a Design System). This module has the UI components created;

* buildSrc module: is where all the dependencies and versions used for the project relies.

## Main dependencies
**Koin** - _dependence injection_
 <p> Library chosen for its simple implementation. As a negative point, there is some loss of performance when compared to other competitors, such as Dagger. There is no significant loss for this application. </p>

**Coroutines** - _dealing with threads and asynchronism_
 <p> Approach suggested by Google and working well with Live Data, makes good use of the device's Threads and Thread Pool, improving application performance. When compared to RxJava, which is its biggest competitor, its positive point is its smaller size and its error handling, which is a little more manual. </p>

**Navigation component** - _navigation between screens_
 <p> Used as a new form of navigation for the application. </p>

**Retrofit** - _HTTP requirements_
 <p> Retrofit is the most widespread library for handling HTTP requests, in addition to being easy to implement. </p>
 
 **Shimmer** - _animation_
 <p> The Facebook library allows you to introduce shimmering animations in a simple way. It is widely used to signal the loading of some content. </p>
 
 **MockK** - _unit tests_
 <p> MockK is a test lib for the Kotlin language. Its use is simple and has the advantage of competitors, the possibility of mocking methods of Objects and Companion Objects without having to create interfaces. On the other hand, it is an exclusive library for Kotlin, not working with the Java language. </p>
 
## Tests
You can visualizate tests performed by me on an old project at the following links:

* [unit test 1]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/search/src/test/java/com/lucasdias/search/presentation/SearchViewModelTest.kt)
* [unit test 2]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/factcatalog/src/test/java/com/lucasdias/factcatalog/FactCatalogAdapter.kt)
* [unit test 3]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/search/src/test/java/com/lucasdias/search/domain/usecase/GetRandomCategoriesFromDatabaseTest.kt)
* [unit test 4]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/search/src/test/java/com/lucasdias/search/data/historic/SearchHistoricRepositoryImplTest.kt)
* [ui test 1]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/factcatalog/src/androidTest/java/com/lucasdias/search/presentation/FactCatalogFragmentTest.kt)
