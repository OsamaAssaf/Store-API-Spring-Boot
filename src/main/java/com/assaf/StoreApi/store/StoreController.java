package com.assaf.StoreApi.store;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

//    @PostMapping(name = "/add")
//    public ResponseEntity<Store> addStore(){
//        return storeService.addStore();
//    }

}
