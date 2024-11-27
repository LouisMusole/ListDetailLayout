package com.drcmind.myapp.utils

data class Discussion(
    val participants : Pair<String, String>,
    val messages : List<Message>,
)
