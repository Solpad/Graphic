package com.example.graphic

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.lang.Integer.min
import java.lang.Math.cos
import java.lang.Math.sin

private enum class FanSpeed(val label:Int){
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high)


}
private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35
class DialView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet? = null,
    defStyleAttr:Int = 0,
) :View(context,attrs,defStyleAttr){
    private  var radius = 0.0f
    private var fanSpeed = FanSpeed.LOW
    private val pointPosition:PointF = PointF(0.0f,0.0f)


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("",Typeface.BOLD)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = ((min(width,height)/2.0*0.8).toFloat())
    }
// фигачит положение и радиус циферблата
    private fun PointF.computeXYForSpeed(pos:FanSpeed,radius:Float){
        val startAngle = Math.PI*(9/8.0)
        val angle = startAngle + pos.ordinal*(Math.PI/4)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    override fun onDraw(canvas: Canvas?) {
        paint.color = if(fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
        super.onDraw(canvas)

        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas?.drawCircle(pointPosition.x, pointPosition.y, radius/12, paint)

        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas?.drawText(label, pointPosition.x, pointPosition.y, paint)
        }

    }
}