package com.grailsinaction

class UserController {
    static scaffold = true

    def search() {}

    def results(String loginId) {

//        def users = User.where {
//            loginId =~ loginId
//        }.list()

//        def users = User.findAllByLoginIdLike("%${loginId}%")

        def users = User.where { loginId =~ "%${loginId}%" }.list()

        return [users     : users,
                term      : params.loginId,
                totalUsers: User.count()]
    }

    def advSearch() {}

    def advResults() {
        def profileProps = Profile.metaClass.properties*.name
//        def profileMethods = Profile.metaClass.methods*.name
        def profiles = Profile.withCriteria {
            "${params.queryType}" {

                params.each { field, value ->

                    if (profileProps.grep(field) && value) {
                        ilike(field, value)
                    }
                }

            }

        }
        [profiles: profiles]
    }

    def update() {
        def user = session.user?.attach()
        if (user) {
            user.properties = params
            if (user.save()) {
                flash.message = "Successfully updated user"
            } else {
                flash.message = "Failed to update user"
            }
            [user: user]
        } else {
            response.sendError(404)
        }
    }

    def register() {
        if (request.method == "POST") {
            def user = new User(params)
            if (user.validate()) {
                user.save()
                flash.message = "Successfully Created User"
                redirect(uri: '/')
            } else {
                flash.message = "Error Registering User"
                return [user: user]
            }
        }
    }

}
