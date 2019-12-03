package com.xiaoshu.admin.oracle.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshu.admin.model.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface SwMapper extends BaseMapper<Bdcquery>{
	
	//根据受理编号查询数据
	//@Select("select * from bdc_query where ywh = #{ywh} and fwyt not like '%地下室%'")
	List<Bdcquery> selectSwList(Bdcquery Bdcquery);
	
	//根据受理编号查询数据
	@Select("select * from bdc_query where ywh = #{ywh} and fwyt like '%地下室%'")
	List<Bdcquery> selectSwListDxs(Bdcquery Bdcquery);
	
	//根据受理编号查询上传文件名称
	@Select("select distinct path from bdc_file ywh = #{ywh} and path like #{path}")
	String selectFileNameList(Bdcfile Bdcfile);
	
	//查询抵押情况
	@Select("select * from bdc_dy where ywh = #{ywh}")
	List<Bdcdy> selectDyList(Bdcdy BdcDy);
	
	//根据ywh更新缴费金额
	@Update("update bdc_query set jfje = #{jfje},swblr = #{swblr},swblrq = #{swblrq} where ywh = #{ywh}")
	boolean upDateJfje(Bdcquery Bdcquery);
	
	//查询所有数据，分页显示
	List<Bdcquery> selectSwAllList(Bdcquery Bdcquery);
	
	//分页显示数据总数
	Integer findCountSwAllList(Bdcquery Bdcquery);
	
	//根据当前登录人查询所有数据，分页显示
	List<Bdcquery> selectSwAllListByUser(Bdcquery Bdcquery);
	
	//根据当前登录人分页显示数据总数
	Integer findCountSwAllListByUser(Bdcquery Bdcquery);
	
	//根据受理编号查询买方家庭房产信息
	@Select("select * from BDC_JTQUERY where bdcywh = #{bdcywh} and qlrlx = '买方'")
	List<Fcxx> findAllJtxxWinthSlbh(Fcxx Fcxx);
	
	//查看转移登记权利人信息
	@Select("select * from bdc_zy where ywh = #{ywh}")
	List<Bdczy> findZydjList(Bdczy Bdczy);
	
	//根据受理编号查询卖方家庭房产信息
	@Select("select * from BDC_JTQUERY where bdcywh = #{bdcywh} and qlrlx = '卖方'")
	List<Fcxx> findAllJtxxWinthSlbh1(Fcxx Fcxx);
}
