package com.p2p.dao;

import com.p2p.VO.LoanMarkVO;
import com.p2p.entity.LoanMark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

}