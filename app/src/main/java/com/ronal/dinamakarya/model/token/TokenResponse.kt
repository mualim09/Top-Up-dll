package com.ronal.dinamakarya.model.token

data class TokenResponse(
    val token_type : String,
    val access_token : String,
    val expires_in : String,
)