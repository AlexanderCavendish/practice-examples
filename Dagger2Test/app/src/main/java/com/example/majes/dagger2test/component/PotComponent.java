package com.example.majes.dagger2test.component;

import com.example.majes.dagger2test.entity.Pot;
import com.example.majes.dagger2test.module.PotModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author:majes
 * @date:2018/3/29
 * @email:marlborn@foxmail.com
 */
@Component(modules = PotModule.class, dependencies = FlowerComponent.class)
public interface PotComponent {

    /**
     * 使用默认的@Scope实现——@Singleton
     * 需要在@Provide和@Component中同时使用才起作用
     */
    Pot getPot();
}
