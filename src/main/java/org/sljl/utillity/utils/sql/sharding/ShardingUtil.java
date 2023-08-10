package org.sljl.utillity.utils.sql.sharding;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.sljl.utillity.enums.ShardingPolicyEnum;

/**
 * 数据库分表工具类
 * 
 * @author L.Y.F
 * 
 */
public class ShardingUtil {

	// Hash方式分表的基数
	private static final int SHARDING_VALUE16 = 16;
	// Hash方式分表的基数
	private static final int SHARDING_VALUE32 = 32;
	// Hash方式分表的基数
	private static final int SHARDING_VALUE64 = 64;
	// Hash方式分表的基数
	private static final int SHARDING_VALUE128 = 128;
	// Hash方式分表的基数
	private static final int SHARDING_VALUE256 = 256;
	// Hash方式分表的基数
	private static final int SHARDING_VALUE512 = 512;
	// Hash方式分表的基数
	private static final int SHARDING_VALUE1024 = 1024;
//	// 滚动增长方式分表的基数
//	private static final long MAX_CNT_VALUE = 4000000l;
	// 系统默认分隔符
	private static final String default_seperator = "_";

	private static int getShardingValue16(String uniqueId) {
		return (uniqueId.hashCode() & 0x7FFFFFFF) % SHARDING_VALUE16;
	}

	private static int getShardingValue32(String uniqueId) {
		return (uniqueId.hashCode() & 0x7FFFFFFF) % SHARDING_VALUE32;
	}

	private static int getShardingValue64(String uniqueId) {
		return (uniqueId.hashCode() & 0x7FFFFFFF) % SHARDING_VALUE64;
	}

	private static int getShardingValue128(String uniqueId) {
		return (uniqueId.hashCode() & 0x7FFFFFFF) % SHARDING_VALUE128;
	}
	
	private static int getShardingValue256(String uniqueId) {
		return (uniqueId.hashCode() & 0x7FFFFFFF) % SHARDING_VALUE256;
	}

	private static int getShardingValue512(String uniqueId) {
		return (uniqueId.hashCode() & 0x7FFFFFFF) % SHARDING_VALUE512;
	}

	private static int getShardingValue1024(String uniqueId) {
		return (uniqueId.hashCode() & 0x7FFFFFFF) % SHARDING_VALUE1024;
	}

	private static String joinSubKeyBy(Object... objects) {
		if (null != objects) {
			return Joiner.on(default_seperator).join(
					ImmutableList.builder().add(objects).build());
		} else {
			return "";
		}
	}
	
	/**
	 * 数据库分表
	 * @param tableName
	 * @param shardingPolicy
	 * @param uniqueId
	 * @return
	 */
	public static String getShardingTable(String tableName, ShardingPolicyEnum shardingPolicy, long uniqueId) {
		String shardingTable = "";
		if (StringUtils.isNotBlank(tableName)) {
			if (ShardingPolicyEnum.SHARDING_FOR_HASH16 == shardingPolicy) {
				// FIXME 临时写法这种散列方式分表的算法要优化，保证每次扩展16张表，可以根据ID自动定位到新扩展的表中
				return joinSubKeyBy(tableName, getShardingValue16(uniqueId + ""));
			} else if (ShardingPolicyEnum.SHARDING_FOR_HASH32 == shardingPolicy) {
				return joinSubKeyBy(tableName, getShardingValue32(uniqueId + ""));
			} else if (ShardingPolicyEnum.SHARDING_FOR_HASH64 == shardingPolicy) {
				return joinSubKeyBy(tableName, getShardingValue64(uniqueId + ""));
			} else if (ShardingPolicyEnum.SHARDING_FOR_HASH128 == shardingPolicy) {
				return joinSubKeyBy(tableName, getShardingValue128(uniqueId + ""));
			} else if (ShardingPolicyEnum.SHARDING_FOR_HASH256 == shardingPolicy) {
				return joinSubKeyBy(tableName, getShardingValue256(uniqueId + ""));
			} else if (ShardingPolicyEnum.SHARDING_FOR_HASH512 == shardingPolicy) {
				return joinSubKeyBy(tableName, getShardingValue512(uniqueId + ""));
			} else if (ShardingPolicyEnum.SHARDING_FOR_HASH1024 == shardingPolicy) {
				return joinSubKeyBy(tableName, getShardingValue1024(uniqueId + ""));
			}
//			else if (ShardingPolicyEnum.SHARDING_FOR_INCREASE == shardingPolicy) {
//				// FIXME 临时写法这种滚动增长方式要根据具体情况控制每张表的实际数据量大概在MAX_CNT_VALUE个左右，超过后可以自动定位到新表
//				return joinSubKeyBy(tableName, 1);
//			}
		}
		return shardingTable;
	}

	/**
	 * 生成用户关注表分表结构
	 *
	 * @param tableIndex
	 * @return void
	 */
	private static String generateAttention(int tableIndex) {
		String sql = "CREATE TABLE `user_attention_"+tableIndex+"` (\n" + "" +
				"  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" + "" +
				"  `user_id` bigint(20) NOT NULL COMMENT '当前用户ID',\n" + "" +
				"  `target_user_id` bigint(20) NOT NULL COMMENT '目标用户ID',\n" + "" +
				"  `bilateral` smallint(1) NOT NULL DEFAULT '0' COMMENT '当前用户是否同时关注目标用户，如果是则说明是双向关系   0：不是、1：是',\n" + "" +
				"  `create_time` datetime NOT NULL COMMENT '关系建立时间',\n" + "" +
				"  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" + "" +
				"  PRIMARY KEY (`id`) USING BTREE,\n" + "  UNIQUE KEY `User_Unique` (`user_id`,`target_user_id`) USING BTREE\n" + "" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}

	/**
	 * 生成用户粉丝表分表结构
	 *
	 * @param tableIndex
	 * @return void
	 */
	private static String generateFans(int tableIndex) {
		String sql = "CREATE TABLE `user_fans_"+tableIndex+"` (\n" + "" +
				"  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" + "" +
				"  `user_id` bigint(20) NOT NULL COMMENT '当前用户ID',\n" + "" +
				"  `target_user_id` bigint(20) NOT NULL COMMENT '目标用户ID',\n" + "" +
				"  `bilateral` smallint(1) NOT NULL DEFAULT '0' COMMENT '当前用户是否同时关注目标用户，如果是则说明是双向关系   0：不是、1：是',\n" + "" +
				"  `create_time` datetime NOT NULL COMMENT '关系建立时间',\n" + "" +
				"  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" + "" +
				"  PRIMARY KEY (`id`) USING BTREE,\n" + "  UNIQUE KEY `User_Unique` (`user_id`,`target_user_id`) USING BTREE\n" + "" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}

	public static void main(String[] args) {
		int tableCnt = 256;
		for (int i=0; i<tableCnt; i++) {
//			System.err.println(ShardingUtil.generateAttention(i));
			System.err.println(ShardingUtil.generateFans(i));
		}
	}

}
