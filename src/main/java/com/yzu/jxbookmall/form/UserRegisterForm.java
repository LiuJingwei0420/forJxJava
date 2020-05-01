package com.yzu.jxbookmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Will
 *
 * @Create: at 2020-04-30 20:41
 */

@Data
public class UserRegisterForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
