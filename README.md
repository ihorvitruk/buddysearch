# Buddy Search
//TODO description will be here

## Demo
![Demo movie]
(https://github.com/ihorvitruk/buddysearch/blob/master/demo.gif)

## Used libraries
* __Dagger2__ - used to make the app more structural and its modules - more independent (weak connected). 
* __RxJava/RxAndroid__ - used for multithreading.
* __FirebaseAuth__ - used for user authentication.
* __FirebaseDatabase__ - used as a remote data repository.
* __Realm__ - used for local data caching.
* __Play Services Auth__ - used for signing in with Google.
* __Android Data Binding Library__.
* __LeakCanary__ - used to avoid possible memory leaks.
* __Lombok__ - useful annotations.
* __AppCompat__, __Design Library__.

Libraries for testing
* __JUnit__
* __Mockito__

## Project Setup
* [Add Firebase to Android Project](https://firebase.google.com/docs/android/setup), both for __data__ and __presentation__ modules. (As a result you should have two __google-services.json__ files, one for __data__ module and one for __presentation__ module.)
* Go to the [Firebase Console](https://console.firebase.google.com) and navigate to your project:
  * Select the **Auth** panel and then click the **Sign In Method** tab.
  * Click **Google** and turn on the **Enable** switch, then click **Save**.
* Compile the __presentation__ module and run on your device or emulator. (should have Play Services enabled)

## Description of Classes and layers
* __Data__ layer
* __Domain__ layer - the whole business logic of your project is described here. When you decide to implement a new feature in app, start from this layer. Describe new __dto__, __usecases__ and __repositories__ for it. 
** __Dto__ - data class on __domain__ layer.
** __UseCase__ - appropriate business logic use case is described here. 
** __Repository__ - provides appropriate data.
//TODO description for classes: entity, store, repository etc will be here

## TODO
* Add __presentation_mvvm module__ - Write alternative presentation layer to demonstrate MVVM architectural pattern.
* Write __data layer tests__.
* Write __presentation layer tests__.
* Add __NavigationDrawer__ and a few __fragments__ with some new logic: __Dialogs List, Users Map__ (using Google Map) etc. 
* Write __Kotlin__ version of this project to demonstrate power and benefits of this programming language on real project example.

##License

    Copyright 2016 Ihor Vitruk
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

