package tw.personal.jehuty.fsdation.data.vo

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}