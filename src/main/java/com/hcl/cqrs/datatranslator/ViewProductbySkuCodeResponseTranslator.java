package com.hcl.cqrs.datatranslator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.hcl.cqrs.entities.CreateproductReq;
import com.hcl.cqrs.exception.ProductException;
import com.hcl.cqrs.response.ViewproductRes;

/**
 * 
 * @author BrijendraK
 *
 */
public class ViewProductbySkuCodeResponseTranslator {
    static Logger log = LoggerFactory.getLogger(ViewProductbySkuCodeResponseTranslator.class);

    /**
     * This method is used as translator from backend to frontend.
     * 
     * @param pList
     * @param env
     * @return
     */
    public ViewproductRes viewProductbySkuCodeResponseTranslator(List<CreateproductReq> pList, Environment env)
            throws ProductException {
        log.info("Response translation from backend to frontend start");
        ViewproductRes viewproductRes = new ViewproductRes();
        List<CreateproductReq> productsList = new ArrayList<CreateproductReq>();
        for (CreateproductReq products : pList) {
            if (products.isIs_deleted() == false) {
                productsList.add(products);
            }
        }

        if (productsList.isEmpty()) {
            viewproductRes.setStatus("product not found");
            viewproductRes.setStatusCode(String.valueOf(HttpStatus.NO_CONTENT.value()));
        }
        viewproductRes.setProductList(productsList);
        log.info("Response translation from backend to frontend end");
        return viewproductRes;
    }
}