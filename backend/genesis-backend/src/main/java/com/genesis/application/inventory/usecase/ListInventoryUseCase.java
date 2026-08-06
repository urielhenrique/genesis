package com.genesis.application.inventory.usecase;

import com.genesis.domain.inventory.Inventory;
import com.genesis.domain.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListInventoryUseCase {

    private final InventoryRepository repository;

    public ListInventoryUseCase(
        InventoryRepository repository) {

        this.repository = repository;
    }

    public List<Inventory> execute() {
        return repository.findAll();
    }
}
