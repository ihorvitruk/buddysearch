# Buddy Search  [![Build Status](https://travis-ci.org/ihorvitruk/buddysearch.svg?branch=master)](https://travis-ci.org/ihorvitruk/buddysearch)
This app demonstrates how to use [Uncle Bob's Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) in practice, with __MVP__ on presentation layer.
App is based on [this famous repository](https://github.com/android10/Android-CleanArchitecture) (thanks author for it).
But for me, there were a lot of things that I wanted somehow to modify or improve, somewhere to simplify or to generify the code with creating __Base__-classes, somewhere to separate or move the code to other places etc. That's why I created this app and I will continue developing it and show, thus, how to apply this architecture approach on real projects. 

## Change Log
See [CHANGLELOG.md](https://github.com/ihorvitruk/buddysearch/blob/develop/CHANGELOG.md)

## Demo 1.1.0
The app, for now, allows to __Sign in with Google__ using __FirebaseAuth__ and to chat with other users across the __FirebaseDatabase__ service. It is also possible to edit and delete your message. App provides __offline data cache__ with __Realm__. Also user can edit his profile on appropriate screen. If user is not on a Dialog Screen or application is in background, the user will receive Push Notification message, which appear in Android Status Bar. By clicking on message notification, Dialog Screen will be opened for messaging.  

Sign In | Push Notification | Edit Profile
--- | --- | ---
![Sign in demo movie] (https://github.com/ihorvitruk/buddysearch/blob/develop/README/sign_in.gif) |  ![Push Notification demo movie] (https://github.com/ihorvitruk/buddysearch/blob/develop/README/push_notification.gif) |  ![Edit profile demo movie] (https://github.com/ihorvitruk/buddysearch/blob/develop/README/edit_profile.gif)

Send/Edit/Delete Message | Sign Out
--- | ---
![Send/edit/delete message demo movie] (https://github.com/ihorvitruk/buddysearch/blob/develop/README/send_edit_delete_message.gif) |  ![Sign out demo movie] (https://github.com/ihorvitruk/buddysearch/blob/develop/README/sign_out.gif)

## Used libraries
* __Dagger2__ - used to make the app more structural and its modules - more independent (weak connected). 
* __RxJava/RxAndroid__ - used for multithreading.
* __FirebaseAuth__ - used for user authentication.
* __FirebaseDatabase__ - used as a remote data repository.
* __FirebaseMessaging__ - used for Push Notifications implementation.
* __Realm__ - used for local data caching.
* __Play Services Auth__ - used for signing in with Google.
* __Android Data Binding Library__.
* __LeakCanary__ - used to avoid possible memory leaks.
* __Lombok__ - useful annotations.
* __Parceler__ - used to make work with `Parcelables` more easier.
* __Saripaar__ - used for user input validation.
* __Dart / Henson__ - annotation processor that make navigation between screens easier (with extras injection).
* __AppCompat__, __Design Library__.

Libraries for testing
* __JUnit__
* __Mockito__

## Project Setup
* [Add Firebase to Android Project](https://firebase.google.com/docs/android/setup), for __data__ module. (As a result you should have __google-services.json__ file in __data__ module)
* Go to the [Firebase Console](https://console.firebase.google.com) and navigate to your project:
  * Select the **Auth** panel and then click the **Sign In Method** tab.
  * Click **Google** and turn on the **Enable** switch, then click **Save**.
* Compile the __presentation__ module and run on your device or emulator. (should have Play Services enabled)

## Description of Classes and Layers
* __Data__ layer - responsible for working with all data that app requires.
 * __Entity__ - data class for __data__ layer.
 * __BaseMapper__ - can convert one object to another and vice versa.
 * __EntityStore__ - provides data (used by __Repository__)
 * __Cache__ - an extension to __EntityStore__ (also can provide all related data), but with own methods for data caching.
* __Domain__ layer - the whole business logic of your project is described here. When you decide to implement a new feature in app, start from this layer. Describe new __dto__, __usecases__ and __repositories__ for it. 
 * __Dto__ - data class on __domain__ layer.
 * __UseCase__ - appropriate business logic use case is described here. 
 * __Repository__ - describes what data should pe provided from __data__ layer.
* __Presentation__ layer - platform-specific implementation of the app.
 * __Model__ - data class for __presentation__ layer (MVP Model).
 * __View__ - MVP View
 * __Presenter__ - MVP Presenter
* __Library__ module - base classes for __data__ and __presentation__ layers can be found here.

## __Dagger 2__ - dependency injection (implemented on `Presentation` Layer) 
* __Scope__
 * `@AppScope` - global scope (one instance of specific object per application)
 * `@ViewScope` - one instance of specific object per MVP-View. As an MVP-View next android classes can be used: `Activity`, `Fragment`, `View`, `Service`. 
* __Component__
 * `AppComponent` - global component (use `@AppScope`)
 * `ViewComponent` - uses `@ViewScope`
* __Module__
 * `AppModule` - provides global application objects like `Schedulers`, `Managers` etc (included in `AppComponent`).
 * `RepositoryModule` - provides `Repository` objects (included in `AppComponent`).
 * `CacheModule` - provides `Cache` objects (included in `AppComponent`).
 * `EntityStoreModule` - provides `EntityStore` objects (included in `AppComponent`).
 * `ViewModule` - provides `MVP-View`-related objects (included in `ViewComponent`).

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
