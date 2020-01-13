package com.bladejs.sat.entities.figures

import com.bladejs.sat.functions.getTriangleVertices

class Triangle(x: Float, y: Float, edgeLength: Float = 20f, rotation: Float = 0f): Figure(x, y, getTriangleVertices(x, y, edgeLength), rotation)