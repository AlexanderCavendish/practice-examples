package com.example.majes.dagger2test.module;

import com.example.majes.dagger2test.entity.Flower;
import com.example.majes.dagger2test.entity.Pot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author:majes
 * @date:2018/3/29
 * @email:marlborn@foxmail.com
 */
@Module
public class PotModule {
    @Provides
    Pot providePot(@FlowerModule.RoseFlower Flower flower) {
        return new Pot(flower);
    }
}
