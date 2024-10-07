package com.oracle.dev.jdbc;

import java.util.Optional;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface MartialArtRepository extends CrudRepository<MartialArt, Long> {
  Optional<MartialArt> findByNameIgnoreCase(String name);
  void deleteById(Long id);  
}
