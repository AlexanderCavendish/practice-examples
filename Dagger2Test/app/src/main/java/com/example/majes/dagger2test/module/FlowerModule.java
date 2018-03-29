package com.example.majes.dagger2test.module;

import com.example.majes.dagger2test.entity.Flower;
import com.example.majes.dagger2test.entity.Lily;
import com.example.majes.dagger2test.entity.Rose;

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
public class FlowerModule {

    /**
     * @Module需要和@Provide是需要一起使用的时候才具有作用的，并且@Component也需要指定了该Module的时候。
     * @Module是告诉Component，可以从这里获取依赖对象。 Component就会去找被@Provide标注的方法，相当于构造器的@Inject，可以提供依赖。
     * @Component可以指定多个@Module的，如果需要提供多个依赖的话。 并且Component也可以依赖其它Component存在。
     * 使用默认的@Scope实现——@Singleton
     * 需要在@Provide和@Component中同时使用才起作用
     */

//    @Named("Rose")
    @Provides
    @RoseFlower
    Flower provideRose() {
        return new Rose();
    }

    /**
     * @Qualifier是限定符，而@Named则是基于String的限定符。 <P>
     * 当我有两个相同的依赖（都继承某一个父类或者都是先某一个接口）可以提供给高层时，
     * 那么程序就不知道我们到底要提供哪一个依赖，因为它找到了两个。
     * 这时候我们就可以通过限定符为两个依赖分别打上标记，指定提供某个依赖。
     * </P>
     */
//    @Named("Lily")
    @Provides
    @LilyFlower
    Flower provideLily() {
        return new Lily();
    }

    /**
     * 而@Qualifier的作用和@Named是完全一样的，不过更推荐使用@Qualifier
     * 因为@Named需要手写字符串，容易出错。
     *
     * @Qualifier不是直接注解在属性上的，而是用来自定义注解的。
     */
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RoseFlower {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LilyFlower {
    }

//    @Provides
//    Flower provideFlower() {
//        return new Lily();
//    }


}