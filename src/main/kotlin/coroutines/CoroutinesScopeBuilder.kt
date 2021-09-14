package coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/***
 * runBlocking and coroutineScope builders may look similar because they both wait for
 * their body and all its children to complete. The main difference is that the runBlocking
 * method blocks the current thread for waiting, while coroutineScope just suspends,
 * releasing the underlying thread for other usages. Because of that difference,
 * runBlocking is a regular function and coroutineScope is a suspending function.
 ***/
fun main() = runBlocking {
    doScopeWorld() // will wait here until the end of the function call
    println("Done")
}

/***
 * Both pieces of code inside launch { ... } blocks execute concurrently,
 * with World 1 printed first, after a second from start, and World 2 printed next,
 * after two seconds from start.
 * A coroutineScope in doScopeWorld completes only after both are complete,
 * so doScopeWorld returns and allows Done string to be printed only after.
 ***/
suspend fun doScopeWorld() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(2000L)
        print("World 2")
    }
    launch {
        delay(1000L)
        print("World 1")
    }
    launch {
        printDots() // infinite dot printing
    }
    println("Hello")
}

suspend fun printDots() = coroutineScope {
    while (true) {
        print(".")
        delay(250)
    }
}

