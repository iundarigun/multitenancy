package br.com.devcave.multitenancy.controller;

import br.com.devcave.multitenancy.domain.Product;
import br.com.devcave.multitenancy.repository.ProductRepository;
import io.swagger.annotations.ApiImplicitParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping
    @ApiImplicitParam(name = "tenantId", dataType = "string", paramType = "header")
    public HttpEntity<?> create(@RequestBody final Product product) {
        Product productSaved = productRepository.save(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(productSaved.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    @ApiImplicitParam(name = "tenantId", dataType = "string", paramType = "header")
    public HttpEntity<Product> findById(@PathVariable final Long id) {
        Product product = productRepository.findById(id).orElse(null);

        return ResponseEntity.ok(product);
    }

    @GetMapping
    @ApiImplicitParam(name = "tenantId", dataType = "string", paramType = "header")
    public HttpEntity<List<Product>> findAll() {
        List<Product> productList = productRepository.findAll();

        return ResponseEntity.ok(productList);
    }
}
