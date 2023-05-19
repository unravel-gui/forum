package com.kemorebi.forum.controller;

import com.kemorebi.forum.common.BaseController;
import com.kemorebi.forum.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexController extends BaseController {

    @GetMapping("/index")
    public R index(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "10") int pageSize) {
        return R.success(getAccount(), "success");
    }
}
