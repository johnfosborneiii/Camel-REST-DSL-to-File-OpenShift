/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mycompany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User entity
 *
 */
@ApiModel(description = "Represents an user of the system")
public class User {

    @ApiModelProperty(value = "The ID of the user", required = true)
    private Integer id;

    @ApiModelProperty(value = "The name of the user", required = true)
    private String name;
    
    @ApiModelProperty(value = "The email of the user", required = true)
    private String email;
    
    @ApiModelProperty(value = "The base64 encoded image of the user", required = true)
    private String imagedata;

    public User() {
    }

    public User(Integer id, String name, String email, String base64imagedata) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imagedata = imagedata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getImagedata() {
        return imagedata;
    }

    public void setImagedata(String imagedata) {
        this.imagedata = imagedata;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
