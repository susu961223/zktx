package com.xiaoshu.common.config;

import java.lang.annotation.*;
import com.xiaoshu.admin.Enum.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

	DBTypeEnum value() default DBTypeEnum.db1;
}