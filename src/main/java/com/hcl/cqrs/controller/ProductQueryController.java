package com.hcl.cqrs.controller;

import static com.hcl.cqrs.constants.ProductConstants.ACCESS_TOKEN;
import static com.hcl.cqrs.constants.ProductConstants.SKU_CODE;
import static com.hcl.cqrs.constants.ProductConstants.VIEW_PRODUCT_BYSKUCODE_URI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.cqrs.constants.ProductConstants;
import com.hcl.cqrs.datatranslator.ViewProductbySkuCodeResponseTranslator;
import com.hcl.cqrs.datatranslator.ViewProductsResponseTranslator;
import com.hcl.cqrs.entities.CreateproductReq;
import com.hcl.cqrs.exception.ProductException;
import com.hcl.cqrs.response.ViewproductRes;
import com.hcl.cqrs.service.IProductService;


@RefreshScope
@RestController
public class ProductQueryController {
	
	public static final Logger logger=LoggerFactory.getLogger(ProductQueryController.class);
	
	@Autowired
	IProductService productService;

	@Autowired
	Environment env;
	
	/**
     * This method is used for view all active products.
     * 
     * @param accessToken
     * @return ResponseEntity
     * @throws ProductException
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewproductRes> viewProducts(
            @RequestHeader(value = ProductConstants.ACCESS_TOKEN, required = true) String accessToken) throws ProductException {
        ViewProductsResponseTranslator vproductst = new ViewProductsResponseTranslator();
        ViewproductRes viewproductRes = null;

        List<CreateproductReq> pList = productService.viewProducts(env);
        viewproductRes = vproductst.viewProductsResponseTranslator(pList, env);
        logger.info("viewProducts call end");
        return ResponseEntity.ok().body(viewproductRes);

    }
    
    /**
     * This method is used for view single entry of active product based on skuCode.
     * 
     * @param accessToken
     * @param skuCode
     * @return ResponseEntity
     * @throws ProductException
     */
    @RequestMapping(method = RequestMethod.GET, value = VIEW_PRODUCT_BYSKUCODE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewproductRes> viewProductBySkuCode(
            @RequestHeader(value = ACCESS_TOKEN, required = true) String accessToken,
            @PathVariable(SKU_CODE) String skuCode) throws ProductException {
    	logger.info("viewProductBySkuCode call start");
        ViewProductbySkuCodeResponseTranslator vpt = new ViewProductbySkuCodeResponseTranslator();
        ViewproductRes viewproductRes = null;
        try {
            if (!StringUtils.isEmpty(skuCode)) {
                List<CreateproductReq> pList = productService.viewproductbyskuCode(skuCode, env);
                viewproductRes = vpt.viewProductbySkuCodeResponseTranslator(pList, env);
            }
        } catch (Exception ex) {
            throw new ProductException(ex.getMessage());
        }
        logger.info("viewProductBySkuCode call end");
        return ResponseEntity.ok().body(viewproductRes);

    }
    
    /**
     * @param productService
     */
    public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	/**
	 * @param env
	 */
	public void setEnv(Environment env) {
		this.env = env;
	}

}
	