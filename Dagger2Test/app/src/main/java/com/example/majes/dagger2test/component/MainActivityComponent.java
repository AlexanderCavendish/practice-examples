package com.example.majes.dagger2test.component;

import com.example.majes.dagger2test.MainActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;


/**
 * @author:majes
 * @date:2018/3/29
 * @email:marlborn@foxmail.com <p>
 * 而Dagger2则是用Component来完成依赖注入的，@Component可以说是Dagger2中最重要的一个注解。
 * 定义一个Component的方式。使用接口定义，并且@Component注解。
 * 命名方式推荐为：目标类名+Component，在编译后Dagger2就会为我们生成DaggerXXXComponent这个类，
 * 它是我们定义的xxxComponent的实现，在目标类中使用它就可以实现依赖注入了。
 * Component中一般使用两种方式定义方法。
 * void inject(目标类 obj);
 * Dagger2会从目标类开始查找@Inject注解，自动生成依赖注入的代码，调用inject可完成依赖的注入。
 * Object getObj();
 * 如：Pot getPot();
 * Dagger2会到Pot类中找被@Inject注解标注的构造器，自动生成提供Pot依赖的代码，这种方式一般为其他Component提供依赖。
 */
//@Component(modules = {FlowerModule.class, PotModule.class})
@Component(dependencies = PotComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
