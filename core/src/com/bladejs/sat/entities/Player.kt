package com.bladejs.sat.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys.D
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array
import com.bladejs.sat.entities.figures.Figure
import com.bladejs.sat.entities.figures.Rectangle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.bladejs.sat.functions.findMax
import com.bladejs.sat.functions.findMin
import com.bladejs.sat.functions.getNormalVector
import com.bladejs.sat.types.Vector2D


class Player(var figures: Array<Figure>) : Rectangle(Gdx.graphics.width.toFloat() / 2f, Gdx.graphics.height.toFloat() / 2f, 40f, 40f), InputProcessor {

    private val speed = 10f
    private var rotation = 0
    private var debug = false

    private fun changePosition(x: Float, y: Float) {
        val changedX = x - this.x
        val changedY = y - this.y
        this.x = x
        this.y = y
        for (i in vertices.indices) vertices[i] += if (i % 2 == 0) changedX else changedY
        shape.vertices = vertices
        shape.setOrigin(x, y)
    }

    fun update(camera: OrthographicCamera) {
        val mousePos = Vector3()
        mousePos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
        camera.unproject(mousePos)
        changePosition(mousePos.x, mousePos.y)
        if(Gdx.input.isKeyJustPressed(D)) debug = !debug
        shape.rotation = 0f
        if (rotation > 0) rotate(speed)
        if (rotation < 0) rotate(-speed)
        rotation = 0
        shape.setOrigin(x, y)
        sat()
    }

    override fun scrolled(amount: Int): Boolean {
        rotation = amount
        return true
    }

    private fun sat() {
        color = Color.BLACK
        for (figure in figures) {
            if (isColliding(figure)){
                figure.color = Color.RED
                this.color = Color.RED
            }
            else figure.color = Color.BLACK
        }
    }

    private fun isColliding(figure: Figure): Boolean {
        debugList.clear()
        figure.debugList.clear()
        debugDots.clear()
        figure.debugDots.clear()

        val normalVectors = Array<Vector2D>()
        for (i in vertices.indices step 2) {
            val j = i + 1
            val k = (i + 2) % vertices.size
            val l = (i + 3) % vertices.size
            normalVectors.add(Vector2D(vertices[i] - vertices[k], vertices[j] - vertices[l]).getNormalVector())
        }

        if (debug) normalVectors.forEach{
            debugList.add(it.getVector2())
        }

        for (i in figure.vertices.indices step 2) {
            val j = i + 1
            val k = (i + 2) % figure.vertices.size
            val l = (i + 3) % figure.vertices.size
            normalVectors.add(Vector2D(figure.vertices[i] - figure.vertices[k], figure.vertices[j] - figure.vertices[l]).getNormalVector())
        }

        if (debug) normalVectors.forEach{
            if(!debugList.contains(it.getVector2())) figure.debugList.add(it.getVector2())
        }

        normalVectors.forEach {
            val projectedPoints1 = Array<Float>()
            val projectedPoints2 = Array<Float>()
            for (i in vertices.indices step 2)
                projectedPoints1.add(Vector2D(vertices[i], vertices[i + 1]).dot(it))
            for (i in figure.vertices.indices step 2)
                projectedPoints2.add(Vector2D(figure.vertices[i], figure.vertices[i + 1]).dot(it))

            val max1 = findMax(projectedPoints1)
            val min1 = findMin(projectedPoints1)
            val max2 = findMax(projectedPoints2)
            val min2 = findMin(projectedPoints2)

            if (max1 < min2 || max2 < min1) return false
        }
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false
    override fun mouseMoved(screenX: Int, screenY: Int): Boolean = false
    override fun keyTyped(character: Char): Boolean = false
    override fun keyUp(keycode: Int): Boolean = false
    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false
    override fun keyDown(keycode: Int): Boolean = false
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false
}