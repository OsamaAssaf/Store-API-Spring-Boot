package com.assaf.StoreApi.store;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;


    public ResponseEntity<Store> addStore(){

        return ResponseEntity.ok();
    }
}
