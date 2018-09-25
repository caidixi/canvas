package wnderful.myPoject.dataServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wnderful.myPoject.dataService.MarkDataService;
import wnderful.myPoject.po.Mark;
import com.alibaba.fastjson.*;
import wnderful.myPoject.util.FileHelper;

import java.io.IOException;

@Service
public class MarkDataServiceImpl implements MarkDataService {
    private FileHelper fileHelper;

    @Autowired
    public MarkDataServiceImpl(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    @Override
    public void saveMark(Mark mark) throws IOException {
        String name = mark.getName();
        String markContent = JSON.toJSONString(mark);
        fileHelper.save(name, markContent);
    }

    @Override
    public Mark getMark(String name)  throws IOException {
        String content = fileHelper.load(name);
        return JSON.parseObject(content, Mark.class);
    }

    @Override
    public String findShapeName(Mark mark) {
        int endMark = mark.getEndMark();
        int times = 0;
        for (int i = 0; i < mark.getX().length; i++) {
            if (mark.getX()[i] == endMark) {
                times++;
            }
        }
        switch (times) {
            case 1:
                return "circle";
            case 3:
                return "trangle";
            case 4:
                return "rectangle";
            default:
                return "unknown";
        }
    }
}
