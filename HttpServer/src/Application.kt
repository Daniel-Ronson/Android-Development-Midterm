package com.example


import com.example.Dao.ClaimService.ClaimService
import com.example.Dao.ClaimService.ClaimServiceDao
import  com.example.Dao.Database
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
//import com.example.Dao.person.Person
//import com.example.Dao.person.PersonDao
//import com.google.gson.GsonGson
import io.ktor.application.*
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Message.Http
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.http.*
import io.ktor.utils.io.readAvailable

fun main(args: Array<String>): Unit {
    // Register PersonStore callback functions

    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module() {
    // extension
    // @annotation
    // routing constructor takes only one parameter which is a lambda function
    // DSL - Domain Specific Language
    routing{

        get("ClaimService/getAll"){
            println("Claim Service: getAll ")
            val claimServiceList = ClaimServiceDao().getAll()
            // JSON Serialization/Deserialization
            val respJsonStr = Gson().toJson(claimServiceList)
            call.respondText(respJsonStr, status= HttpStatusCode.OK, contentType= ContentType.Application.Json)
        }

        post("/ClaimService/add"){
            // Recieve Json as String
            val json = call.receive<String>()

            // Deserialize Json to Claim Service Object
            var claimService = Gson().fromJson(json, ClaimService::class.java)

            println("Recieved: ${claimService}")
            // Add Person list to database
            ClaimServiceDao().addClaimService(claimService);

            call.respondText(
                "The POST request was successfully processed. ",
                status = HttpStatusCode.OK, contentType = ContentType.Text.Plain
            )
        }

        }

    }



