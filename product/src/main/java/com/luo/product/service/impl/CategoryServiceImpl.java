package com.luo.product.service.impl;

import com.luo.product.dataobject.ProductCategory;
import com.luo.product.repo.ProductCategoryRepository;
import com.luo.product.service.CategoryService;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
