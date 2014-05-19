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

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.restfiddle.dao.ItemRepository;
import com.restfiddle.dto.ItemDTO;
import com.restfiddle.entity.Item;

@RestController
@EnableAutoConfiguration
@ComponentScan
@Transactional
public class ItemController {
    private ItemRepository itemRepository;

    public Item create(ItemDTO created) {
	return null;
    }

    public Item delete(Long id) {

	return null;
    }

    public List<Item> findAll() {
	return null;
    }

    public Item findById(Long id) {
	return null;
    }

    public Item update(ItemDTO updated) {
	return null;
    }
}
