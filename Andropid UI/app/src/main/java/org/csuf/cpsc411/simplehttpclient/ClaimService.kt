package org.csuf.cpsc411.simplehttpclient

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import java.lang.reflect.Type
import java.util.*
import java.text.SimpleDateFormat

class ClaimService (val ctx : CustomActivity){

    var claimList : MutableList<Claim> = mutableListOf()
    var currentIndx : Int = 0

    companion object {
        private var claimService : ClaimService? = null

        fun getInstance(act : CustomActivity) : ClaimService {
            if (claimService == null) {
                claimService = ClaimService(act)
            }

            return claimService!!
        }
    }

    fun getAll()  {
        val client = AsyncHttpClient()
        val requestUrl = "http://10.0.2.2:8080/ClaimService/getAll"

        Log.d("Claim Service", "Sending the HTTP Request. ")

        client.get(requestUrl, GetAllServiceRespHandler())
    }

    fun addClaim(claimObject : Claim) {
        // Generate UUID
        val uuid = UUID.randomUUID()
        claimObject.id = uuid.toString()

        // Generate Claim status
        claimObject.isSolved = false

        // Generate date if one is not given
        if (claimObject.date == null || claimObject.date == ""){
            val sdf = SimpleDateFormat("yyyy MM-dd")
            val currentDate = sdf.format(Date())
            claimObject.date = currentDate
        }

        val client = AsyncHttpClient()
        val requestUrl = "http://10.0.2.2:8080/ClaimService/add"
        // 1. Convert the pObj into JSON string
        val pJsonString= Gson().toJson(claimObject)
        // 2. Send the post request
        val entity = StringEntity(pJsonString)
        client.post(ctx, requestUrl, entity,"application/json", addServiceRespHandler())
    }

    inner class addServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                val respStr = String(responseBody)
                Log.d("Claim Service", "The add Service response : ${respStr} + Status code: $statusCode")
            }
            if (statusCode == 200){
                ctx.refreshScreenPostEffect("Successfully Added Claim")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            Log.d("Claim Service","Post Request failed")
            ctx.errorCode = true
            ctx.refreshScreen("Request Failed")
        }
    }

    inner class GetAllServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            // JSON string
            if (responseBody != null) {
                Log.d("Claim Service", "The response JSON string is ${String(responseBody)}")
                val gson = Gson()
                val claimListType: Type = object : TypeToken<List<Claim>>() {}.type
                claimList = gson.fromJson(String(responseBody), claimListType)

//      ToDo: Implement list view activity where list of Claims is presented on screen
////                if (ctx is CustomActivity) {
////                    (ctx as CustomActivity).refreshScreen((claimList[currentIndx]))
////                } else ctx.refreshScreen(claimList[currentIndx])
//                  ctx.refreshScreen(claimList[0])

                Log.d("Claim Service", "The Claim List: $claimList")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            TODO("Not yet implemented")
        }
    }
}