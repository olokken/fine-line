package web.controllers

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route


class LeaderboardController() : BaseController() {
    fun setUpRoutes(route: Route) {
        route.route("/api/v1/groups/{id}/leaderboard") {
            post {
                getLeaderboard(call)
            }
        }
    }

    fun getLeaderboard(call: ApplicationCall) {

    }
}