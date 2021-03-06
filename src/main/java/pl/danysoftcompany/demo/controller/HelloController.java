package pl.danysoftcompany.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.danysoftcompany.demo.service.HelloService;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;
    @GetMapping("/")
    public String hello(){
        return helloService.helloService();
    }
    @GetMapping("/helloWorld")
    public String helloWorld(){
        return helloService.helloService();
    }
}
