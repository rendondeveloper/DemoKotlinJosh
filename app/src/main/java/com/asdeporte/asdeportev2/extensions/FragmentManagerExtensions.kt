package com.asdeporte.asdeportev2.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


fun FragmentManager.getVisibleFragment(fragment: String): Boolean {
    val fragments: List<Fragment> = this.fragments
    val navHost = fragments[0]
    for (child in navHost.childFragmentManager.fragments) {
        if (child.javaClass.simpleName == fragment){
            if (child.isVisible) return true
        }
    }
    return false
}