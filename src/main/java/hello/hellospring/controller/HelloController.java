package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello2")
    public String hello(Model model) {
        model.addAttribute("data", "???!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name2") String name, Model model){
        model.addAttribute("name2", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name
    ){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;


//        return id;
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }

//    @RestController
//    @RequiredArgsConstructor
    // lambok 을 설치해야 사용할수 있는 기능
//    public class SizeLimitController{
//        @GetMapping("/hello2")
//        public ResponseEntity<List<Long>> boardList(@RequestParam List<Long> ids){
//            return ResponseEntity.ok(ids);
//        }
//    }

}
