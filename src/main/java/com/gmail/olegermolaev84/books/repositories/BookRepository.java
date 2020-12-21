package com.gmail.olegermolaev84.books.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmail.olegermolaev84.books.models.Book;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	@Query("SELECT b from Book b ORDER BY b.name ASC")
	public List<Book> findAllOderedByName();
	
	/*
	 SELECT * FROM book WHERE name LIKE '%на%';
	 */
	public List<Book> findByNameLikeOrderByNameAsc(String name);
	
	/*
	 SELECT book.id, book.name, book_author.author_id, author.name FROM book 
	     INNER JOIN book_author ON (book.id=book_author.book_id)
	     INNER JOIN author ON (book_author.author_id = author.id)
	     	WHERE author.name='Ильф Илья Арнольдович'
	 ORDER BY book.name ASC;
	 */
	@Query("SELECT b FROM Book b INNER JOIN FETCH b.authors a WHERE a.id=:authorId ORDER BY b.name ASC")
	public List<Book> findByAuthorIdOrderByNameAsc(Long authorId);
	
	/*
	 SELECT book.id, book.name, genre.name FROM book
	 	INNER JOIN genre ON (book.genre_id = genre.id)
	 		WHERE genre.name = 'Стихи'
	 ORDER BY book.name ASC;
	 */
	
	@Query("SELECT b from Book b WHERE b.genre.id=:genreId")
	public List<Book> findByGenreIdOrderByNameAsc(Long genreId);

	/*
	 SELECT book.id, book.name, book_author.author_id, author.name FROM book 
	     INNER JOIN book_author ON (book.id=book_author.book_id)
	     INNER JOIN author ON (book_author.author_id = author.id)
	     	WHERE author.name='Пушкин Александр Сергеевич'
	     	AND book.name LIKE '%Руслан%'
	 ORDER BY book.name ASC;
	 */
	@Query("SELECT b FROM Book b INNER JOIN FETCH b.authors a "+
	 "WHERE a.id=:authorId AND b.name LIKE :bookName ORDER BY b.name ASC")
	public List<Book> findByAuthorIdAndNameLikeOrderByNameAsc(Long authorId, String bookName);
	
	/*
	 SELECT book.id, book.name, genre.name FROM book
	 	INNER JOIN genre ON (book.genre_id = genre.id)
	 		WHERE genre.name = 'Стихи'
	 		AND book.name LIKE '%о%'
	 ORDER BY book.name ASC;
	 */
	@Query("SELECT b from Book b WHERE b.genre.id=:genreId AND b.name LIKE :bookName")
	public List<Book> findByGenreIdAndNameLikeOrderByNameAsc(Long genreId, String bookName);
	
	/*
	 SELECT book.id, book.name, book_author.author_id, author.name, genre.name FROM book 
	     INNER JOIN book_author ON (book.id=book_author.book_id)
	     INNER JOIN author ON (book_author.author_id = author.id)
	     INNER JOIN genre ON (book.genre_id = genre.id)
	     	WHERE author.name='Пушкин Александр Сергеевич'
	     	AND genre.name LIKE 'Поэмы'
	 ORDER BY book.name ASC;
	 */
	@Query("SELECT b FROM Book b INNER JOIN FETCH b.authors a "
			+ "WHERE a.id=:authorId "
			+ "AND b.genre.id = :genreId "
			+ "ORDER BY b.name ASC")
	public List<Book> findByAuthorIdAndGenreIdOrderByNameAsc(Long authorId, Long genreId);
	
	/*
	 SELECT book.name AS book, author.name AS author, genre.name AS genre FROM book 
	     INNER JOIN book_author ON (book.id=book_author.book_id)
	     INNER JOIN author ON (book_author.author_id = author.id)
	     INNER JOIN genre ON (book.genre_id = genre.id)
	     	WHERE author.name='Блок Александр Александрович'
	     	AND genre.name LIKE 'Стихи'
	     	AND book.name LIKE '%хо%'
	 ORDER BY book.name ASC;
	 */
	@Query("SELECT b from Book b "
			+ "INNER JOIN FETCH b.authors a "
			+ "INNER JOIN FETCH b.genre g "
			+ "WHERE a.id=:authorId "
			+ "AND g.id=:genreId "
			+ "AND b.name LIKE :bookName "
			+ "ORDER BY b.name ASC")
	public List<Book> findByAuthorIdAndGenreIdAndNameLikeOrderByNameAsc(Long authorId, Long genreId, String bookName);
}
