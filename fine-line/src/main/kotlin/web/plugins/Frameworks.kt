package web.plugins

import com.auth0.jwk.UrlJwkProvider
import domain.group.GroupRepository
import domain.group.GroupService
import domain.group.GroupServiceImpl
import domain.membership.MembershipRepository
import domain.membership.MembershipService
import domain.membership.MembershipServiceImpl
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import web.controllers.GroupController
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import kotlinx.serialization.json.Json
import repository.group.GroupRepositoryImpl
import repository.membership.MembershipRepositoryImpl
import io.ktor.server.auth.jwt.jwt
import java.net.URL

fun Application.configureFrameworks() {
    val dotenv = dotenv {}

    install(Koin) {
        modules(module {
            //Repositories
            single<MembershipRepository> { MembershipRepositoryImpl() }
            single<GroupRepository> { GroupRepositoryImpl() }

            //Services
            single<MembershipService> { MembershipServiceImpl(get()) }
            single<GroupService> { GroupServiceImpl(get(), get()) }

            //Controllers
            single { GroupController(get()) }
        })
    }

    val issuer = dotenv["KEYCLOAK_ISSUER"]
    val myRealm = dotenv["KEYCLOAK_REALM"]
    var jwkProvider = UrlJwkProvider(URL("${issuer}/protocol/openid-connect/certs"))

    install(Authentication) {
        jwt("keycloak-bearer") {
            realm = myRealm
            verifier(jwkProvider, issuer) {
                acceptLeeway(3)
            }
            validate { credentials ->
                    UserIdPrincipal(credentials.payload.id)
            }
        }
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}
