package com.example.zhuyanliang.hardlibrary;


public class HardControl{

    public static native int ledCtrl(int which,int status);
    public static native int ledOPen();
    public static native void ledClose();

    static{
        try {
            System.loadLibrary("hardcontrol");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}