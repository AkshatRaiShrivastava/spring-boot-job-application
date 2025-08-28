package com.akshat.FirstSpring.review.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akshat.FirstSpring.company.Company;
import com.akshat.FirstSpring.company.CompanyService;
import com.akshat.FirstSpring.review.Review;
import com.akshat.FirstSpring.review.ReviewRepository;
import com.akshat.FirstSpring.review.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService {
    private ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews;
    }

    @Override
    public boolean addReview(Review review, Long companyId) {

        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId))
                .findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            Review myRev = reviewRepository.findById(reviewId).orElse(null);
            Company company = myRev.getCompany();
            company.getReviews().remove(myRev);
            myRev.setCompany(null);
            companyService.updateCompany(company, reviewId);
            reviewRepository.deleteById(reviewId);
            return true;
        }

        return false;
    }

}
