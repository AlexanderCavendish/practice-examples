package com.example.majes.dagger2test.entity;

import com.example.majes.dagger2test.entity.Flower;

/**
 * @author:majes
 * @date:2018/3/29
 * @email:marlborn@foxmail.com
 */
public class Rose extends Flower {

    @Override
    public String whisper() {
        return "热恋";
    }

//    @Inject
//    public Rose() {
//    }
}
