/*
 * Copyright 2014 Ranjan Kumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.restfiddle.controller.rest;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restfiddle.dao.ItemRepository;
import com.restfiddle.dao.util.ItemConverter;
import com.restfiddle.dto.RfRequestDTO;
import com.restfiddle.entity.Item;
import com.restfiddle.exceptions.ApiException;
import com.restfiddle.handler.RequestHandler;
import com.restfiddle.handler.http.GenericHandler;

@RestController
@EnableAutoConfiguration
@ComponentScan
@Transactional
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    RequestHandler requestHandler;

    @Resource
    private ItemRepository itemRepository;

    @RequestMapping(value = "/api/processor", method = RequestMethod.POST, headers = "Accept=application/json")
    String processor(@RequestBody RfRequestDTO rfRequestDTO) {
	try {
	    GenericHandler handler = requestHandler.getHandler(rfRequestDTO.getMethodType());
	    String result = handler.process(rfRequestDTO);

	    Item item = ItemConverter.convertToEntity(rfRequestDTO, result);

	    // TODO : Support all the databases.
	    // TODO : Use Item controller here.
	    try {
		itemRepository.save(item);
	    } catch (InvalidDataAccessResourceUsageException e) {
		throw new ApiException("Please use sql as datasource, some of features are not supported by hsql", e);
	    }
	    return result;

	} catch (IOException e) {
	    logger.error("IO Exception", e);
	    throw new ApiException(e);
	}
    }

}
