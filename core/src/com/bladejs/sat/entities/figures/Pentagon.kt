package com.bladejs.sat.entities.figures

import com.bladejs.sat.functions.getPentagonVertices

class Pentagon(x: Float, y: Float, radius: Float = 20f, rotation: Float = 0f): Figure(x, y, getPentagonVertices(x, y, radius), rotation)