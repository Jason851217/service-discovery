package com.learningcenter.product.dao;

import com.learningcenter.product.bean.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 描述:
 *
 * @author Jason
 * @email 285290078@qq.com
 * @create 2018-06-13 19:28
 **/
public interface ProductDao {
    @Insert("insert into product(pname,type,price) values(#{pname},#{type},#{price})")
    int add(Product product);

    @Update("update product set pname=#{pname},type=#{type},price=#{price} where id=#{id}")
    int update(Product product);

    @Delete("delete product where id=#{0}")
//  @Delete("delete product where id=#{arg1}")
    int delete(Integer id);

    @Select("select * from product where id=#{0}")
    Product findById(Integer id);

    @Select("select * from product")
    List<Product> findAll();
}
