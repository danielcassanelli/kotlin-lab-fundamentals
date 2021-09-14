package coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    // When launch { ... } is used without parameters,
    // it inherits the context (and thus dispatcher) from the CoroutineScope
    // it is being launched from.
    // In this case, it inherits the context of the main runBlocking coroutine
    // which runs in the main thread.
    // The default dispatcher that is used when no other dispatcher is explicitly specified in the scope.
    // It is represented by Dispatchers.Default and uses a shared background pool of threads.
    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }


    // The unconfined dispatcher is appropriate for coroutines
    // which neither consume CPU time nor update any shared data (like UI) confined to a specific thread.
    // After suspension it resumes the coroutine in the thread that
    // is fully determined by the suspending function that was invoked.
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
}