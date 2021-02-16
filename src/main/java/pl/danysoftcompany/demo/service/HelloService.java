package pl.danysoftcompany.demo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String helloService() {
        return "Hello World Service.";
    }
}
