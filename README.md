# RetrofitCompose

## 프로젝트 구조

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/57c107b9-3857-498c-ab61-25ccb5740a70/01989754-8797-4553-b70d-e8f73ca9781f/Untitled.png)

**Domain 계층에서 Respository 를 호출하는데 왜 독립적인가 ?
→ UseCase 에서 필요한 부분들을 Respository interface 로 선언 이후 Data 계층에서 선언된 interface 를 상속받아 구현하기 때문에 Domain 은 독립적이고, Data 계층은 Domain 계층에 의존을 하고있다.**

## Libraries

- AndroidX
    - Activity Compose
    - ViewModel Compose
- Android Dagger & Hilt, Hilt ViewModel
- Android Compose
- Android Coroutine (Kotlin)
- Retrofit
- Compose Glide
- Movie Open API : *`https://www.omdbapi.com/`*

참고 문서

Compose Glide : 

[Glide v4 : Compose](https://bumptech.github.io/glide/int/compose.html)

2023 드로이드 나이츠 : https://github.com/droidknights/DroidKnights2023_App

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
