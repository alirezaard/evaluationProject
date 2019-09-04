package com.gamelectronics.evaluateProject;

import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;


public interface CrudTest {
    void createTest() throws DuplicateElementException;
    void duplicateInsertTest() throws DuplicateElementException;
    void getAllTest() throws DuplicateElementException;
    void validReadTest() throws ResourceNotFoundException, DuplicateElementException;
    void notValidReadTest() throws ResourceNotFoundException;
    void updateTest() throws ResourceNotFoundException, DuplicateElementException;
    void deleteTest() throws ResourceNotFoundException, DuplicateElementException;
    void deleteAll();
}
