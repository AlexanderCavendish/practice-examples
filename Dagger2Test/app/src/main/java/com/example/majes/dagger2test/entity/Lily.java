package com.example.majes.dagger2test.entity;

import com.example.majes.dagger2test.entity.Flower;

/**
 * @author:majes
 * @date:2018/3/29
 * @email:marlborn@foxmail.com
 */
public class Lily extends Flower {

//    @Inject
//    Lily() {
//    }

    @Override
    public String whisper() {
        return "纯洁";
    }
}
