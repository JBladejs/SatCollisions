package com.bladejs.sat.functions

import com.badlogic.gdx.math.Vector2

fun Vector2.getNormalVector() : Vector2 = Vector2(-this.y, this.x)