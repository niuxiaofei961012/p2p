package com.p2p.dao;

import com.p2p.vo.LoanMarkVO;
import com.p2p.entity.LoanMark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface LoanMarkMapper {
    int deleteByPrimaryKey(Integer borrowSignId);

    int insert(LoanMark record);

    int insertSelective(LoanMark record);

    LoanMarkVO selectByPrimaryKey(Integer borrowSignId);

    int updateByPrimaryKeySelective(LoanMark record);

    int updateByPrimaryKey(LoanMark record);

    List<LoanMarkVO> getLoanMarkList(@Param("statusType") Integer statusType);

    @Update("update t_loan_mark set status = #{status,jdbcType=INTEGER} where borrow_sign_id = #{borrowSignId,jdbcType=INTEGER} ")
    int updateStatus(@Param("borrowSignId") Integer borrowSignId, @Param("status")Integer status);

    List<LoanMarkVO> getLoanMarkListByStatus(@Param("userId") Integer userId,@Param("status") Integer status);

    @Update("update update  t_loan_mark set  access_money =  access_money + #{bidMoney}  where borrow_sign_id=#{id}")
    void updateAccessMoney(@Param("id") Integer id,@Param("bidMoney") BigDecimal bidMoney);

    @Update("update update  t_loan_mark set  status_type = #{statusType}  where borrow_sign_id=#{id}")
    void updateStatusType(@Param("id") Integer id,@Param("statusType") Integer statusType);
}