/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.weaver.normalizer;

import org.apache.commons.weaver.model.Scanner;
import org.apache.commons.weaver.model.WeaveEnvironment;
import org.apache.commons.weaver.spi.Weaver;

/**
 * The purpose of the normalizer module is to merge identical anonymous class definitions into a single type, thereby
 * "normalizing" them and reducing their collective footprint on your archive and more importantly on your JVM.
 */
public class NormalizerWeaver implements Weaver {

    @Override
    public boolean process(WeaveEnvironment environment, Scanner scanner) {
        return new Normalizer(environment).normalize(scanner);
    }

}