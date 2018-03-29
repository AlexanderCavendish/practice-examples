package com.example.majes.dagger2test.component;

import com.example.majes.dagger2test.entity.Flower;
import com.example.majes.dagger2test.module.FlowerModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author:majes
 * @date:2018/3/29
 * @email:marlborn@foxmail.com
 */
@Component(modules = FlowerModule.class)
public interface FlowerComponent {

    @FlowerModule.RoseFlower
    Flower getRoseFlower();

    @FlowerModule.LilyFlower
    Flower getLilyFlower();
}
