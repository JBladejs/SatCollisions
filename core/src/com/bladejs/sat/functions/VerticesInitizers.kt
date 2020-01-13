package com.bladejs.sat.functions

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

val sqrt3t2 = sqrt(3f) / 2f

fun getTriangleVertices(x: Float, y: Float, edgeLength: Float): FloatArray {
    val height: Float = edgeLength * sqrt3t2
    return floatArrayOf(x, y + (2f * height / 3f), x - (edgeLength / 2f), y - (height / 3f), x + (edgeLength / 2f), y - (height / 3f))
}

fun getRectangleVertices(x: Float, y: Float, width: Float, height: Float): FloatArray = floatArrayOf(x - width / 2f, y - height / 2f, x - width / 2f, y + height / 2f,
        x + width / 2f, y + height / 2f, x + width / 2f, y - height / 2f)

fun getPentagonVertices(x: Float, y: Float, radius: Float): FloatArray {
    val c1 = cos(0.4f * PI).toFloat() * radius
    val c2 = cos(PI / 5f).toFloat() * radius
    val s1 = sin(0.4f * PI).toFloat() * radius
    val s2 = sin(0.8f * PI).toFloat() * radius
    return floatArrayOf(x, y + radius, x + s1, y + c1, x + s2, y - c2, x - s2, y - c2, x - s1, y + c1)
}