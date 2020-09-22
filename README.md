
# CommonUi
> 以DSL格式快速实现通用布局

![](https://img.shields.io/github/stars/Surine/CommonUi)
![](https://img.shields.io/github/issues/Surine/CommonUi)
![](https://img.shields.io/github/license/Surine/CommonUi)
![](https://img.shields.io/badge/Kotlin-100%25-orange)

对于个人开发者，平时开发中可能有大量的重复页面或者控件需要写（快速产出），而书写重复代码或者封装控件在一定程度上也带来麻烦。结合Kotlin的DSL特性，封装了一些常用的页面或者控件，目前主打设置页面。
虽然UI风格较为固定，但是本项目提供了一种思路，可以快速实现一个复杂页面，后续有可能支持更多风格或者自定义的布局，总之，本项目的目的就是为了简化一些复杂页面的渲染和数据管理。


## 支持

目前支持的通用页面/布局有以下几种

- Setting页
- Material Dialog

## 使用

```
//project gradle
maven { url 'https://jitpack.io' }

//app gradle
implementation 'com.github.Surine:CommonUi:1.0.0'
```

### 通用Setting

#### 支持

通用setting目前支持的item有

- 普通item
- switchitem （带switch开关的）
- slideritem （带滑动进度条的）
- selector （单选/多选器）
- view (自定义的任何布局)



#### 使用

按照下面的步骤，您可以渲染一个完整的设置项，并且不必担心数据持久化问题。

#### 1. 在您的Activity/Fragment页面添加一个父节点。

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/root"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical">

</LinearLayout>
```

#### 2. 将设置项代码绑定父节点

```
 setting(root){
            group {
                item("点击弹出"){
                   //do something
                }
                switchItem("标题","打开","关闭",true,"1"){
                    view, isChecked ->
                }
//                sliderItem()  滑动进度条
//                selector()  选择器
            }
        }
```

上述代码展示了通用setting的层级关系，其中setting为最外层，其中必须包裹group，以group作为一个设置项组，group可以组标题，在组内可以使用任意定义好的item。

#### Item

- 普通item

```kotlin
item("点击弹出"){
//do something
}
```

普通item支持配置标题，副标题和点击事件。得益于Kotlin默认参数，您可以省略某些参数不写。

- SwitchItem

```
switchItem("标题","打开","关闭",true,"tag"){
	view, isChecked ->
}
```

switchitem支持配置标题和副标题状态显示，配置初始化默认值。同时该item自动做数据持久化，只需要指定一个tag，后续如果需要取值，直接使用**SharedPreferences**读取（boolean类型）。您可以在行尾lambda中监听选择状态。

- slideritem

```
sliderItem("标题","副标题"){
	view, value ->  
}  
```

sliderItem是一个滑动进度条形式的item，支持配置标题副标题，进度范围与步长，同时也支持使用tag来缓存用户操作后的结果（Float类型）。

- selector

```
selector(normalSelect = intArrayOf(0),mutiSelect = false,tag = "selector",element = {
    selectItem("标题","副标题")
    selectItem("标题","副标题")
    selectItem("标题","副标题")
}){
//选择结果
}
```

selector是一个支持单选和多选的选择器item，通过指定selectItem元素来生成布局，结果由行尾lambda返回，tag依然为持久化的key，存取类型为string

- view

```
view(R.layout.activity_main){
		view ->  
}
```

您可以指定在view item的参数中指定自己的布局，并在行尾lambda内使用它。







### 未完
