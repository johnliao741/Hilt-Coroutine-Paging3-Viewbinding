package tw.personal.jehuty.fsdation.domain

abstract class AppUseCase<T, R> {
    abstract suspend operator fun invoke(request: T): R
}