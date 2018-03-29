package com.example.majes.dagger2test.entity;

/**
 * @author:majes
 * @date:2018/3/29
 * @email:marlborn@foxmail.com
 */
public class Pot {
    private Rose rose;
    private Flower flower;

//    public String show() {
//        return rose.whisper();
//    }

    public String show() {
        return flower.whisper();
    }

//    @Inject
//    public Pot(Rose rose) {
//        this.rose = rose;
//    }

    /**
     * 遵循依赖倒置规则。这时候Dagger就报错了，
     * 因为Pot对象需要Flower，而Flower是抽象的，
     * 无法使用@Inject提供实例
     */

//    @Inject
    public Pot(Flower flower) {
        this.flower = flower;
    }

//    @Inject
//    public Pot(@FlowerModule.RoseFlower Flower flower) {
//        this.flower = flower;
//    }

}
