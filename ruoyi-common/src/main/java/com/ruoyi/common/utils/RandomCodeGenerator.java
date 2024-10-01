package com.ruoyi.common.utils;

import java.util.Random;

public class RandomCodeGenerator {

    /**
     * 生成6位数字随机验证码
     *
     * @return 随机验证码
     */
    public static String generateVerificationCode() {
        Random random = new Random();
        int verificationCode = random.nextInt(900000) + 100000; // 生成100000到999999之间的随机数
        return String.valueOf(verificationCode);
    }

    public static void main(String[] args) {
        // 测试生成随机验证码
        String code = generateVerificationCode();
        System.out.println("生成的验证码: " + code);
    }
}