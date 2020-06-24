package com.myviewapplication.propertyanimation.objectanimator

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton


/**
 * Created by dell on 2020/6/13.
 **/
class FallingBallButton(context : Context, attributeSet: AttributeSet) : AppCompatButton(context, attributeSet){
    public fun setFallingPos(pos : Point){
        layout(pos.x, pos.y, pos.x + width, pos.y + height)
    }
}