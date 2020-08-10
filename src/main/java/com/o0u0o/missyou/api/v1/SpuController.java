package com.o0u0o.missyou.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.o0u0o.missyou.bo.PageCounter;
import com.o0u0o.missyou.core.http.NotFoundException;
import com.o0u0o.missyou.model.Spu;
import com.o0u0o.missyou.service.SpuService;
import com.o0u0o.missyou.util.CommonUtil;
import com.o0u0o.missyou.vo.PagingDozer;
import com.o0u0o.missyou.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SpuController
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/28 下午11:17
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    SpuService spuService;

    @GetMapping("/latest")
    public PagingDozer<Spu ,SpuSimplifyVO> getLatestSpuList(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                                @RequestParam(value = "count", defaultValue = "20") Integer count){
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        Page<Spu> page = this.spuService.getLatestPagingSpu(pageCounter.getPage(), pageCounter.getCount());
        return new PagingDozer<>(page, SpuSimplifyVO.class);
    }

    @GetMapping("/id/{id}/simplify")
    public SpuSimplifyVO getSimplifySpu(@PathVariable @Positive(message = "{id.positive}") Long id){
        Spu spu = this.spuService.getSpu(id);
        SpuSimplifyVO vo = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu, vo);
        return vo;
    }

    @GetMapping("/id/{id}/detail")
    public Spu detail(@PathVariable @Positive Long id){
        Spu spu = this.spuService.getSpu(id);
        if (spu == null){
            throw new NotFoundException(30003);
        }
        return spu;
    }

    /**
     * 根据分类ID查询
     * @param
     * @return: 
     * @author: ChuanGui.Yue
     * @date: 2020/8/2 下午3:32
     */
    @GetMapping("/by/category/{id}")
    public PagingDozer<Spu, SpuSimplifyVO> getByCategoryId(@PathVariable(name = "id") @Positive Long id,
                                                           @RequestParam(name = "is_root", defaultValue = "false") Boolean isRoot,
                                                           @RequestParam(name = "start", defaultValue = "0") Integer start,
                                                           @RequestParam(name = "count", defaultValue = "10") Integer count){
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        Page<Spu> page = spuService.getByCategory(id, isRoot, pageCounter.getPage(), pageCounter.getCount());
        return new PagingDozer<>(page, SpuSimplifyVO.class);
    }


}
