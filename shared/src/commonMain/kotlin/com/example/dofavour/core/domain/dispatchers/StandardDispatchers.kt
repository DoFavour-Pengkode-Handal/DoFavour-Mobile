package com.example.dofavour.core.domain.dispatchers

import com.example.dofavour.core.domain.dispatchers.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher

expect class StandardDispatchers: DispatchersProvider {
    override val main: CoroutineDispatcher
    override val io: CoroutineDispatcher
    override val default: CoroutineDispatcher
    override val unconfined: CoroutineDispatcher
}