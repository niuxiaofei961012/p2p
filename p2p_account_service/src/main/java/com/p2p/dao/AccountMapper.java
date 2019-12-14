package com.p2p.dao;

import com.p2p.entity.Account;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    Account verifyPassword(@Param("id") Integer id, @Param("tradePassword")String tradePassword);

    @Update("update account set  useable_amount = useable_amount-#{bidMoney}, freezon_amount =  freezon_amount+ #{bidMoney}  where id= #{id} ")
    void updateMoney(@Param("id") Integer id,@Param("bidMoney") BigDecimal bidMoney);

    //修改之后查询账户可用金额
    @Select("select accoubt_avbalance from t_account where id =#{id}")
    @ResultType(com.p2p.entity.Account.class)
    Account selectById(Integer id);
}