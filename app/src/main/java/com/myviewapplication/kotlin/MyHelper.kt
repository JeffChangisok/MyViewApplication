package com.myviewapplication.kotlin

import android.util.Log

/**
 * Created by Jeff on 2020/5/15.
 * kotlin文件中定义的所有方法都是顶层方法
 * 编译器会把顶层文件编译成静态方法
 * 顶层方法在任意位置都可以随意调用
 * 但是在java代码中需要MyHelperKt.doSomething()这么用
 **/

fun doSomething(){
    Log.d("zhang","kt文件doSomething")
}