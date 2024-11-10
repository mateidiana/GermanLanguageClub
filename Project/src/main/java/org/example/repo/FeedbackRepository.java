package org.example.repo;
import org.example.model.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackRepository implements IRepository<Feedback> {
    private List<Feedback> feedbacks;
    private static FeedbackRepository instance;
    private FeedbackRepository() {
        this.feedbacks=new ArrayList<>();
    }

    @Override
    public List<Feedback> getObjects(){
        return feedbacks;
    }

    @Override
    public void save(Feedback entity) {
        feedbacks.add(entity);
    }

    @Override
    public void update(Feedback entity, Feedback feedbackRepl) {
        int index = feedbacks.indexOf(entity);
        if (index != -1) {
            feedbacks.set(index, feedbackRepl);
        }
    }

    @Override
    public void delete(Feedback object) {
        feedbacks.remove(object);
    }

    public static FeedbackRepository getInstance() {
        if (instance == null) {
            instance = new FeedbackRepository();
        }
        return instance;
    }
}



