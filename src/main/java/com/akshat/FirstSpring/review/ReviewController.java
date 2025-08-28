package com.akshat.FirstSpring.review;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getAllReviews(id), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Long companyId) {

        boolean isReviewAdded = reviewService.addReview(review, companyId);
        if (isReviewAdded) {
            return new ResponseEntity<>("Review added successfuly !", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not added !", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId, @PathVariable Long companyId){
        Review myrev = reviewService.getReview(companyId, reviewId);
        if(myrev != null){
            return new ResponseEntity<>(myrev, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(myrev, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @PathVariable Review review){
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isUpdated){
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not delted", HttpStatus.NOT_FOUND);
    }

}
