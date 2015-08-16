/*
 * Copyright (c) 2015 Spotify AB.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.heroic.common;

import java.util.Collection;

import lombok.RequiredArgsConstructor;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

@RequiredArgsConstructor
public class CollectingTypeListener<T> implements TypeListener {
    private final Collection<T> collected;

    @Override
    public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {
        encounter.register(new InjectionListener<I>() {
            @SuppressWarnings("unchecked")
            @Override
            public void afterInjection(I i) {
                collected.add((T) i);
            }
        });
    }
}