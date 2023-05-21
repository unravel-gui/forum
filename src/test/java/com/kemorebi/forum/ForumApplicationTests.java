package com.kemorebi.forum;

import com.kemorebi.forum.mapper.ArticleMapper;
import com.kemorebi.forum.model.dto.ArticleSimDTO;
import com.kemorebi.forum.model.dto.QueryParamDTO;
import com.kemorebi.forum.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;

@SpringBootTest
public class ForumApplicationTests {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void main() {
        QueryParamDTO dto = new QueryParamDTO();
        dto.setQuery("测试");
        dto.setType("测试");
        dto.setAuthor("测试");
        List<ArticleSimDTO> list = articleMapper.getQueryArticleSimList(dto, true);
        for (ArticleSimDTO dto1 : list) {
            System.out.println(dto1);
        }
    }


}
