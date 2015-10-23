package com.grailsinaction

class Post {
    String content
    Date dateCreated

    static hasMany = [ tags : Tag ]
    static belongsTo = [user: User]

    static constraints = {
        content blank: false
    }

    static mapping = {
        sort dateCreated: "desc"
    }
}
