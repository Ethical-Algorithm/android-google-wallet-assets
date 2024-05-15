package net.ealgorithm.googlewallet

import android.content.Context
import android.view.LayoutInflater

internal val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)