package com.wanxi.service;


import com.wanxi.entity.ProductModel;
import com.wanxi.commonresult.CommonResult;

public interface ProductService extends BaseService<ProductModel>{


    CommonResult addText(ProductModel productModel);

    CommonResult findServiceType(ProductModel product);
}
