package ru.baster.spring.sample.shop.study.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.baster.spring.sample.shop.study.market.converters.ProductConverter;
import ru.baster.spring.sample.shop.study.market.service.ProductService;
import ru.baster.spring.sample.shop.study.market.soap.product.GetAllProductsRequest;
import ru.baster.spring.sample.shop.study.market.soap.product.GetAllProductsResponse;


@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.winter-market.com/spring/products";
    private final ProductService productService;
    private final ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAll().stream().map(productConverter::entityToSoapDto).forEach(response.getProducts()::add);
        return response;
    }
}
