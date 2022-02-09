package com.example.diary_us

class Users {
    var id: String? = null
    var displayname: String? = null
        private set
    var imageURL: String? = null

    constructor(id: String?, displayname: String?, imageURL: String?) {
        this.id = id
        this.displayname = displayname
        this.imageURL = imageURL
    }

    constructor() {}

    fun setDisplayame(username: String?) {
        displayname = username
    }
}