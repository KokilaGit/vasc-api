package com.vasc.vasc_api.controllers;

import com.vasc.vasc_api.entities.Feedback;
import com.vasc.vasc_api.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
 import java.net.URI;
 import java.util.HashMap;



@RestController
@RequestMapping("/feedbacks")

public class FeedbackController {

    private final FeedbackService feedbackService;
    public FeedbackController(FeedbackService feedbackService){
        this.feedbackService= feedbackService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Feedback>> findAllFeedback(){
        return ResponseEntity.ok(feedbackService.findAllFeedbacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackByID(@PathVariable Integer id){
        return ResponseEntity.ok(feedbackService.getFeedbackByID(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback){
        System.out.println("selvi POST Method is executed" + feedback.toString());
        Feedback savedFeedback = feedbackService.addFeedback(feedback);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/feedbacks/{id}")
                .buildAndExpand(savedFeedback.getId())
                .toUri();

        return ResponseEntity.created(location).body(feedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback>  updatedPlayer(@PathVariable Integer id, @RequestBody Feedback updates){
        return ResponseEntity.ok(feedbackService.updateFeedback(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteFeedback(@PathVariable Integer id){

        HashMap<String, Object> responseMap = feedbackService.deletePlayer(id);

        if(responseMap.get("feedbackInfo") == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }

        return ResponseEntity.ok(responseMap);
    }


}
