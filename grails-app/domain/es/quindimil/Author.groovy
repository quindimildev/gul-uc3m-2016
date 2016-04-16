package es.quindimil

class Author {

    String name

    //implements a 1:n relation
    static hasMany = [books: Book]

    /*all attributes are not nullable then no setting is needed.
    could be deleted*/
    static constraints = {
    }

    String toString(){
        name
    }

    static mapping = {
        name column: 'name'
    }
}
