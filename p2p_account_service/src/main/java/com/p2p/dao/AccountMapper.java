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

    @Update("update t_account set remain_credit_limit =  remain_credit_limit - #{accessMoney},accoubt_avbalance = accoubt_avbalance + #{accessMoney},unreceive_money = unreceive_money + #{unreceiveMoney} where id = #{borrowUserId}")
    int updateAccountByBorrowUser(@Param("borrowUserId") Integer borrowUserId,@Param("accessMoney") BigDecimal accessMoney,@Param("unreceiveMoney") BigDecimal unreceiveMoney);

    @Update("update t_account set unreceive_principal = unreceive_principal + #{unreceivePrincipal},unreceive_interest = unreceive_interest + #{unreceiveInterest},accoubt_frobalance = accoubt_frobalance + #{unreceivePrincipal} where id = #{accountId}")
    int updateAccountByBidUser(@Param("accountId") Integer accountId, @Param("unreceiveInterest")BigDecimal unreceiveInterest, @Param("unreceivePrincipal")BigDecimal unreceivePrincipal);
}