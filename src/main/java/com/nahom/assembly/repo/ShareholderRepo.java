package com.nahom.assembly.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.nahom.assembly.model.Sherholderdetail;
import java.util.List;
import java.math.BigDecimal;



public interface ShareholderRepo extends JpaRepository<Sherholderdetail, Long>{

   List<Sherholderdetail> findByPhoneStartsWith(String phone);
   List<Sherholderdetail> findByNameengStartsWith(String nameeng);


    }
    