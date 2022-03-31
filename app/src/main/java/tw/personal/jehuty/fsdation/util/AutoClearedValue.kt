package tw.personal.jehuty.fsdation.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearedValue<T>(fragment: Fragment) : ReadWriteProperty<Fragment, T> {

    private var value: T? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    viewLifecycleOwner.lifecycle.removeObserver(this)
                    value = null
                }
            })
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        value ?: throw IllegalStateException(
            "should never call auto-cleared-value get when it might not be available"
        )

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        this.value = value
    }
}

fun <T> Fragment.autoCleared() = AutoClearedValue<T>(this)