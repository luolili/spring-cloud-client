package com.luo.product.service.impl;

import com.luo.product.dataobject.ProductInfo;
import com.luo.product.dto.CartDTO;
import com.luo.product.enums.ProductStatusEnum;
import com.luo.product.enums.ResultEnum;
import com.luo.product.exception.ProductException;
import com.luo.product.repo.ProductInfoRepository;
import com.luo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {

            Optional<ProductInfo> productInfoOptional =
                    productInfoRepository.findById(cartDTO.getProductId());

            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            
            //获取optional里面的对象
            ProductInfo productInfo = productInfoOptional.get();
            // 获取这个商品的库存

            Integer productStock = productInfo.getProductStock();
            Integer result = productStock  - cartDTO.getProductQuantity();
            //先处理异常：库存不够
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            //设置新的库存
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);



        }

    }
}
