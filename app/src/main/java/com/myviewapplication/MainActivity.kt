package com.myviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.myviewapplication.viewanimation.AnimationCodeActivity
import com.myviewapplication.viewanimation.AnimationTestActivity
import com.myviewapplication.activity.BaseDrawActivity
import com.myviewapplication.databinding.ActivityMainBinding
import com.myviewapplication.kotlin.KotlinTestActivity
import com.myviewapplication.propertyanimation.objectanimator.ObjectAnimatorActivity
import com.myviewapplication.propertyanimation.valueanimator.ValueAnimatorActivity
import com.myviewapplication.viewbanner.TestMainActivity
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "zhang"

    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        mBinding.lifecycleOwner = this  //用处待研究

        mBinding.btnBaseDraw.setOnClickListener(this)
        mBinding.btnAnimationTest.setOnClickListener(this)
        mBinding.btnAnimationCode.setOnClickListener(this)
        mBinding.btnValueAnimator.setOnClickListener(this)
        mBinding.btnObjectAnimator.setOnClickListener(this)
        mBinding.btnFunTest.setOnClickListener(this)
        mBinding.btnVideoBanner.setOnClickListener(this)
    }

    private fun to(cls: Class<*>) = startActivity(Intent(this, cls))

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBaseDraw -> to(BaseDrawActivity::class.java)
            R.id.btnAnimationTest -> to(AnimationTestActivity::class.java)
            R.id.btnAnimationCode -> to(AnimationCodeActivity::class.java)
            R.id.btnValueAnimator -> to(ValueAnimatorActivity::class.java)
            R.id.btnObjectAnimator -> to(ObjectAnimatorActivity::class.java)
            R.id.btnFunTest -> funTest()
            R.id.btnVideoBanner -> to(TestMainActivity::class.java)
        }
    }

    //在xml中引用
    fun kotlinTest(view: View) = to(KotlinTestActivity::class.java)

    private fun funTest() {
        val resourceTP = intArrayOf(5, 5, 5, 5, 5, 5, 5)
        val heroList = ArrayList<Hero>()
        for (i in resourceTP.indices) {
            heroList.add(Hero(i, resourceTP[i]))
        }

        Log.d(TAG, "resource:")
        showData(heroList)
        bubbleSort(heroList)
        Log.d(TAG, "bubbleSort:")
        showData(heroList)
    }

    /**
     * 冒泡排序
     */
    private fun bubbleSort(heroList: ArrayList<Hero>) {
        for (i in heroList.indices) {
            for (j in 0 until heroList.size - i - 1) {
                if (heroList[j].tp > heroList[j + 1].tp) {
                    val tempTp = heroList[j]
                    heroList[j]  = heroList[j + 1]
                    heroList[j + 1] = tempTp
                }
            }
        }
    }

    /**
     * 快速排序
     */
    private fun quickSort(heroList: ArrayList<Hero>){
        var low = 0
        var high = heroList.size - 1
        var key = heroList[0].tp


    }

    private fun showData(heroList: ArrayList<Hero>) {
        val sb = StringBuilder()
        heroList.forEach { sb.append(it.name).append(": ").append(it.tp).append("  ") }
        Log.d(TAG, sb.toString())
    }

    private data class Hero(var name: Int, var tp: Int)
}
