package com.community2.cache;

import com.community2.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO  program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript","php","css","html","html5","java","python","golang",".net","node","c","c++","swift","shell","c#","lua","ruby","vue","typescript","objective-c","sass","bash","less","scala","asp.net","actionscript","coffeescript","erlang","perl","rust"));
        tagDTOS.add(program);
        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","express","laravel","django","flask","yii","ruby-on-rails","koa","struts","tornado"));
        tagDTOS.add(framework);
        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","ubuntu","centos","tomcat","unix","hadoop","windows-server"));
        tagDTOS.add(server);
        TagDTO db = new TagDTO();
        db.setCategoryName("服务器");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","json","elasticsearch","nosql","memcached","postgresql","sqlite","mariadb"));
        tagDTOS.add(db);
        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","github","macos","visual-studio-code","windows","vim","sublime-text","intellij-idea","eclipse","phpstorm","webstorm","svn","visual-studio","pycharm","emacs"));
        tagDTOS.add(tool);

        return tagDTOS;

    }
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;

    }
}

