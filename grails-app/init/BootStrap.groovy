import es.quindimil.Author
import es.quindimil.Role
import es.quindimil.User
import es.quindimil.UserRole

class BootStrap {

    //do this before boot
    def init = { servletContext ->

        //adding some data
        50.times{ authorIndex ->
            Author author = new Author(name: "Author ${authorIndex}")
            3.times{ bookIndex ->
                author.addToBooks(title: "Book${authorIndex}-${bookIndex}")
            }
            author.save()
        }

        //creating a user with ROLE_ADMIN
        User user = new User(username: 'admin', password: 'admin').save()
        Role role = new Role(authority: 'ROLE_ADMIN').save()
        UserRole.create(user, role)
    }

    //do this on stopping application
    def destroy = {
    }
}
