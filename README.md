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
