package com.xiaoshu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshu.admin.entity.Menu;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiaoshu.admin.entity.vo.ShowMenuVo;
import com.xiaoshu.admin.model.QlrTs;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.model.YwrTs;

public interface ShMainSwMapper extends BaseMapper<ShMain> {

	List<ShMain> selectSwAllList(ShMain shMain);

	int findCountSwAllList(ShMain shMain);

	int findbdcswCountAllList(ShMain shMain);

	int findbdcfgCountAllList(ShMain shMain);

	@Select("select * from sh_sl t where t.ywh = #{ywh} ")
	List<ShMain> selectZt(ShMain ywh);

	boolean insertShSl(ShMain shMain);

	boolean insertshQlr(QlrTs qlrTs);

	boolean insertShYwr(YwrTs ywrTs);

	@Select("select count(*) from sh_sl where ywh=#{ywh}")
	int selectYwh(ShMain shMain);

	@Update("update sh_sl set zt='2' where ywh=#{ywh}")
	boolean updatezt(ShMain shMain);

	List<ShMain> selectSwList(ShMain shMain);

	boolean swUpdatefje(ShMain shMain);

	List<ShMain> shbdcAllList(ShMain shMain);

	List<ShMain> shbdcswList(ShMain shMain);

	List<ShMain> shbdcfgList(ShMain shMain);

	List<ShMain> selectQsxxTS(ShMain shMain);

	List<QlrTs> selectQlr(QlrTs qlrTs);

	List<YwrTs> selectYwr(YwrTs ywrTs);
	
	//首页数据显示查询
	List<ShMain> selectMain(ShMain shMain);
	//首页显示数据总数
	int findMainCount(ShMain shMain);

	boolean swUpdateMfje(ShMain shMain);


}