package com.visionet.repair.repository.test;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.visionet.repair.entity.test.Test;


public interface TestDao extends PagingAndSortingRepository<Test, String>, JpaSpecificationExecutor<Test>{

}
