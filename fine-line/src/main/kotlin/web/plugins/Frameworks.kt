package web.plugins

import com.auth0.jwk.UrlJwkProvider
import domain.fine.FineRepository
import domain.fine.FineService
import domain.fine.FineServiceImpl
import domain.fineType.FineTypeRepository
import domain.fineType.FineTypeService
import domain.fineType.FineTypeServiceImpl
import domain.group.GroupRepository
import domain.group.GroupService
import domain.group.GroupServiceImpl
import domain.membership.MembershipRepository
import domain.membership.MembershipService
import domain.membership.MembershipServiceImpl
import domain.user.UserRepository
import domain.user.UserServiceImpl
import domain.user.models.UserService
import io.github.cdimascio.dotenv.dotenv
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import kotlinx.serialization.json.Json
import repository.group.GroupRepositoryImpl
import repository.membership.MembershipRepositoryImpl
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.plugins.cors.routing.*
import repository.fine.FineRepositoryImpl
import repository.fineType.FineTypeRepositoryImpl
import repository.user.UserRepositoryImpl
import web.controllers.*
import java.net.URL
import kotlin.math.sin

fun Application.configureFrameworks() {
    val dotenv = dotenv {}

    install(CORS) {
        anyHost()

        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Options)

        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Accept)

        allowCredentials = true
        exposeHeader(HttpHeaders.ContentDisposition)
        maxAgeInSeconds = 3600
    }

    install(Koin) {
        modules(module {
            //Repositories
            single<MembershipRepository> { MembershipRepositoryImpl() }
            single<GroupRepository> { GroupRepositoryImpl() }
            single<UserRepository> { UserRepositoryImpl() }
            single<FineTypeRepository> { FineTypeRepositoryImpl() }
            single< FineRepository> { FineRepositoryImpl() }

            //Services
            single<MembershipService> { MembershipServiceImpl(get()) }
            single<GroupService> { GroupServiceImpl(get(), get()) }
            single<UserService> { UserServiceImpl(get()) }
            single<FineTypeService> { FineTypeServiceImpl(get()) }
            single< FineService> { FineServiceImpl(get()) }

            //Controllers
            single { GroupController(get()) }
            single { UserController(get()) }
            single { MembershipController(get()) }
            single { FineTypeController(get()) }
            single { FineController(get()) }
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
