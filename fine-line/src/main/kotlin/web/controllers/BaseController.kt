package web.controllers
import com.auth0.jwt.JWT
import io.ktor.server.application.ApplicationCall

open class BaseController {
    fun getUserIdFromToken(call: ApplicationCall): String? {
        val header = call.request.headers["Authorization"]
        if (header == null) return null

        val token = header.removePrefix("Bearer ")

        val jwt = JWT.decode(token)
        return jwt.getClaim("sub").asString()
    }
}