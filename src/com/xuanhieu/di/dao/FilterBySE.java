/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xuanhieu.di.dao;

import com.xuanhieu.di.dto.Student;
import com.xuanhieu.di.util.Filter;

/**
 *
 * @author Admin
 */
public class FilterBySE implements Filter<Student>{

    @Override
    public boolean check(Student t) {
        return t.getMajor().equalsIgnoreCase("SE");
    }
    
}
