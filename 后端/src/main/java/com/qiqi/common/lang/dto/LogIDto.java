package com.qiqi.common.lang.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
@Data
public class LogIDto implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String username;


    @NotBlank(message = "密码不能为空")
    private String password;


}
