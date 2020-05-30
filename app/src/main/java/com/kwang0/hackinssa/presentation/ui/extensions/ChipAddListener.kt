package com.kwang0.hackinssa.presentation.ui.extensions

import com.kwang0.hackinssa.data.models.Tag

interface ChipAddListener {
    fun onChipAddedFromExtra(tag: Tag)
    fun onChipAdded(chipStr: String)
}