# Android MVVM Architecture (HILT)

Consist of three main layer (3 Project modules with dependency between them)
- Presentation - UI (Activity, Fragment, ViewModel, ViewModelFactory)
- Domain (Usecase)
- Data - Data source (Rest API, Local DB)

Architecture has no dependency injection library. All injections between layers are done manually using 'inversion of control' concept
Reference : https://antonioleiva.com/clean-architecture-android/

Dependencies in Use
- Hilt (Dependecy Injection)
- LiveData + ViewModel
- Coroutines + Flow
- Kotlinx serialization (Json Serialization Deserialization)
- Retrofit (Rest API Library)
- Coil (Image Loader)

Add On
- ktlint is integrated to maintain kotlin coding standards
- Github action to perform the following
  - Run KtLint
  - Run Unit test cases
  - Build Debug APK
  - Save APK as a Github Artifact
  
All References can be found in this [wiki](https://github.com/adilmas13/android-architecture/wiki/References)

