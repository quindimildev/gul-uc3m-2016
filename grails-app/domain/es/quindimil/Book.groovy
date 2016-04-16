package es.quindimil

class Book {

    String title
    String isbn

    static belongsTo = [author: Author]

    static constraints = {
        title nullable: false
        isbn nullable: true
    }

    String toString(){
        title
    }
}
