package com.example.graphic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class MyDraw(context:Context): View(context){

    private val paint:Paint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.apply {
            style = Paint.Style.FILL
            color = Color.WHITE
        }
        canvas?.drawPaint(paint)
        paint.apply {
            isAntiAlias = true
            color = Color .YELLOW
        }
        canvas?.drawCircle(950F,30F,100F,paint)
        paint.color = Color.GREEN
        canvas?.drawRect(20F,650F,950F,680F,paint)

        paint.apply {
            color = Color.BLUE
            style = Paint.Style.FILL
            isAntiAlias = true
            textSize=150F
        }
        canvas?.drawText("Only for cat",30F,648F,paint)
    }
}