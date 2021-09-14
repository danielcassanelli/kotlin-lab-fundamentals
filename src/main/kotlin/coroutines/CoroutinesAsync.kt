package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.guide.exampleCompose01.doSomethingUsefulOne
import kotlinx.coroutines.guide.exampleCompose01.doSomethingUsefulTwo
import kotlin.system.measureTimeMillis


fun main() = runBlocking<Unit> {
    launch {
        callAsync()
    }
    launch {
        callAsyncStart()
    }
    concurrentSum()
}

suspend fun callAsync() = coroutineScope {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("callAsync::The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

suspend fun callAsyncStart() = coroutineScope {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // some computation
        one.start() // start the first one
        two.start() // start the second one
        println("callAsyncStart::The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}