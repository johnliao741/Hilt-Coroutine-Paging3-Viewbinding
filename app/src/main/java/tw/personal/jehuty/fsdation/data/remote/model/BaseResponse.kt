package tw.personal.jehuty.fsdation.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseResponse(
    var IS_VALID: String? = null, // Y/N
    var MSG: String? = null
) {
    override fun toString(): String = "BaseResponse(IS_VALID='$IS_VALID', MSG='$MSG')"
}