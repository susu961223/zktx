package com.xiaoshu.admin.oracle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshu.admin.model.Bdcdhgl;
import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdcsj;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.Fcxx;

public interface BdcsjMapper extends BaseMapper<Bdcsj> {

	// 查询当天数据记录
	@Select("select max(to_number(ywh)) from bdc_query where ywh like #{slbh}")
	public String selectCountCS(String slbh);

	//插入受理编号
	@Insert("insert into bdc_query(ywh)values(TO_CHAR(SYSDATE,'YYYYMMdd')||LPAD(TO_CHAR(DATE_SEQ.NEXTVAL),5,'0')/*ywh为系统时间加上1-99999的5位数字，不够四位用0来补*/)")
	boolean insertSlbh();
	// 查询要办理的不动产单元号
	public String selectBdcdyh(Bdcsj bdcsj);

	// 模糊查询权证号
	List<Bdcsj> selectQzcx(Bdcsj bdcsj);

	// 模糊查询证号总数
	Integer findQzcx(Bdcsj bdcsj);

	// 查询是否抵押
	@Select("select count(*) from dyaq a ,fdcq2 f  where a.bdcdyh=f.bdcdyh and  a.qszt ='1'  and f.qszt='1' and f.bdcqzh= #{bdcqzh}")
	public int getByDyzt(String bdcqzh);

	// 查询是否查封
	@Select("select count(*) from cfdj a ,fdcq2 f where a.bdcdyh=f.bdcdyh and a.qszt='1' and f.bdcqzh= #{bdcqzh}")
	public int getByCfzt(String bdcqzh);

	// 查询是否异议
	@Select("select distinct a.bdcdyh from yydj a ,fdcq2 f where a.bdcdyh=f.bdcdyh and  f.qszt ='1' and f.bdcqzh= #{bdcqzh}")
	public String getByYyzt(String bdcqzh);

	// 查询是否预告
	@Select("select distinct a.bdcdyh from ygdj a ,fdcq2 f where a.bdcdyh=f.bdcdyh and  f.qszt ='1' and f.bdcqzh= #{bdcqzh}")
	public String getByYgzt(String bdcqzh);

	// 查询查封信息
	List<Bdcsj> selectCf(Bdcsj bdcsj);

	// 查封抵押信息
	List<Bdcsj> selectDy(Bdcsj bdcsj);

	List<Bdcsj> selectBdcsjList(Bdcsj bdcsj);

	boolean insertBdcquery(Bdcquery bdcquery);

	boolean insertBdcJtQuery(Fcxx fcxx);

	boolean insertBdcdy(Bdcdy bdcdy);

	boolean insertBdczy(Bdczy bdczy);

	boolean insertBdcfile(Bdcfile bdcfile);

	boolean insertBdcdhgl(Bdcdhgl bdcdhgl);
	
	// 查询房产数据
	List<Fcxx> findByFcxx(Fcxx fcxx);

	// 根据当前登录人查询所有数据，分页显示
	List<Bdcquery> selectBdcAllListByUser(Bdcquery bdcquery);

	// 根据当前登录人分页显示数据总数
	Integer findCountBdcAllListByUser(Bdcquery bdcquery);

	// 根据受理编号查询数据
	//@Select("select * from bdc_query where ywh = #{ywh} ")
	List<Bdcquery> selectBdcquery(Bdcquery Bdcquery);

	// 根据受理编号查询转移数据
	List<Bdczy> selectBdcZy(Bdczy bdczy);

	// 根据受理编号查询抵押数据
	List<Bdcdy> selectBdcDy(Bdcdy bdcdy);
	
	//更新业务状态，1是显示，2是历史
	@Update("update bdc_query set ywzt=#{ywzt},delyy=#{delyy} where ywh = #{ywh}")
	boolean updateYwzt(Bdcquery Bdcquery);
	
}
