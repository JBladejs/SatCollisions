package com.bladejs.sat.functions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array
import com.bladejs.sat.entities.figures.Figure
import com.bladejs.sat.entities.figures.Pentagon
import com.bladejs.sat.entities.figures.Rectangle
import com.bladejs.sat.entities.figures.Triangle
import kotlin.random.Random

object RNG {
    private fun getAngle(): Float = Random.nextDouble(0.0, 360.0).toFloat()
    private fun getInt(): Int = Random.nextInt(0, 5)
    private fun getFloat(downRange: Float, topRange: Float): Float = Random.nextDouble(downRange.toDouble(), topRange.toDouble()).toFloat()
    fun getRandomFigures(size: Int) : Array<Figure> {
        val figures: Array<Figure> = Array()
        for (i in 0..size) {
            val angle = getAngle()
            val size1 = getFloat(30f, 50f)
            val size2 = getFloat(30f, 50f)
            val x = getFloat(0f, Gdx.graphics.width.toFloat())
            val y = getFloat(0f, Gdx.graphics.height.toFloat())
            val type = getInt()
            val figure : Figure
            figure = when(type) {
                1 -> Triangle(x, y, size1, angle)
                2 -> Rectangle(x, y, size1, size1, angle)
                3 -> Rectangle(x, y, size1, size2, angle)
                else -> Pentagon(x, y, size1, angle)
            }
            figures.add(figure)
        }
        return figures
    }
}