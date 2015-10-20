import com.grailsinaction.Post
import com.grailsinaction.Tag
import com.grailsinaction.User

class BootStrap {

    def init = { servletContext ->
        def user = new User(loginId: 'igor', password: 'password').save()
        def tagMy = new Tag(user: user, name: 'MY')
        tagMy.save(failOnError: true)
        def post = new Post(user: user, content: "Igor post")
        post.save(failOnError: true)
        tagMy.addToPosts(post)
        tagMy.ad
    }
    def destroy = {
    }
}
