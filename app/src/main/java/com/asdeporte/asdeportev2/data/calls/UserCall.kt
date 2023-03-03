package com.asdeporte.asdeportev2.data.calls

import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.data.responses.user.UserData

class UserCall
constructor (private val context: MainActivity) {
    data class UserResult(val user: UserData?, val message: String?)
    data class GeneralResult(val success: Boolean?, val message: String?)

}