package tw.personal.jehuty.fsdation.data.remote.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class CommodityDetailResponse(
    val id:String,
    val url:String,
    val name:String,
    val price:Int,
    val format:String,
    val discountPrice:Int,
    val internetPrice:Int,
    val pv:Double,
    val vendor:Vendor,
    val promptLabel: PromptLabel
) :BaseResponse(){

}
@JsonClass(generateAdapter = true)
class PromptLabel(
    val labelList : List<Prompt>
){

}
@JsonClass(generateAdapter = true)
class Prompt(
    val id: String,
    val display:String,
){


}
@JsonClass(generateAdapter = true)
class Vendor(
    val id :String,
    val name:String,
    val maxCashBack:Int,
    val maxDiscount:Int
) {

}