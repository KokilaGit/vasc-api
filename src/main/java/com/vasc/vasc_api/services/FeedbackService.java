package com.vasc.vasc_api.services;


import com.vasc.vasc_api.entities.Feedback;
import com.vasc.vasc_api.repositories.FeedbackRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Optional;
@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
@Transactional
    public Iterable<Feedback>findAllFeedbacks(){
    Iterable<Feedback> allFeedbacks = feedbackRepository.findAll();
    return allFeedbacks;
}
public Feedback getFeedbackByID(Integer id){
    Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);

    if(optionalFeedback.isEmpty()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found with id: " + id);
    }
//        gets the player out of the optional container and returns it to the controller
    return optionalFeedback.get();

}
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
    public Feedback updateFeedback(Integer id, Feedback updates){
        Feedback feedbackToUpdate = getFeedbackByID(id);

        if(updates.getFirstName() != null){
            feedbackToUpdate.setFirstName(updates.getFirstName());
        }
        if(updates.getLastName() != null){
           feedbackToUpdate.setLastName(updates.getLastName());
        }
        if(updates.getEmailId() != null){
           feedbackToUpdate.setEmailId(updates.getEmailId());
        }
        if(updates.getFeedBack() != null){
            feedbackToUpdate.setFeedBack(updates.getFeedBack());
        }

        return feedbackRepository.save(feedbackToUpdate);
    }

    public HashMap<String, Object> deletePlayer(Integer id){
        HashMap<String, Object> responseMap = new HashMap<>();

        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);

        if(optionalFeedback.isEmpty()){
//            if the player does not exist, this is what will be returned
            responseMap.put("wasDeleted", false);
            responseMap.put("playerInfo", null);
            responseMap.put("Message", "Player not found with id: " + id);
            return responseMap;
        }
        feedbackRepository.deleteById(id);
        responseMap.put("wasDeleted", true);
        responseMap.put("playerInfo", optionalFeedback.get());

        return responseMap;
    }

    public void handlePlayerEvent(Feedback feedback){

        feedbackRepository.save(feedback);
    }



}
