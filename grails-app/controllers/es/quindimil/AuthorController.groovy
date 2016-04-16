package es.quindimil

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')
class AuthorController {

    AuthorService authorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Could be tested by http://localhost:8080/author/search?name=0
     * @return
     */
    def search(){
        [result: authorService.search(params.name)]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Author.list(params), model:[authorCount: Author.count()]
    }

    def show(Author author) {
        respond author
    }

    def create() {
        respond new Author(params)
    }

    @Transactional
    def save(Author author) {
        if (author == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (author.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond author.errors, view:'create'
            return
        }

        author.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'author.label', default: 'Author'), author.id])
                redirect author
            }
            '*' { respond author, [status: CREATED] }
        }
    }

    def edit(Author author) {
        respond author
    }

    @Transactional
    def update(Author author) {
        if (author == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (author.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond author.errors, view:'edit'
            return
        }

        author.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'author.label', default: 'Author'), author.id])
                redirect author
            }
            '*'{ respond author, [status: OK] }
        }
    }

    @Transactional
    def delete(Author author) {

        if (author == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        author.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'author.label', default: 'Author'), author.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'author.label', default: 'Author'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
