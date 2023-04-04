package com.example.dto;

import org.jboss.resteasy.reactive.*;

/**
 * @author yanxin
 * @Description:
 */
public class UserParameters {

    @RestQuery
    public String name;
}
