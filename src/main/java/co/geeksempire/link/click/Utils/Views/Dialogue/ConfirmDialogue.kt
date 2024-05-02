/*
 * Copyright © 2023 By Geeks Empire.
 *
 * Created by Elias Fazel
 * Last modified 3/13/23, 7:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package co.geeksempire.link.click.Utils.Views.Dialogue

import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import co.geeksempire.link.click.R
import co.geeksempire.link.click.databinding.ConfirmationLayoutBinding

interface ConfirmDialogueInterface {
    fun confirmed()
    fun dismissed()
}

class ConfirmDialogue (private val context: AppCompatActivity, private val viewGroup: ConstraintLayout) {

    private val confirmationLayoutBinding = ConfirmationLayoutBinding.inflate(context.layoutInflater)

    fun initialize(dialogueTitle: String, dialogueDescription: String) : ConfirmDialogue {

        viewGroup.addView(confirmationLayoutBinding.root)

        confirmationLayoutBinding.root.visibility = View.VISIBLE

        val confirmDialogueParameters = confirmationLayoutBinding.root.layoutParams as ConstraintLayout.LayoutParams
        confirmDialogueParameters.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        confirmDialogueParameters.height = ConstraintLayout.LayoutParams.MATCH_PARENT
        confirmationLayoutBinding.root.layoutParams = confirmDialogueParameters

        confirmationLayoutBinding.confirmTitle.text = dialogueTitle

        confirmationLayoutBinding.confirmDescription.text = Html.fromHtml(dialogueDescription, Html.FROM_HTML_MODE_COMPACT)

        confirmationLayoutBinding.rootView.apply {
            visibility = View.VISIBLE
            startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
        }

        confirmationLayoutBinding.backgroundDecoration.apply {
            visibility = View.VISIBLE
            startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
        }

        return this@ConfirmDialogue
    }

    fun show(confirmDialogueInterface: ConfirmDialogueInterface) {

        confirmationLayoutBinding.rootView.setOnClickListener {

            confirmDialogueInterface.dismissed()

            dismiss()

        }

        confirmationLayoutBinding.confirmTitle.setOnClickListener {  }

        confirmationLayoutBinding.contentWrapper.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_up_dialogue))

        confirmationLayoutBinding.confirmButton.setOnClickListener {

            confirmDialogueInterface.confirmed()

            dismiss()

        }

        confirmationLayoutBinding.dismissButton.setOnClickListener {

            confirmDialogueInterface.dismissed()

            dismiss()

        }

    }

    private fun dismiss() {

        Handler(Looper.getMainLooper()).postDelayed({

            confirmationLayoutBinding.contentWrapper.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_down_dialogue))

            confirmationLayoutBinding.rootView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))

            confirmationLayoutBinding.backgroundDecoration.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))

            viewGroup.removeView(confirmationLayoutBinding.root)

        }, 333)

    }

}