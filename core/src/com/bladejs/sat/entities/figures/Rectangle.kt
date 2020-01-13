package com.bladejs.sat.entities.figures

import com.bladejs.sat.functions.getRectangleVertices

open class Rectangle(x: Float, y: Float, width: Float = 20f, height: Float = 20f, rotation: Float = 0f): Figure(x, y, getRectangleVertices(x, y, width, height), rotation)
