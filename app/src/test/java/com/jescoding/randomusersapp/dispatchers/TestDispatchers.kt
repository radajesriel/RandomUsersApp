package com.jescoding.randomusersapp.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatchers : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
    override val io: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
    override val default: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
    override val unconfined: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
}