package com.ruoyi.common.core.domain.model;


import lombok.Data;

@Data
public class LoginBodyPhone extends LoginBody{
    private Long phonenumber;
    private String code;
}
