package com.bill.demo.exceptions



class AppErrorModel (

    var status: Int? = null,
    var message: String? = null,
    var details: Map<String, String?>
)