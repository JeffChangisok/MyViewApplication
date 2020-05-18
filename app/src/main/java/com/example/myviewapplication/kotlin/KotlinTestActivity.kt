package com.example.myviewapplication.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myviewapplication.R
import kotlinx.android.synthetic.main.activity_kotlin_test.*

/**
 * Created by Jeff on 2020/5/14.
 * 集合、if、lambda
 * 标准函数let、with、run、apply
 * 静态方法
 * 方法的参数
 * 数据类、单例类
 * 操作符号 ?  ?:  ?. !!.
 **/
class KotlinTestActivity : AppCompatActivity() {
    private val tag = "zhang"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)

        val list = listOf(1, 2, 3, 4) //只读
        val mutableList = mutableListOf(1, 2, 3, 4) //读写

        val set = setOf(1, 2, 3, 4)
        val mutableSet = mutableSetOf(1, 2, 3, 4)

        val map = mapOf(1 to "apple", 2 to "peach", 3 to "pear", 4 to "banana")
        val mutableMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3)

        withRunApply(map)
        staticFun()

        Thread(Runnable {
            Log.d(tag, "Thread is running...")
        }).start()

        Thread {
            Log.d(tag, "Thread is running...")
        }.start()

        btn.setOnClickListener {
            showStudent(null)
        }
        showStudent(Student("csr", 17))

        logParams(100, "jeff")
        logParams(200)
        logParams(name = "baby")

        repeat(2) {
            //重复2次
            Log.d("zhang", "重复1次")
        }

    }

    public data class People(val name: String, var love: Boolean)

    /**
     * 标准函数with 需要接受一个类型的对象来作为第一个参数，返回lambda表达式中的最后一行代码
     * 标准函数run 是需要调用一个对象的run函数，返回lambda表达式中的最后一行代码
     * 标准函数apply 也是需要调用一个对象的apply函数，但返回的是对象本身
     */
    private fun withRunApply(map: Map<Int, String>) {
        val str1 = with(StringBuilder()) {
            append("there are some fruits....\n")
            for ((key, value) in map) {
                append("$key: $value\n")
            }
            append("end....")
            toString()
        }//返回的是最后一行代码

        val str2 = StringBuilder().run {
            append("there are some fruits....\n")
            for ((key, value) in map) {
                append("$key: $value\n")
            }
            append("end....")
            toString()
        }//返回的是最后一行代码

        val stringBuilder = StringBuilder().apply {
            append("there are some fruits....\n")
            for ((key, value) in map) {
                append("$key: $value\n")
            }
            append("end....")
        }//返回的是对象本身
        tvResult.text = stringBuilder.toString()
    }

    /**
     * 给参数设定默认值后，调用时可不填该参数
     * 调用方法时，可采用键值对的方式传参，且不受顺序限制
     */
    private fun logParams(money: Int = 999, name: String = "csr") {
        Log.d(tag, "money = $money, name = $name")
    }


    /**
     * 静态方法调用
     */
    private fun staticFun() {
        SingleTest.doSomething()
        SingleTest.doStaticThing()
        Util().doSomething()
        Util.doCompanionThing()
        Util.doStaticThing()
        doSomething()
    }

    /**
     * kotlin中所有参数变量默认都不可为null
     * ? 表示所声明的变量可为null
     * ?. 操作符号表示该对象不为null时正常调用相应方法，否则啥都不做且返回null
     * A ?: B  当A不为null时候返回A，否则返回B
     * !!. 操作符号表示我非常确信该对象不为null，不用做空指针检查，出事了我自己承担
     * let 是kotlin的一个标准函数，能让对象传递到lambda表达式中，然后立即执行其中的代码
     *     值得一提的是，let函数也会把最后一行代码作为返回值，与run函数的区别就是run会在lambda表达式中直接提供对象的上下文，而let中需要声明对象
     */
    private fun showStudent(student: Student?) {

        Log.d(tag, "student.toString() = " + student.toString())

        student?.doSomething()
        student?.eatSomething()

        //使用let函数，避免向上方一样重复进行非空判断
        student?.let { it ->
            //student对象会被传进来，名为it
            it.doSomething()
            it.eatSomething()
            val str = if (it.num == 17) {
                "yes"
            } else {
                "no"
            }//if语句会把最后一行代码作为返回值
            Log.d(tag, str)
        }

        val name = student?.name ?: "no name"
        Log.d(tag, "my name is : $name")
    }

    /**
     * 数据类
     * 自动重写hashCode(),equals(),toString()方法
     */
    data class Student(var name: String, var num: Int) {
        fun doSomething() {
            Log.d("zhang", "student doSomething")
        }

        fun eatSomething() {
            Log.d("zhang", "student eatSomething")
        }
    }

    /**
     * 单例类
     * 所有方法是类似静态方法一样调用
     */
    object SingleTest {
        fun doSomething() {
            Log.d("zhang", "单例类doSomething")
        }

        @JvmStatic
        fun doStaticThing() {
            Log.d("zhang", "单例类中JvmStatic静态方法doSomething")
        }

    }

    class Util {
        fun doSomething() {
            Log.d("zhang", "普通方法doSomething")
        }

        companion object {
            private const val tag = "zhang"

            /*
             * companion object会在Util类中新建一个伴生类
             * doCompanionThing()就是定义在里面的实例方法
             * kotlin会保证Util只存在一个伴生类
             */
            fun doCompanionThing() {
                Log.d(tag, "伴生类的doSomething")
            }

            /*
             * 真正的静态方法
             * JvmStatic只能加在单例类和companion object中的方法
             */
            @JvmStatic
            fun doStaticThing() {
                Log.d(tag, "伴生类中JvmStatic静态方法doSomething")
            }
        }
    }
}
