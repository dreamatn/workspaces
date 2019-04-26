package com.hisun.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by MoHuaiyi 2019/4/17 10:59
 */
@Getter
@Setter
@AllArgsConstructor // 含参数的构造方法
@NoArgsConstructor // 不含参数的构造方法
public class MyResult {

	private int code;
	private String msg;
	private List<?> list;
	private Object obj;
}
