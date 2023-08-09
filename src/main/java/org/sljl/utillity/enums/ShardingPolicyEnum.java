package org.sljl.utillity.enums;

/**
 * 数据库分表策略
 * 
 * @author L.Y.F
 *
 */
public enum ShardingPolicyEnum {
	
	/** Hash散列方式分16张表 */
	SHARDING_FOR_HASH16(1),
	/** Hash散列方式分32张表 */
	SHARDING_FOR_HASH32(2),
	/** Hash散列方式分64张表 */
	SHARDING_FOR_HASH64(3),
	/** Hash散列方式分128张表 */
	SHARDING_FOR_HASH128(4),
	/** Hash散列方式分256张表 */
	SHARDING_FOR_HASH256(5),
	/** Hash散列方式分512张表 */
	SHARDING_FOR_HASH512(6),
	/** Hash散列方式分1024张表 */
	SHARDING_FOR_HASH1024(7),
//	/** 滚动增长方式分表（目前基数400万） */
//	SHARDING_FOR_INCREASE(6),
	;

	private int shardingPolicy;

	private ShardingPolicyEnum(int shardingPolicy) {
		this.shardingPolicy = shardingPolicy;
	}

	public int getShardingPolicy() {
		return shardingPolicy;
	}

	public void setShardingPolicy(int shardingPolicy) {
		this.shardingPolicy = shardingPolicy;
	}

}
