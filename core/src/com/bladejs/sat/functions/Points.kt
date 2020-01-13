package com.bladejs.sat.functions

import com.badlogic.gdx.utils.Array

fun findMax(points: Array<Float>): Float {
    var max = points[0]
    for (i in 1 until points.size) {
        if (points[i] > max) max = points[i]
    }
    return max
}

fun findMin(points: Array<Float>): Float {
    var min = points[0]
    for (i in 1 until points.size) {
        if (points[i] < min) min = points[i]
    }
    return min
}