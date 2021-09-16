package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        simple()
            .buffer()
            .collect { value ->
            delay(800) // pretend we are processing it for 300 ms
            println("Collect: $value")
        }
    }
    println("Collected in $time ms")
}