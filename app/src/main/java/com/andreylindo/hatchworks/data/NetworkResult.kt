package com.andreylindo.hatchworks.data

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/6/24
 */
sealed class NetworkResult<T> {
    class Success<T>(val data: T) : NetworkResult<T>()
    class Error<T>(val message: String, code: Int) : NetworkResult<T>()
    class Exception<T>(val throwable: Throwable) : NetworkResult<T>()
}