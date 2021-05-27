package com.hmarianno.course.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hmarianno.course.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text); // o método pode ter qualquer nome

	List<Post> findByTitleContainingIgnoreCase(String text); 

  // essa linha abaixo é uma cópia da proposta original caso eu resolva fazer testes e precise voltar
  //@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2 } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2 } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
