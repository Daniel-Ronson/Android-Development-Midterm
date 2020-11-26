package org.csuf.cpsc411.simplehttpclient

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UIeditorActivity : CustomActivity () {


    private fun setIds(){
        claimTitleView = findViewById(R.id.claim_title)
        dateView = findViewById(R.id.date)
        statusMessage  = findViewById(R.id.status)
        bView = findViewById(R.id.add_btn)
        cardView = findViewById(R.id.card_content)
    }

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        //
        setContentView(R.layout.test_activity)
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