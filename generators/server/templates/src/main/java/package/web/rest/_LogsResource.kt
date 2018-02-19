<%#
 Copyright 2013-2018 the original author or authors from the JHipster project.

 This file is part of the JHipster project, see http://www.jhipster.tech/
 for more information.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-%>
package <%=packageName%>.web.rest

import <%=packageName%>.web.rest.vm.LoggerVM

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import com.codahale.metrics.annotation.Timed
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Controller for view and managing Log Level at runtime.
 */
@RestController
@RequestMapping("/management")
class LogsResource {

    @GetMapping("/logs")
    @Timed
    fun getList(): kotlin.collections.List<LoggerVM> {
        val context = LoggerFactory.getILoggerFactory() as LoggerContext
        return context.loggerList
            .map{ LoggerVM(it) }
    }

    @PutMapping("/logs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Timed
    fun changeLevel(@RequestBody jsonLogger: LoggerVM) {
        val context = LoggerFactory.getILoggerFactory() as LoggerContext
        context.getLogger(jsonLogger.name).level = Level.valueOf(jsonLogger.level)
    }
}
