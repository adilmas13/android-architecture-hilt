# Android MVVM Architecture

Consist of three main layer (3 Project modules with dependency between them)
- Presentation - UI (Activity, Fragment, ViewModel, ViewModelFactory)
- Domain (Usecase)
- Data - Data source (Rest API, Local DB)

Dependencies in Use
- LiveData + ViewModel
- Coroutines
- Gson (to be changed to Kotlinx serialization or moshi)
- Retrofit
- Coil (Image Loader)

Add On
- ktlint is integrated to maintain kotlin coding standards
