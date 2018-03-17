package uo.asw.utils;

import java.util.UUID;

public class UuidGenerator2 {
	
	public static String getUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
