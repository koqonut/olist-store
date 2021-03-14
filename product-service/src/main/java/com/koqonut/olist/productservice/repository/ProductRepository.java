package com.koqonut.olist.productservice.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.koqonut.olist.productservice.model.Product;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ProductRepository {
    
    @Value("${file.path}")
    private String filePath;
    private List<Product> productList;
    private AtomicBoolean isLoaded =new AtomicBoolean(false);


    public String loadData(){       

        if(isLoaded.get()){
            return "Products already loaded";
        }
        isLoaded.set(true);
        log.info("Reading file from path  {}",filePath);
        long t1= System.currentTimeMillis();
        try ( Reader reader = new BufferedReader(
                                new InputStreamReader(
                                 new ClassPathResource(filePath).getInputStream()));){ 
           
            CsvToBean<Product> csvToBean = new CsvToBeanBuilder(reader)
            .withType(Product.class)
            .withIgnoreLeadingWhiteSpace(true)
            .build();

            productList = csvToBean.parse();
            log.info("Populated {} records in {} ms",productList.size(),(System.currentTimeMillis()-t1));
            
        }catch(IOException ex){
            log.error("unable to parse csv file " +ex);
            isLoaded.set(false);
        }
        return  "Loaded "+productList.size()+" records in " + ((System.currentTimeMillis()-t1 )+ " ms" );
    }   

    public String getRecords(int numOfRecords){
    

        if(numOfRecords>productList.size()){
            numOfRecords=productList.size();
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<numOfRecords;i++){
            sb.append(productList.get(i).toString());
        }

        return sb.toString();
    }
}
