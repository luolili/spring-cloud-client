package com.luo.product.controller;

import com.luo.product.dataobject.ProductCategory;
import com.luo.product.dataobject.ProductInfo;
import com.luo.product.service.CategoryService;
import com.luo.product.service.ProductService;
import com.luo.product.util.ResultVOUtils;
import com.luo.product.vo.ProductInfoVO;
import com.luo.product.vo.ProductVO;
import com.luo.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1.获取所以上架的商品
     * 2. 获取type的列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @RequestMapping("/list")
    public ResultVO<ProductVO> list() {

        //-1 查询所有的商家商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //-2 获取商品的类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //-3 从数据库中出现上面商品类目的信息
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //-4 构造数据：api接口是一个商品的list
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            //-5 准备productInfoVO list
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }

            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);

    }
}
