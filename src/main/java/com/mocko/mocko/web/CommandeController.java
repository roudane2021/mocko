package com.mocko.mocko.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/demandes")
public class CommandeController {

    private final static Map<String, CommandeDto> map;

    static {
        map = new HashMap<>();
        CommandeDto com1 = CommandeDto.builder().code("123").name("com3").build();
        CommandeDto com2 = CommandeDto.builder().code("124").name("com4").build();
        CommandeDto com4 = CommandeDto.builder().code("125").name("com5").build();
        CommandeDto com5 = CommandeDto.builder().code("126").name("com6").build();
        map.put("123", com1);
        map.put("124", com2);
        map.put("125",com4);
        map.put("126", com5);
    }


    @GetMapping
    public ResponseEntity<Collection<CommandeDto>> methodeGetAll() {

        Collection<CommandeDto> set = map.values();

        return  ResponseEntity.ok().body(set);
    }

    @GetMapping(value = "withPath/{code}")
    public ResponseEntity<CommandeDto> methodeGetOnePath(@PathVariable String code) {
   if( map.containsKey(code) ) {
       return ResponseEntity.ok(map.get(code));
   }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(value = "withParamGet")
    public ResponseEntity<CommandeDto> methodeGetOneParam(@RequestParam String code) {
        if( map.containsKey(code) ) {
            return ResponseEntity.ok(map.get(code));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(value = "withParamHeader")
    public ResponseEntity<CommandeDto> methodeGetOneheader(@RequestHeader("code") String code) {
        if( map.containsKey(code) ) {
            return ResponseEntity.ok(map.get(code));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping()
    public ResponseEntity<CommandeDto> methodePost(@RequestBody CommandeDto commandeDto) {
        commandeDto.setCode(commandeDto.getCode()+"valide");
        map.putIfAbsent(commandeDto.getCode(), commandeDto);
        return ResponseEntity.status(HttpStatus.OK).body(commandeDto);

    }
}
