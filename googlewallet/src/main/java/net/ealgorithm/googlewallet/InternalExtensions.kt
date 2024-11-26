package net.ealgorithm.googlewallet

import android.content.Context
import android.view.LayoutInflater

/**
 * Layout inflater
 */
internal val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

/**
 * Id to string
 *
 * @param context
 * @return
 */
internal fun Int.idToString(context: Context?): String = context?.getString(this) ?: ""

/**
 * Convert Resource to Dimen Int DEFAULT VALUE: 1px
 *
 * @param context
 * @sample R.dimen.appMargin.idToDimen(context)
 * @return Int - Size
 */
internal fun Int.idToDimen(context: Context?): Int = context?.resources?.getDimensionPixelSize(this) ?: 1
