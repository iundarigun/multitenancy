package br.com.devcave.multitenancy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class MultitenancyApplication

fun main(args: Array<String>) {
	runApplication<MultitenancyApplication>(*args)
}
