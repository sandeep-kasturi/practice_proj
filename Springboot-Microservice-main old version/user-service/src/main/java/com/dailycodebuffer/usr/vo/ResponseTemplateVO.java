package com.dailycodebuffer.usr.vo;

import com.dailycodebuffer.usr.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private User user;
    private com.dailycodebuffer.dep.entity.Department department;
}