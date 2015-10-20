package com.grailsinaction

class Post {
    String content
    Date dateCreated

    static constraints = {
        content blank: false
    }

    static hasMany = [ tags : Tag ]

    static belongsTo = [user: User]

    static mapping = {
        sort dateCreated: "desc"
    }
}
