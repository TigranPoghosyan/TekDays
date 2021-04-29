class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/events/$nickname"{
            controller = "tekEvent"
            action = "show"
        }

        "/api/$controller/$id?"{
            action = "appData"
        }


        "/users/$userName"{
            controller = "tekUser"
            action = "show"
        }

	}
}
