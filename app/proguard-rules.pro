#-----------------------------------------基本不用动规则----------------------------------------------------
#指定混淆采用的算法，后面的参数是一个过滤器
#这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#代码混淆压缩比，在0和7之间，默认为5,一般不用修改
-optimizationpasses 5

#混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames

#指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses

#指定不去忽略非公共库的类的成员
-dontskipnonpubliclibraryclassmembers

#不做预校验，preverify是prohuard的四个步骤之一
#Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify

#这个是为windows用户设置的，因为Proguard假定使用的操作系统是能区分两个大小写不同的文件名，但是windows不区分
#我是用的linux，所以不存在这个问题
#-dontusemixedcaseclassnames


#有这个，混淆后会生成映射文件
-verbose
#使用printmapping指定映射文件的名称 ****************
-printmapping proguardMapping.txt

#保护代码中的Annotation不被混淆，这个在JSON实体映射中很重要，比如fastjson
-keepattributes *Annoation*

#避免混淆泛型，这个在JSON实体映射很重要
-keepattributes Signature

#抛出异常时保留代码行号， 在异常分析中方便定位
-keepattributes SourceFile，LineNumberTable

#用于告诉Proguard。不要跳过对非公开类的处理。
#默认情况下是跳过的，因为程序中不会引用他们。有些情况是人们编写的代码与类库中的类在同一个包下
#此时需要加入此条声明
-dontskipnonpubliclibraryclasses

#保留R$*类中静态字段的名字
-keepclassmembers class **.R$*{
    public static <fields>;
}
#保留Android四大组件的子类,其他的用到了再补叭
-keep public clas * extends android.app.Activity

#保留继承自Application的子类
-keep public class * extends android.app.Application
#保留所有自定义view控件。
-keep public class * extends android.view.View{
    ***get*();
    void set*(***);
    public <init>(android.context.Context);
    public <init>(android.context.Context, android.util.AttributeSet);
    public <init>(android.context.Context, android.util.AttributeSet, int);
}
#保留Activity的方法参数是view的方法，这样在layout里写onClick就不会被影响
-keepclassmembers class * {
    public void *(android.view.View);
}

#对于带有回调的函数，不能混淆。on**Event,**On*Listener
-keepclassmembers class *{
    void *(**On*Event);
    void *(**On*Listener);
}

#保留Parcelable序列化的类不被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator*;
}
#保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable{
     static final long serialVersionUID;
     private static final java.io.ObjectStreamField[] serialPersistentFields;
     private void writeObject(java.io.ObjectOutputStream);
     private void readObject(java.io.ObjectInputStream);
     java.lang.Object writeReplace();
     java.lang.Object readResolve();
}


#----------------------------针对本身App----------------------------------------------------------
#注意如果要去除日志类，要将系统默认的混淆文件改为：`proguard-android-optimize.txt`，，因为他已经默认打开优化开关
#将日志设置为无效（实际App不打印Log）
# 去除系统的Log类
-assumenosideeffects class LogUtil{
    public static boolean isLoggable(java.lang.String, int);
      public static int v(...);
      public static int i(...);
      public static int w(...);
      public static int d(...);
      public static int e(...);
}
#保留实体类
-keep class com.example.train.bean.**{*;}

#--------------------------针对三方库-------------------------------------------------------------------------
#----------------------------glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule


#--------------------------rxjava  rxandroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}


#-------------------------OkHttp3
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}


#-----------------------------Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keep public class * extends retrofit2.Converter {*;}


#-----------------------------Gson
-keep public class com.google.gson.**
-keep public class com.google.gson.** {public private protected *;}
-keep class sun.misc.Unsafe { *; }
-keepattributes Signature
-keepattributes *Annotation*

