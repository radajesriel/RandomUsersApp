package com.jescoding.randomusersapp.dispatchers

import kotlinx.coroutines.Dispatchers

class StandardDispatchers : DispatcherProvider {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
    override val default = Dispatchers.Default
    override val unconfined = Dispatchers.Unconfined
}