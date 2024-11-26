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
class AddToGoogleWalletButton
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
    ) : RelativeLayout(context, attrs),
        View.OnClickListener {
        private val binding = ViewAddToGooglewalletButtonBinding.inflate(context.layoutInflater, this)
        private var callback: (() -> Unit)? = null

        init {
            val attributes =
                context.obtainStyledAttributes(attrs, R.styleable.AddToGoogleWalletButton, 0, 0)

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

                val horizontal = R.dimen.googlewallet_button_horizontal_margins.idToDimen(context)
                val vertical = R.dimen.googlewallet_button_vertical_margins.idToDimen(context)
                val marginsHorizontal =
                    attributes.getDimensionPixelSize(
                        R.styleable.AddToGoogleWalletButton_marginsHorizontal,
                        -1,
                    )
                val marginsVertical =
                    attributes.getDimensionPixelSize(
                        R.styleable.AddToGoogleWalletButton_marginsVertical,
                        -1,
                    )
                val marginStart =
                    attributes.getDimensionPixelSize(
                        R.styleable.AddToGoogleWalletButton_marginStart,
                        if (marginsHorizontal == -1) horizontal else marginsHorizontal,
                    )
                val marginTop =
                    attributes.getDimensionPixelSize(
                        R.styleable.AddToGoogleWalletButton_marginTop,
                        if (marginsVertical == -1) vertical else marginsVertical,
                    )
                val marginRight =
                    attributes.getDimensionPixelSize(
                        R.styleable.AddToGoogleWalletButton_marginEnd,
                        if (marginsHorizontal == -1) horizontal else marginsHorizontal,
                    )
                val marginBottom =
                    attributes.getDimensionPixelSize(
                        R.styleable.AddToGoogleWalletButton_marginBottom,
                        if (marginsVertical == -1) vertical else marginsVertical,
                    )

                googlewalletLabelImageView.apply {
                    val layoutParams = layoutParams as RelativeLayout.LayoutParams
                    layoutParams.setMargins(
                        marginStart,
                        marginTop,
                        marginRight,
                        marginBottom,
                    )
                }
                googlewalletLabelImageView.setOnClickListener(this@AddToGoogleWalletButton)
            }
            attributes.recycle()
        }

        /**
         * Set on button click
         *
         * @param callback
         * @receiver
         */
        fun setOnButtonClick(callback: () -> Unit) {
            this.callback = callback
        }

        /**
         * On click
         *
         * @param v
         */
        override fun onClick(v: View?) {
            callback?.invoke()
        }
    }
