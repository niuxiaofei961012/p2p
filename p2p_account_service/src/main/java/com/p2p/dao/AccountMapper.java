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

    @Update("update t_account set  accoubt_avbalance = accoubt_avbalance - #{bidMoney}, accoubt_frobalance =  accoubt_frobalance+ #{bidMoney}  where id= #{id} ")
    void updateMoney(@Param("id") Integer id,@Param("bidMoney") BigDecimal bidMoney);

    @Update("update t_account set  accoubt_frobalance =  accoubt_frobalance+ #{payMoney}  where id= #{createUserId} ")
    int updateAccoubtFrobalance(@Param("createUserId") Integer createUserId, @Param("payMoney") BigDecimal payMoney);

    @Update("update t_account set accoubt_frobalance =  accoubt_frobalance - #{recharge},accoubt_avbalance = accoubt_avbalance + #{recharge} where id = #{id}")
    int subtractFrobalanceAndAddAvbalance(@Param("id") Integer id,@Param("recharge") BigDecimal recharge);
}