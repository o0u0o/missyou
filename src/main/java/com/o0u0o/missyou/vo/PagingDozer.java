package com.o0u0o.missyou.vo;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PagingDozer
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/30 下午11:11
 * @Descripton: PagingDozer
 * @Version: v0.0.1
 **/

public class PagingDozer<T, K> extends Paging {

    public PagingDozer(Page<T> page, Class<K> classK){
        this.initPageParameters(page);

        List<T> tList = page.getContent();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<K> voList = new ArrayList<>();

        tList.forEach(t ->{
            K vo = mapper.map(t, classK);
            voList.add(vo);
        });

        this.setItems(voList);
    }


}
