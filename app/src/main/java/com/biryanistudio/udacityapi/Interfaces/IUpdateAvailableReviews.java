package com.biryanistudio.udacityapi.Interfaces;

import com.biryanistudio.udacityapi.Models.Certification;

import java.util.List;

/**
 * Created by Sravan on 07-Apr-16.
 */
public interface IUpdateAvailableReviews {
    void availableReviews(List<Certification> certifications);
}
