package com.bladejs.sat

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Array
import com.bladejs.sat.entities.Player
import com.bladejs.sat.entities.figures.Figure
import com.bladejs.sat.functions.RNG

class SatTest : ApplicationAdapter() {
    private lateinit var renderer: ShapeRenderer
    private lateinit var camera: OrthographicCamera
    lateinit var figures: Array<Figure>
    private lateinit var player: Player

    override fun create() {
        renderer = ShapeRenderer()
        camera = OrthographicCamera()
        camera.setToOrtho(false,1280f, 720f)
        figures = RNG.getRandomFigures(19)
        player = Player(figures)
        Gdx.input.inputProcessor = player
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        camera.update()
        with(renderer) {
            projectionMatrix = camera.combined
            begin(ShapeRenderer.ShapeType.Filled)
            setAutoShapeType(true)
            color = Color.BLACK
            for (figure in figures) {
                figure.render(this)
            }
            player.render(this)
            end()
        }
        player.update(camera)
    }

    override fun dispose() {
    }
}
