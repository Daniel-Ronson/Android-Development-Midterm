package com.example.Dao

import com.almworks.sqlite4java.SQLiteConnection
import java.io.File

class Database constructor(var dbName : String=""){

    init {
        var dbName = "C:\\Users\\Dan\\IdeaProjects\\DatabaseFiles\\claimService.db"
        val dbConn = SQLiteConnection(File(dbName));
        dbConn.open()
        val sqlStmt = "create table if not exists person (first_name text, last_name text, ssn text)"
        val createClaimServiceTable = "create table if not exists claimService (id string PRIMARY KEY, title, date, isSolved int DEFAULT 0)"
        dbConn.exec(sqlStmt)
        dbConn.exec(createClaimServiceTable)

    }

    fun getDbConnection() : SQLiteConnection{
        val dbConn = SQLiteConnection(File("C:\\Users\\Dan\\IdeaProjects\\DatabaseFiles\\claimService.db"));
        dbConn.open()
        return dbConn
    }
    // single object pattern
    companion object{
        private var dbObj : Database? = null;

        fun getInstance(dbName : String="") :  Database? {
            if(dbObj == null){
                dbObj = Database(dbName)
            }
            return dbObj;
        }
    }
}