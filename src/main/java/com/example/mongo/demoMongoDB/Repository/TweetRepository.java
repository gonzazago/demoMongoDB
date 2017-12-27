package com.example.mongo.demoMongoDB.Repository;

import com.example.mongo.demoMongoDB.Entities.Tweets;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweets,String> {

}
