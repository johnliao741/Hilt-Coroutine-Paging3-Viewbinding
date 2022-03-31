package tw.personal.jehuty.fsdation.data.remote.mock

import kotlinx.coroutines.delay
import tw.personal.jehuty.fsdation.data.remote.CommodityService
import tw.personal.jehuty.fsdation.data.remote.model.*
import java.lang.Exception
import kotlin.random.Random

@Deprecated(
    "for local test",
    ReplaceWith("CommodityService", "tw.personal.jehuty.fsdation.data.remote"),
    DeprecationLevel.WARNING
)
class MockCommodityService: CommodityService {

    override suspend fun getInfo(type: Int,page:Int,offset:Int): CommodityItemResponse =
        when (type) {
            0 -> {
                if(page == 1){
                    delay(500)
                }else{
                    delay(1000)
                }
                //test error
                //if(page == 5) throw Exception("more load error")
                CommodityItemResponse(
                    commodityList = List(offset){commodityList.random()}
                ).apply { IS_VALID = "Y"; MSG = "" }
            }
            else -> {
                CommodityItemResponse(
                    commodityList = listOf()
                ).apply { IS_VALID = "Y"; MSG = "" }
            }
        }

    override suspend fun getCommodityDetail(id: String): CommodityDetailResponse {

        val commodityItem = commodityList.findLast { it.id == id } ?: notifyCommodityList.findLast { it.id == id }
        val commodityDetail = commodityItem?.let {
            CommodityDetailResponse(
                id = it.id,
                name = it.name,
                url = it.url,
                price = it.price,
                discountPrice = Random.nextInt(1,(it.price*.8).toInt()),
                internetPrice = Random.nextInt((it.price*.8).toInt(),it.price),
                format = formatList.random(),
                pv = String.format("%.2f",Random.nextDouble(0.0,1.0)).toDouble(),
                vendor = vendorList.random(),
                promptLabel = PromptLabel(promptLabelList.random())

            )
        } ?: throw Exception("發生錯誤")

        return commodityDetail.apply { IS_VALID = "Y"; MSG = "" }
    }
    var notifyCommodityList =List(10){
        index->
        CommodityItem(
            id = String.format("notice%02d",index),
            name = "notice$index",
            url = "https://www.travel.taipei/image/213781",
            price = Random.nextInt(1000,10000),
            status = when(index%2){
                0 -> CommodityType.DeliveryNotice
                else->CommodityType.AddShoppingCar
            }
        )
    }
    var commodityList = List(10){
        index->
        CommodityItem(
            id = String.format("collect%02d",index+1),
            name = String.format("collect%02d",index+1),
            url = "https://www.travel.taipei/image/155549",
            price = Random.nextInt(1000,10000),
            status = when(index % 2){
                0 -> CommodityType.DeliveryNotice
                else -> CommodityType.AddShoppingCar
            }
        )
    }



    val formatList = listOf("讀取160MB/s 寫入90MB/s","讀取80MB/s 寫入45MB/s","讀取100MB/s 寫入50MB/s","讀取320MB/s 寫入180MB/s","讀取20MB/s 寫入10MB/s")
    val vendorList = listOf(
        Vendor(id = "v1",name = "東森",maxDiscount = 100,maxCashBack = 1),
        Vendor(id = "v2",name = "三立",maxDiscount = 200,maxCashBack = 2),
        Vendor(id = "v3",name = "聯強",maxDiscount = 300,maxCashBack = 3),
        Vendor(id = "v4",name = "華碩",maxDiscount = 400,maxCashBack = 4),
        Vendor(id = "v5",name = "台朔",maxDiscount = 500,maxCashBack = 5),
    )
    val promptLabelList = listOf(
        listOf(
            Prompt(
                id = "p1",
                display = "24H"
            ),
            Prompt(
                id = "p2",
                display = "宅配"
            ),
            Prompt(
                id= "p3",
                display = "折價券"
            )
        ),
        listOf(
            Prompt(
                id = "p1",
                display = "24H"
            ),
            Prompt(
                id = "p2",
                display = "超取"
            )

        ),
        listOf(
            Prompt(
                id = "p1",
                display = "24H"
            ),
            Prompt(
                id= "p3",
                display = "折價券"
            )
        ),
        listOf(
            Prompt(
                id = "p1",
                display = "12H"
            ),
            Prompt(
                id = "p2",
                display = "宅配"
            ),
            Prompt(
                id= "p3",
                display = "折價券"
            )
        ),
        listOf(
            Prompt(
                id = "p1",
                display = "24H"
            ),
            Prompt(
                id = "p2",
                display = "宅配"
            ),
            Prompt(
                id= "p3",
                display = "折價券"
            )
        )
    )
}