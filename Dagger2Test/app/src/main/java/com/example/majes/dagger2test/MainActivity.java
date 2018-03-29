package com.example.majes.dagger2test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.majes.dagger2test.component.DaggerFlowerComponent;
import com.example.majes.dagger2test.component.DaggerMainActivityComponent;
import com.example.majes.dagger2test.component.DaggerPotComponent;
import com.example.majes.dagger2test.entity.Pot;
import com.example.majes.dagger2test.module.PotModule;

import javax.inject.Inject;

/**
 * @author majes
 * <p>
 * Q1：dagger2是什么，有什么用？
 * A1：dagger2是一个基于JSR-330标准的依赖注入框架，在编译期间自动生成代码，负责依赖对象的创建。
 * <p>
 * Q2：什么是JSR-330
 * A2：JSR即Java Specification Requests，意思是java规范提要。
 * 而JSR-330则是 Java依赖注入标准
 * 关于JSR-330可以阅读这篇文章Java 依赖注入标准（JSR-330）简介，随便看下就好了，不是重点。
 * <p>
 * Q3：用dagger2提供依赖有什么好处
 * A3：为了进一步解耦和方便测试，我们会使用依赖注入的方式构建对象。
 */
public class MainActivity extends AppCompatActivity {

    /**
     * @Inject注解只是JSR-330中定义的注解，在javax.inject包中。 这个注解本身并没有作用，它需要依赖于注入框架才具有意义，
     * 用来标记需要被注入框架注入的方法，属性，构造。
     */
    @Inject
    Pot pot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DaggerMainActivityComponent.create().inject(this);

        DaggerMainActivityComponent.builder()
                .potComponent(DaggerPotComponent.builder()
                        .flowerComponent(DaggerFlowerComponent.create())
                        .build())
                .build().inject(this);
        String show = pot.show();

        Toast.makeText(MainActivity.this, show, Toast.LENGTH_SHORT).show();
    }

    /**
     * 依赖注入中第一个并且是最重要的就是@Inject注解。JSR-330标准中的一部分，标记那些应该被依赖注入框架提供的依赖。
     * 在Dagger 2中有3种不同的方式来提供依赖：
     * <p>
     * 构造器注入，@Inject标注在构造器上其实有两层意思。
     * 告诉Dagger2可以使用这个构造器构建对象。如Rose类
     * 注入构造器所需要的参数的依赖。 如Pot类，构造上的Rose会被注入。
     * 构造器注入的局限：如果有多个构造器，我们只能标注其中一个，无法标注多个。
     * <p>
     * 属性注入
     * 如MainActivity类，标注在属性上。被标注的属性不能使用private修饰，否则无法注入。
     * 属性注入也是Dagger2中使用最多的一个注入方式。
     * <p>
     * 方法注入
     * 标注在public方法上，Dagger2会在构造器执行之后立即调用这个方法。
     * 方法注入和属性注入基本上没有区别， 那么什么时候应该使用方法注入呢？
     * 比如该依赖需要this对象的时候，使用方法注入可以提供安全的this对象，因为方法注入是在构造器之后执行的。
     * 如下
     */
    @Inject
    public void setPot(Pot pot) {
        this.pot = pot;
    }
}
