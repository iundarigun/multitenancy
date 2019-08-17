package br.com.devcave.multitenancy.repository;

import br.com.devcave.multitenancy.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
