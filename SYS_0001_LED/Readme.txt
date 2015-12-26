(1) AIDL
1. 把 ILedService.aidl 放入 frameworks/base/core/java/android/os
2. 修改 frameworks/base/Android.mk  添加一行
         core/java/android/os/IVibratorService.aidl \
+        core/java/android/os/ILedService.aidl \

3. mmm frameworks/base

4. 它会生成: ./out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/android/os/ILedService.java

(2) Server : LedService.java 
             SystemServer.java

把新文件上传到服务器, 所在目录:
frameworks/base/services/java/com/android/server/SystemServer.java
frameworks/base/services/core/java/com/android/server/LedService.java

不需要修改 frameworks/base/services/core/Android.mk
它的内容里已经把该目录下所有JAVA文件自动包含进去了：
LOCAL_SRC_FILES += \
    $(call all-java-files-under,java)



(3) JNI : com_android_server_LedService.cpp
          onload.cpp

把新文件上传到服务器, 所在目录:
frameworks/base/services/core/jni/onload.cpp
frameworks/base/services/core/jni/com_android_server_LedService.cpp

修改 frameworks/base/services/core/jni/Android.mk :
  $(LOCAL_REL_DIR)/com_android_server_VibratorService.cpp \
+ $(LOCAL_REL_DIR)/com_android_server_LedService.cpp \


编译：
$ mmm frameworks/base/services
$ make snod
$ ./gen-img.sh

