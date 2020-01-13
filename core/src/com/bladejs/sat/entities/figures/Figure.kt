package com.bladejs.sat.entities.figures

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.bladejs.sat.entities.Player

abstract class Figure(var x: Float, var y: Float, vertices: FloatArray, rotation: Float) {
    val shape = Polygon()
    var vertices: FloatArray
    get() = shape.transformedVertices
    set(value) {
        shape.vertices = value
    }
    var color: Color = Color.BLACK
    var debugList = Array<Vector2>()
    var debugDots = Array<Vector2>()

    init{
        this.vertices = vertices
        shape.setOrigin(x, y)
        shape.rotate(rotation)
    }

    fun rotate(angle: Float) {
        shape.rotate(angle)
    }

    fun render(renderer: ShapeRenderer) {
        renderer.color = color
        renderer.polygon(vertices)
        if (debugList.size > 0) {
            renderer.color = Color.GREEN
            debugList.forEach{renderer.line(Vector2(x - (it.x*50f), y - (it.y*50f)), Vector2(x + (it.x*50f), y + (it.y*50f)))}
        }
        if (debugDots.size > 0) {
            renderer.set(ShapeRenderer.ShapeType.Filled)
            renderer.color = Color.GREEN
            debugDots.forEach { renderer.circle(it.x, it.y, 5f) }
        }
    }

}
