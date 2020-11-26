package org.csuf.cpsc411.simplehttpclient

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView


class ObjDetailScreenGenerator(val ctx : Context) {
    lateinit var layoutObj : LinearLayout

    fun createLinearLayout(horizontal_or_vertical:String): LinearLayout {
        val orientation : Int = if (horizontal_or_vertical == "vertical") LinearLayout.VERTICAL else  LinearLayout.HORIZONTAL
        layoutObj = LinearLayout(ctx)
        var lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutObj.layoutParams = lParams
        layoutObj.orientation = orientation

        return layoutObj
    }

    fun dpFromPx(px: Float): Float {
        return px / ctx.resources.displayMetrics.density
    }
    fun pxFromDp(dp: Float): Float {
        return dp * ctx.resources.displayMetrics.density
    }
    fun generate() : LinearLayout {
        // Create a linearLayout object
        layoutObj = createLinearLayout("vertical")

        // Create  Card View
        val cardView = CardView(ctx)

        // Card View Layout Parameters
        val cardParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        cardParams.setMargins(pxFromDp(20F).toInt(),pxFromDp(20F).toInt(),pxFromDp(20F).toInt(),pxFromDp(10F).toInt())
        cardView.layoutParams = cardParams
        cardView.setContentPadding(pxFromDp(10F).toInt(),pxFromDp(20F).toInt(),pxFromDp(20F).toInt(),pxFromDp(45F).toInt())

        // Content of Card View
        // Create Vertical linear layout as container within card
        var layoutObj_two = LinearLayout(ctx)
        var lParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
             )
        layoutObj_two.layoutParams = lParams
        layoutObj_two.orientation = LinearLayout.VERTICAL
        layoutObj_two.setId(R.id.card_content)

        // Heading 1
        val textView  = TextView(ctx)
        var textLayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = textLayoutParams
        textView.text = "Please Enter Claim Information"
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        layoutObj_two.addView(textView)

       // Data Field 1
       // Linear Layout for Data field 1
      var layoutObj_three = LinearLayout(ctx)
        lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
           ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutObj_three.layoutParams = lParams
        layoutObj_three.orientation = LinearLayout.HORIZONTAL

        // Text View forData field 1
        val textView_two = TextView(ctx)
        textLayoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textLayoutParams.weight = 1.0F
        textView_two.text = "Claim Title"
        textView_two.layoutParams = textLayoutParams

        // Edit text for Data field 1
        val editText_one = EditText(ctx)
        editText_one.layoutParams = textLayoutParams
        editText_one.setId(R.id.claim_title)

        layoutObj_three.addView(textView_two)
        layoutObj_three.addView(editText_one)

        //----------------------------------------
        // Data Field 2
        // Linear Layout for Data field 2
        var layoutObj_four = LinearLayout(ctx)
        lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutObj_four.layoutParams = lParams
        layoutObj_four.orientation = LinearLayout.HORIZONTAL

        // Text View forData field 2
        val textView_three = TextView(ctx)
        textLayoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textLayoutParams.weight = 1.0F
        textView_three.text = "Date"
        textView_three.layoutParams = textLayoutParams

        // Edit text for Data field 2
        val editText_two = EditText(ctx)
        editText_one.layoutParams = textLayoutParams
        editText_two.setId(R.id.date)

        layoutObj_four.addView(textView_three)
        layoutObj_four.addView(editText_two)
        // ----------------------------------------
        // Add Button
        // Horizontal Layout
        var layoutObj_five = LinearLayout(ctx)
        val layoutParams_forlayoutObj_five =  LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams_forlayoutObj_five.gravity = Gravity.RIGHT
        layoutObj_five.layoutParams = layoutParams_forlayoutObj_five
        layoutObj_five.orientation = LinearLayout.HORIZONTAL

        //  Button
        val addButton = Button(ctx)
        addButton.text = "Add"
        //nButton.setId(R.id.next_btn)
        addButton.setBackgroundColor(Color.CYAN)
        val addButtonParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        addButtonParams.gravity = Gravity.BOTTOM
        addButton.layoutParams = addButtonParams
        addButton.setId(R.id.add_btn)
        layoutObj_five.addView(addButton)



        // Status Message
        var layoutObj_six = LinearLayout(ctx)
        lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutObj_six.layoutParams = lParams
        layoutObj_six.orientation = LinearLayout.HORIZONTAL

        // Text View Status Message Labe;
        val statusMesageLabel = TextView(ctx)
        statusMesageLabel.text = "Status Message"
        statusMesageLabel.layoutParams = textLayoutParams

        layoutObj_six.addView(statusMesageLabel)

        // Actual Status
        val StatusMessage = TextView(ctx)
        StatusMessage.text = "<Status Message>"
        StatusMessage.setId(R.id.status)
        StatusMessage.layoutParams = textLayoutParams

        layoutObj_six.addView(StatusMessage)

        // layoutObj_two is inside card view
        // The following Layouts are all rows being added
        layoutObj_two.addView(layoutObj_three)
        layoutObj_two.addView(layoutObj_four)
        layoutObj_two.addView(layoutObj_five)
        layoutObj_two.addView(layoutObj_six)
        cardView.addView(layoutObj_two)


        // Add CardView to Main Screen Layout
        layoutObj.addView(cardView)

       // cardObj.layoutParams =

        // 2. Add ObjDetailSection
//        val fldRowGenerator = ObjDetailSectionGenerator(ctx)
//        val colView = fldRowGenerator.generate()
//        layoutObj.addView(colView)

        // 3. Add Next Button LinearLayout
//        val nLayout = LinearLayout(ctx)
//        val nParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT)
//        // only applied to height now
//        nParams.gravity = Gravity.RIGHT
//        nLayout.layoutParams = nParams
//        nLayout.orientation = LinearLayout.HORIZONTAL
//        nLayout.setBackgroundColor(Color.GRAY)
//        //
//        val nButton = Button(ctx)
//        nButton.text = "Next"
//        nButton.setId(R.id.next_btn)
//        nButton.setBackgroundColor(Color.CYAN)
//        val nbParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        nbParams.gravity = Gravity.BOTTOM
//        nLayout.addView(nButton, nbParams)
        //
       // layoutObj.addView(nLayout, nParams)
        return layoutObj
    }
}