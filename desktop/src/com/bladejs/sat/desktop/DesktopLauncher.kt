package com.bladejs.sat.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.bladejs.sat.SatTest

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        with(config) {
            title = "SAT collisions"
            width = 1280
            height = 720
            LwjglApplication(SatTest(), this)
        }
    }
}
