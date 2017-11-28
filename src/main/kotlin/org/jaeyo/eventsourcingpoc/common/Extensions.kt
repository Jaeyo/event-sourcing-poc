package org.jaeyo.eventsourcingpoc.common

import java.util.*

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start

fun <T> List<T>.random() = get((0..size).random())
fun <T> Array<T>.random() = get((0..size).random())
