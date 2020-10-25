package com.example.Dao.ClaimService

import com.example.Dao.Dao
import com.example.Dao.Database
import java.text.SimpleDateFormat
import java.util.*

class ClaimServiceDao : Dao(){

    fun addClaimService(claimService : ClaimService) {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // Generate UUID
        val uuid = UUID.randomUUID()
        val uuid_as_string = uuid.toString()

        // Generate Claim status
        val isSolved = 0

        // Generate date if one is not given
        if (claimService.date == null){
            val sdf = SimpleDateFormat("yyyy MM-dd")
            val currentDate = sdf.format(Date())
            claimService.date = currentDate
        }


        // 2. prepare the sql statement
        sqlStmt = "insert into claimService (id, title, date, isSolved) values ('${uuid_as_string}', '${claimService.title}', '${claimService.date}','${isSolved}')"

        // 3. submit the sql statement
        conn?.exec(sqlStmt)
    }


    // Convert Integer to Boolean
    fun Int.toBoolean() = if (this == 1) true else false

    fun getAll() : List<ClaimService> {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "select id, title, date, isSolved from claimService"

        // 3. submit the sql statement
        var claimServiceList : MutableList<ClaimService> = mutableListOf()
        val st = conn?.prepare(sqlStmt)

        // 4. Convert into Kotlin object format
        while (st?.step()!!) {
            val id = st.columnString(0)
            val title = st.columnString(1)
            val date = st.columnString(2)
            val isSolved = st.columnInt(3)

            claimServiceList.add(ClaimService(UUID.fromString(id),title,date,isSolved.toBoolean()))
        }
        return claimServiceList
    }


}