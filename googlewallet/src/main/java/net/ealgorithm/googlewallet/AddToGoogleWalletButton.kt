package net.ealgorithm.googlewallet

import android.content.Context
import android.util.AttributeSet
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
    RelativeLayout(context, attrs) {

    private val binding = ViewAddToGooglewalletButtonBinding.inflate(context.layoutInflater, this)

    init {
        isClickable = true
        isFocusable = true
        setBackgroundResource(R.drawable.googlewallet_button_background)
        contentDescription =
            R.string.add_to_googlewallet_button_content_description.idToString(context)
    }
}