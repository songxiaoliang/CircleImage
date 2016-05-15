# CircleImage
CircleImageView
#####for example:

![](https://github.com/songxiaoliang/loadinglayout/blob/master/app/demo/demo.jpg "Gif图加载效果") 

## Development Help

####1.Dependence：

#####Android Studio：
#####Insert the following dependency to build.gradle file of your project:
    
     compile 'com.song:circleimage:1.0.0'
      
####2.CircleImage support function：
      
    app:imgBorderColor="@android:color/holo_orange_dark" //圆角图边框的颜色
      
    app:imgBorderWidth="5dp" //圆角图边框的宽度
      
####3.Use code：

#####layout.xml:

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    
    <co.songlcy.circleimage.view.CircleImage
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/head"
        app:imgBorderWidth="5dp"
        app:imgBorderColor="@android:color/holo_orange_dark"
        />
        
</LinearLayout>

## My Blog
####[Click to view](http://blog.csdn.net/u013718120)
## Contact Me
####Email：563609104@qq.com
    
