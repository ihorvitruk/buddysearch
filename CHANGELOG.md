Change Log
==========

Version 1.1.0 *(2016-06-11)*
----------------------------

* **`Presenters` were moved from `Activities` to `Loaders`**. Now, `Presenters` are not destroyed on configuration changes. That's why it is possible to save some data cache or current state information in them.

* **Refactored Dagger `components`, `modules` and `scopes`**. See details in updated [README.md](https://github.com/ihorvitruk/buddysearch/blob/master/README.md).

* **Added new feature: `Edit Profile`**.

* **Used new libraries: `Parceler`, `Dart (Henson)`, `Saripaar`**. See details in updated [README.md](https://github.com/ihorvitruk/buddysearch/blob/master/README.md).

* **Introduced `OnDataChangedListener`**, which allows to track changes on specific data. See example with `OnUserChangedListener` in updated code.

* **Integrated `Firebase Cloud Messaging (FCM)`**. Now, if Dialog Screen is not opened or the application is in background, the user will receive a push notification and, by clicking on it, he will be able to open appropriate Dialog Screen to start messaging.


Version 1.0.0
----------------------------

Initial release.
