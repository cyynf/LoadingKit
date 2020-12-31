# LoadingKit
高性能 LoadingDrawable

## Preview
Style           |      Preview
------------    |   -------------
Fade            | <img src='images/fade.gif' alt='RotatingPlane' width="64px" height="64px"/>
FadeScale       | <img src='images/fade_scale.gif' alt='DoubleBounce' width="64px" height="64px"/>
IOS             | <img src='images/ios.gif' alt='Wave' width="64px" height="64px"/>
IOSV2           | <img src='images/iosv2.gif' alt='WanderingCubes' width="64px" height="64px"/>

## Usage
添加 jitPack.io 仓库
``` groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
添加依赖
``` groovy
	implementation 'com.github.cyynf:LoadingKit:1.0.0'
```
简单使用
``` kotlin
    ProgressBar progressBar = findViewById(R.id.progressBar);
    LoadingDrawable drawable = new FadeLoadingDrawable();
    drawable.setupWithProgressBar(progressBar);
```