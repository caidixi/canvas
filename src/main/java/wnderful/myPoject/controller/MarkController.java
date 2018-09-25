package wnderful.myPoject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wnderful.myPoject.dataServiceImpl.MarkDataServiceImpl;
import wnderful.myPoject.po.Mark;
import wnderful.myPoject.response.NormalResponse;

import java.io.IOException;

@RestController
@RequestMapping("/mark")
public class MarkController {
    private MarkDataServiceImpl markDataService;

    @Autowired
    public MarkController(MarkDataServiceImpl markDataService){
        this.markDataService = markDataService;
    }

    @RequestMapping(value = "/setMark",method = RequestMethod.POST)
    public NormalResponse setMark(@RequestBody Mark mark) throws IOException {
        markDataService.saveMark(mark);
        return new NormalResponse(0,markDataService.findShapeName(mark));
    }

    @GetMapping(value = "/getMark/{name}")
    public Mark getMark(@PathVariable("name") String name) throws IOException{
        return markDataService.getMark(name);
    }
}
