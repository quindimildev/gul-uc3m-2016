package es.quindimil


class AuthorService {

    /**
     * Finds an author by its name
     * @param name
     * @return
     */
    List<Author> search(String name){
        /* Other option is creating a criteria
        Author.withCriteria{
            like('name', "%${name}%")
        }*/
        //Dynamic finder - Best option for easier cases
        Author.findAllByNameLike("%${name}%")
    }
}
