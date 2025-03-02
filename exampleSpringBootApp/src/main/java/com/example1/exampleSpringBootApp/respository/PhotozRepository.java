package com.example1.exampleSpringBootApp.respository;
import com.example1.exampleSpringBootApp.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotozRepository extends CrudRepository<Photo, Integer>{

}
