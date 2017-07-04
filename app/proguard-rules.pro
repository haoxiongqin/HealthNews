# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/jiangqq/Documents/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
   #代码混淆压缩比，在0~7之间，默认为5，一般不做修改
  -optimizationpasses 5
   #混合时不使用大小写混合，混合后的类名为小写
   -dontusemixedcaseclassnames
   #指定不去忽略非公共库的类
   -dontskipnonpubliclibraryclasses
   #保留Annotation不混淆
   -keepattributes *Annotation*,InnerClasses
   # 避免混淆泛型
   -keepattributes Signature
   # 抛出异常时保留代码行号
   -keepattributes SourceFile,LineNumberTable
   # 指定混淆是采用的算法，后面的参数是一个过滤器
   -optimizations !code/simplification/cast,!field/*,!class/merging/*
   #############################################
   #
   # Android开发中一些需要保留的公共部分
   #
   #############################################
   #申明自己的jar包
 -libraryjars libs/jsoup-1.8.3.jar
 -libraryjars libs/nineoldandroids-2.4.0.jar
   #把不需要混淆的类，如四大组件
 -keep public class * extends android.app.Fragment
 -keep public class * extends android.app.Activity
 -keep public class * extends android.app.Application
 -keep public class * extends android.app.Service
 -keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.content.ContentProvider
 -keep public class * extends android.app.backup.BackupAgentHelper
 -keep public class * extends android.preference.Preference
 -keep public class * extends android.support.v4.**
 -keep public class * extends android.support.v7.**
 -keep public class * extends android.support.annotation.**
 -keep public class * extends android.view.View
 -keep public class com.android.vending.licensing.ILicensingService
  # 保留support下的所有类及其内部类
 -keep class android.support.** {*;}
  # 保留R下面的资源
 -keep class **.R$* {*;}
 # 保留在Activity中的方法参数是view的方法，
 # 这样以来我们在layout中写的onClick就不会被影响
 -keepclassmembers class * extends android.app.Activity{
     public void *(android.view.View);
 }
 # 保留我们自定义控件（继承自View）不被混淆
 -keep public class * extends android.view.View{
     *** get*();
     void set*(***);
     public <init>(android.content.Context);
     public <init>(android.content.Context, android.util.AttributeSet);
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }
 # 保留Parcelable序列化类不被混淆
 -keep class * implements android.os.Parcelable {
     public static final android.os.Parcelable$Creator *;
 }
 # 保留Serializable序列化的类不被混淆
 -keepclassmembers class * implements java.io.Serializable {
     static final long serialVersionUID;
     private static final java.io.ObjectStreamField[] serialPersistentFields;
     !static !transient <fields>;
     !private <fields>;
     !private <methods>;
     private void writeObject(java.io.ObjectOutputStream);
     private void readObject(java.io.ObjectInputStream);
     java.lang.Object writeReplace();
     java.lang.Object readResolve();
 }
 # 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
 -keepclassmembers class * {
     void *(**On*Event);
     void *(**On*Listener);
 }

  #############################################
    #
    # webView处理，项目中没有使用到webView忽略即可
    #-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    #     public *;
    #  }
    #  -keepclassmembers class * extends android.webkit.webViewClient {
    #      public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    #      public boolean *(android.webkit.WebView, java.lang.String);
    #  }
    #  -keepclassmembers class * extends android.webkit.webViewClient {
    #      public void *(android.webkit.webView, jav.lang.String);
    #  }
 #############################################
