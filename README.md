# Content Loader
Styling your snackbar easily and quickly

## Installation
#### repositories
```kotlin
maven { url 'https://jitpack.io' }
```

#### dependencies
```kotlin
implementation 'com.github.eriffanani:ContentLoader:1.0.0'
```

### Result
<img src="https://user-images.githubusercontent.com/26743731/174986838-097c62e3-a131-40a9-8749-cbcb6d8de87f.gif" width="180"/> <img src="https://user-images.githubusercontent.com/26743731/174987015-5947051a-53a8-4def-9a14-dd127348af7d.gif" width="180"/> <img src="https://user-images.githubusercontent.com/26743731/174986942-04f009a3-a12d-4687-bd97-0b31d9e81564.gif" width="180"/> <img src="https://user-images.githubusercontent.com/26743731/174987064-ccc27106-f0e4-4c10-9319-72daa29afd4a.gif" width="180"/> <img src="https://user-images.githubusercontent.com/26743731/174987094-2005fe7e-766c-4328-a60f-15b12a0f8f8e.gif" width="180"/>

## How To Use
```xml
<com.erif.contentloader.ContentLoaderFrameLayout
    android:id="@+id/content_loader"
    style="@style/ContentLoader.AutoStart">
  
    <com.erif.contentloader.ContentLoaderView
        style="@style/Loader.Square.Small"/>
  
</com.erif.contentloader.ContentLoaderFrameLayout>

<YourContentView
    android:id="@+id/content_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>

```
## Java
```java
YourContentView contentView = findViewById(R.id.content_view);
ContentLoaderFrameLayout loader = findViewById(R.id.content_loader);
// Do your process
loader.startAndHideContent(contentView);
// When process finished
loader.stopAndShowContent(contentView);
```

### ContentLoaderView
##### General
```xml
<com.erif.contentloader.ContentLoaderView
    android:layout_width="100dp"
    android:layout_height="30dp"
    android:layout_margin="10dp"
    app:viewShape="rounded"
    app:cornerRadius="15dp"
    android:color="@color/purple_500"/>
```
#### Result
<img src="https://user-images.githubusercontent.com/26743731/174992983-480a3ee9-f670-470e-ad5d-012390f6b6cd.jpeg" width="250"/>

##### With Library Styles (Square, Rounded, Circle, Text)
```xml
<com.erif.contentloader.ContentLoaderView
    styles="@style/Loader.Circle"/>
```
#### Result
<img src="https://user-images.githubusercontent.com/26743731/174993779-e5737960-b724-4963-b43b-bdd5a7efa336.jpeg" width="200"/> <img src="https://user-images.githubusercontent.com/26743731/174993841-d73e66c6-f9e2-4426-afbd-a5bb11ea9f2c.jpeg" width="200"/> <img src="https://user-images.githubusercontent.com/26743731/174993864-d6de2058-1605-48de-b021-58b8edf16511.jpeg" width="200"/> <img src="https://user-images.githubusercontent.com/26743731/174993872-b96a9973-2136-467d-885c-35a2c9a6a31e.jpeg" width="200"/>

#### Information
This library is still being developed further, please provide feedback if you find a bug. Thank you
