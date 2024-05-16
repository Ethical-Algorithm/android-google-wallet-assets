package net.ealgorithm.googlewallet

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import net.ealgorithm.googlewallet.databinding.ViewAddToGooglewalletButtonBinding

/**
 * Add to google wallet button
 *
 * @constructor
 *
 * @param context
 * @param attrs
 */
class AddToGoogleWalletButton @JvmOverloads constructor(context: Context,
                                                        attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs), View.OnClickListener {

    private val binding = ViewAddToGooglewalletButtonBinding.inflate(context.layoutInflater, this)
    private var callback: (() -> Unit)? = null

    init {
        binding.apply {
            root.apply {
                isClickable = true
                isFocusable = true
                isFocusableInTouchMode = true
                setBackgroundResource(R.drawable.googlewallet_button_background)
                contentDescription =
                    R.string.add_to_googlewallet_button_content_description.idToString(context)
                setOnClickListener(this@AddToGoogleWalletButton)
            }
            googlewalletOverlayImageView.setOnClickListener(this@AddToGoogleWalletButton)
            googlewalletLabelImageView.setOnClickListener(this@AddToGoogleWalletButton)
        }
    }

    fun setOnButtonClick(callback: () -> Unit) {
        this.callback = callback
    }

    override fun onClick(v: View?) {
        callback?.invoke()
    }
}