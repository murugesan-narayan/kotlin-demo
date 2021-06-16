package com.dcb.kotlin.muru

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController (val repo : BookRepository){
    @PostMapping
    fun addBooks(@RequestBody book: Book) {
        repo.save(book)
    }
/*
    @GetMapping("/")
    fun getAllBooks() : List<Book> {
        return repo.findAll().toList()
    }*/
     //this can be simplified like below as well.
    @GetMapping
    fun getAllBooks() = repo.findAll().toList()

    @GetMapping("/{title}")
    fun getBookByTitle(@PathVariable title: String) = repo.findByTitle(title)

}