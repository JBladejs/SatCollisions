package com.bladejs.sat.types

import com.badlogic.gdx.math.Vector2

data class Vector2D(var x: Float, var y: Float) {
    fun dot(v: Vector2D): Float = x * v.x + y * v.y
    fun getNormalVector(): Vector2D = Vector2D(-y, x)
    fun getVector2(): Vector2 = Vector2(x, y)
}