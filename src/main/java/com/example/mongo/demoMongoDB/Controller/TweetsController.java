package com.example.mongo.demoMongoDB.Controller;

import com.example.mongo.demoMongoDB.Entities.Persona;
import com.example.mongo.demoMongoDB.Entities.Tweets;
import com.example.mongo.demoMongoDB.Repository.TweetRepository;
import com.example.mongo.demoMongoDB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TweetsController {

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/tweets")
    public Flux<Tweets> getAlls(){
        return  tweetRepository.findAll();
    }

    @GetMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweets>> getTweetById(@PathVariable("id") String id){

        return  tweetRepository.findById(id)
                .map(savedTweet -> ResponseEntity.ok(savedTweet))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PostMapping("/tweets")
    public Mono<Tweets>saveTweets(@RequestBody @Valid Tweets tweets) {
        return tweetRepository.save(tweets);

    }

    @PutMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweets>> editTweets(@PathVariable("id") String id , @Valid @RequestBody Tweets tweet){

        return tweetRepository.findById(id)
                .flatMap(existingTweet -> {
                    existingTweet.setTweet(tweet.getTweet());
                    return tweetRepository.save(existingTweet);
                })
                .map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
                .defaultIfEmpty( new  ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/tweets/{id}")
    public Mono<ResponseEntity<Void>>deleteTweet(@PathVariable("id") String id){

        return  tweetRepository.findById(id)
                .flatMap(existingTweet -> tweetRepository.delete(existingTweet)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/tweets/{id}/{idPersona}")
    public Mono<ResponseEntity<Tweets>> addLikeTweet(@PathVariable("id") String idTweet , @PathVariable("idPersona") String idPersona ){

        Mono<Persona> persona = userRepository.findById(idPersona);
        Mono<Tweets> tweet = tweetRepository.findById(idTweet);
        return tweet.flatMap( (Tweets t) -> {
                    t.getLikes().add(persona.block());
                    return tweetRepository.save(t);
                })
                .map(updateTweet -> new ResponseEntity<>(updateTweet,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
