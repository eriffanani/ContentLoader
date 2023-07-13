# Content Loader
This library was created to make it easier for android developers to create placeholders before data or content is displayed. This library can also be combined with ('com.facebook.shimmer:shimmer:0.5.0') as a placeholder view component.

## Installation
#### repositories
```gradle
maven { url 'https://jitpack.io' }
```

#### dependencies
```gradle
implementation 'com.github.eriffanani:ContentLoader:1.2.0'
```

### Example results
<img src="https://user-images.githubusercontent.com/26743731/174986838-097c62e3-a131-40a9-8749-cbcb6d8de87f.gif" width="250"/> <img src="https://user-images.githubusercontent.com/26743731/174987015-5947051a-53a8-4def-9a14-dd127348af7d.gif" width="250"/> <img src="https://user-images.githubusercontent.com/26743731/174986942-04f009a3-a12d-4687-bd97-0b31d9e81564.gif" width="250"/> <img src="https://user-images.githubusercontent.com/26743731/174987064-ccc27106-f0e4-4c10-9319-72daa29afd4a.gif" width="250"/> <img src="https://user-images.githubusercontent.com/26743731/174987094-2005fe7e-766c-4328-a60f-15b12a0f8f8e.gif" width="250"/>

## How To Use
```xml
<com.erif.contentloader.LoaderContainer
    android:id="@+id/content_loader"
    style="@style/ContentLoader.AutoStart">
  
    <com.erif.contentloader.LoaderView
        style="@style/Loader.Square.Small"/>
  
</com.erif.contentloader.LoaderContainer>

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

## Kotlin
```kotlin
val contentView: YourContentView = findViewById(R.id.content_view);
val loader: ContentLoaderFrameLayout = findViewById(R.id.content_loader);
// Do your process
loader.startAndHideContent(contentView);
// When process finished
loader.stopAndShowContent(contentView);
```

### ContentLoaderFrameLayout
```xml
<com.erif.contentloader.LoaderView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:autoStart="true"
    android:duration="1000"/>
```

### ContentLoaderView
* General
```xml
<com.erif.contentloader.LoaderView
    android:layout_width="100dp"
    android:layout_height="30dp"
    android:layout_margin="10dp"
    app:viewShape="rounded"
    app:cornerRadius="15dp"
    android:color="@color/purple_500"/>
```
#### Results
<img src="https://user-images.githubusercontent.com/26743731/174992983-480a3ee9-f670-470e-ad5d-012390f6b6cd.jpeg" width="250"/>

* With Library Styles (Square, Rounded, Circle, Text)
```xml
<com.erif.contentloader.LoaderView
    styles="@style/Loader.Circle"/>
```
#### Result
<img src="https://user-images.githubusercontent.com/26743731/174993779-e5737960-b724-4963-b43b-bdd5a7efa336.jpeg" width="200"/> <img src="https://user-images.githubusercontent.com/26743731/174993841-d73e66c6-f9e2-4426-afbd-a5bb11ea9f2c.jpeg" width="200"/> <img src="https://user-images.githubusercontent.com/26743731/174993864-d6de2058-1605-48de-b021-58b8edf16511.jpeg" width="200"/> <img src="https://user-images.githubusercontent.com/26743731/174993872-b96a9973-2136-467d-885c-35a2c9a6a31e.jpeg" width="200"/>

* Banner
```xml
<com.erif.contentloader.LoaderViewBanner
    android:layout_width="match_parent"
    android:layout_height="150dp"
    app:bannerPeep="leftAndRight"
    android:padding="12dp"
    app:peepPaddingVertical="18dp"
    app:cornerRadius="15dp"/>
```
#### Result
<img src="https://user-images.githubusercontent.com/26743731/174996895-c94feed2-72d5-4924-baed-8bf61acd0a7d.png" width="300"/>

### Best practice to use this library for list or recyclerview
* create new layout (ex: content_loader_item.xml)
* create your own content loader view
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="10dp"
    android:gravity="center_vertical">
    <com.erif.contentloader.LoaderView
        style="@style/Loader.Square.Small"/>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:gravity="center_vertical"
        android:layout_marginStart="10dp">
        <com.erif.contentloader.LoaderView
            style="@style/Loader.Text"/>
        <com.erif.contentloader.LoaderView
            style="@style/Loader.Text.Subtitle.Medium"
            android:layout_marginTop="5dp"/>
    </LinearLayout>
</LinearLayout>
```
* create new layout (ex: content_loader.xml)
* include content_loader_item.xml into content_loader.xml
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">
    <include layout="@layout/content_loader_item"/>
    <include layout="@layout/content_loader_item"/>
    <include layout="@layout/content_loader_item"/>
</LinearLayout>
```
* include content_loader.xml into your activity_main.xml
```xml
<com.erif.contentloader.LoaderContainer
    android:id="@+id/content_loader"
    style="@style/ContentLoader.Match.AutoStart">
    <include layout="@layout/content_loader"/>
</LinearLayout>
```
#### Result
<img src="https://user-images.githubusercontent.com/26743731/174999139-32907130-0580-447a-89bd-8370c4fb9cd5.jpeg" width="200"/>

* if you still don't understand the use of this library, please download this project for you to learn more.

#### Information
This library is still being developed further, please provide feedback if you find a bug. Thank you

### Licence
```license
Copyright 2022 Mukhammad Erif Fanani

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
