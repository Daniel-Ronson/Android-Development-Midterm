package org.csuf.cpsc411.simplehttpclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

open class CustomActivity : AppCompatActivity() {
    lateinit var claimService: ClaimService
    lateinit var claimList : MutableList<Claim>

    // XML attributes
    lateinit var claimTitleView : EditText
    lateinit var dateView : EditText
    lateinit var statusMessage : TextView
    lateinit var bView : Button
    lateinit var cardView : LinearLayout

    var isClaimAdded : Boolean = false
    var errorCode : Boolean = false

    // After Posting To  HTTP Server is Successful
    fun refreshScreenPostEffect(message:String?) {
        //
        Log.d("Claim Service", "Refreshing the Screen. ")
        //
        claimTitleView.setText("")
        dateView.setText("")

        var message = if (message == null) "Status Updated" else message
        statusMessage.setText(message)
        isClaimAdded = true
    }

    // Refreshes Screen to initial state and with given message
    fun refreshScreen(message:String?){
        claimTitleView.setText("")
        dateView.setText("")
        var message =  if (message == null) "<Status Message>" else message
        statusMessage.setText(message)
        isClaimAdded = false
    }

    private fun setIds(){
         claimTitleView = findViewById(R.id.claim_title)
         dateView = findViewById(R.id.date)
         statusMessage  = findViewById(R.id.status)
         bView = findViewById(R.id.add_btn)
         cardView = findViewById(R.id.card_content)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cardGenerator = ObjDetailScreenGenerator(this)
        val colView = cardGenerator.generate()
        setContentView(colView)

        //  Set all the ids in the xml
        setIds()

        // Send Claim to HTTP Server
        claimService = ClaimService.getInstance(this)

        bView.setOnClickListener{
            // Get the next Person object
            // Check Title Field
            if(claimTitleView.text.toString() == ""){
                statusMessage.setText("Title is Required")
                errorCode = true // This is for the click listeners, so the screen will refresh
            }
            else{
                var claimObject = Claim(null,claimTitleView.text.toString(),dateView.text.toString(),null)
                claimService.addClaim(claimObject)
            }
        }

        // Add Click Listeners for Refreshing Screen
        // Refresh Screen After adding Claim or receiving error message
        claimTitleView.setOnClickListener{
            if(isClaimAdded || errorCode) refreshScreen("<Status Message>")
        }
        dateView.setOnClickListener{
            if(isClaimAdded || errorCode) refreshScreen("<Status Message>")
        }
        cardView.setOnClickListener{
            if(isClaimAdded || errorCode) refreshScreen("<Status Message>")
        }

    }

}