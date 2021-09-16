package coroutines

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    withTimeoutOrNull(1000) { // Timeout after 1s
        simple().collect { value -> println(value) } // wont keep running after the timeout
    }
    println("Done")
}