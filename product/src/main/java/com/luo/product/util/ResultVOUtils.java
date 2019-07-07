package com.luo.product.util;

import com.luo.product.vo.ResultVO;

public class ResultVOUtils {

    public static ResultVO sucess(Object object) {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
