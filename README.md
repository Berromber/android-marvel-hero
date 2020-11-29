# android-marvel-hero

Android Marvel Hero is a simple test of creating an Android App based on Clean Architecture with MVVM.

You can see all the layers of the Clean Architecture separated in modules:

- App. The logic for the UI and Data Framework.
- Domain. The Business Logic of the App.
- UseCases. The intermediate layer Logic to communicate UI and Repository.
- Data. Where is the logic for the RepositoryPattern to comunicate UI and Data Framework.

For this application has been used this libraries of 3rd parties:

- AndroidX Libs. AppCompat, RecyclerView, ConstraintLayout, AndroidXCore, LifeCycleViewModel, Material.
- Glide. For load images from URL.
- Coroutines. For jobs in secondary thread.
- OkHttp. As a WebService client.
- Retrofit. For consume REST Api Web Services.
- Koin. For Dependency Injection.
- Mockito / Junit. For Unit Test.
- Espresso. For Instrumentation Test.
