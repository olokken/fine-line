package web.utils

import com.auth0.jwt.JWT
import io.ktor.server.application.ApplicationCall

fun getUserIdFromToken(call: ApplicationCall): String? {
    val header = call.request.headers["Authorization"]
    if (header == null) return null

    val token = header.removePrefix("Bearer ")

    val jwt = JWT.decode(token)
    return jwt.getClaim("sub").asString()
}