package net.ealgorithm.googlewallet

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import net.ealgorithm.googlewallet.databinding.ViewAddToGooglewalletButtonBinding

class AddToGoogleWalletButton @JvmOverloads constructor(context: Context,
                                                        attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {

    private val binding = ViewAddToGooglewalletButtonBinding.inflate(context.layoutInflater, this)

}