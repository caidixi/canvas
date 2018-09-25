package wnderful.myPoject.dataService;

import wnderful.myPoject.po.Mark;

import java.io.IOException;

public interface MarkDataService {
    void saveMark(Mark mark) throws IOException ;

    Mark getMark(String name) throws IOException;

    String findShapeName(Mark make) ;
}
