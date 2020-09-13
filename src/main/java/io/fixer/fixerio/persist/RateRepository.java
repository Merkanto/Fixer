package io.fixer.fixerio.persist;

import io.fixer.fixerio.entity.CurrencyRate;
import org.springframework.data.repository.CrudRepository;

public interface RateRepository extends CrudRepository<CurrencyRate, Long> {

}
