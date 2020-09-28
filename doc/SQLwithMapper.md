# SQL with Mapper


## 商品

```java
@Results({
  @Result(property = "goodsId",column = "goods_id"),
  @Result(property = "goodsName",column = "goods_name"),
  @Result(property = "goodsSubName",column = "goods_sub_name"),
  @Result(property = "goodsPrice",column = "goods_price"),
  @Result(property = "goodsInventory",column = "goods_inventory"),
  @Result(property = "goodsSource",column = "goods_source"),
  @Result(property = "goodsPicture",column = "goods_picture"),
  @Result(property = "goodsDescribe",column = "goods_describe"),
  @Result(property = "goodsTypeId",column = "goods_type_id")})
```

**selectAllGoods**

```sql
SELECT g.goods_id, g.goods_name, g.goods_sub_name, g.goods_source, g.goods_picture
	, g.goods_describe, g.goods_type_id
	, (
		SELECT Concat(MIN(goods_price), '-', MAX(goods_price))
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_price
	, (
		SELECT SUM(goods_inventory)
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_inventory
FROM goods g
ORDER BY g.goods_id;
```


**randSelectAllGoods**

```sql
SELECT g.goods_id, g.goods_name, g.goods_sub_name, g.goods_source, g.goods_picture
	, g.goods_describe, g.goods_type_id
	, (
		SELECT Concat(MIN(goods_price), '-', MAX(goods_price))
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_price
	, (
		SELECT SUM(goods_inventory)
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_inventory
FROM goods g
ORDER BY rand()
LIMIT 12;
```


**selectGoodsListByKeyWord**

```sql
SELECT g.goods_id, g.goods_name, g.goods_sub_name, g.goods_source, g.goods_picture
	, g.goods_describe, g.goods_type_id
	, (
		SELECT Concat(MIN(goods_price), '-', MAX(goods_price))
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_price
	, (
		SELECT SUM(goods_inventory)
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_inventory
FROM goods g
	JOIN goods_sub_type s ON g.goods_type_id = goods_sub_type_id
	JOIN goods_type_main_with_sub_relationship r ON r.goods_type_main_with_sub_relationship_id = s.goods_sub_type_id
	JOIN goods_main_type m ON r.goods_type_main_with_sub_relationship_main_id = m.goods_main_type_id
WHERE (g.goods_name LIKE #{keyWord}
	OR g.goods_sub_name LIKE #{keyWord}
	OR g.goods_describe LIKE #{keyWord}
	OR s.goods_sub_type_name LIKE #{keyWord}
	OR m.goods_main_type_name LIKE #{keyWord});
```


**selectOneGoods**

```sql
SELECT g.goods_id, g.goods_name, g.goods_sub_name, g.goods_source, g.goods_picture
	, g.goods_describe, g.goods_type_id
	, (
		SELECT Concat(MIN(goods_price), '-', MAX(goods_price))
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_price
	, (
		SELECT SUM(goods_inventory)
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_inventory
FROM goods g
WHERE g.goods_id = #{goodsId};
```

**selectGoodsByGoodsSubType**

```sql
SELECT g.goods_id, g.goods_name, g.goods_sub_name, g.goods_source, g.goods_picture
	, g.goods_describe, g.goods_type_id
	, (
		SELECT Concat(MIN(goods_price), '-', MAX(goods_price))
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_price
	, (
		SELECT SUM(goods_inventory)
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_inventory
FROM goods g
WHERE g.goods_type_id = #{goodsSubTypeId};
```


## 父类型

```java
@Results({
  @Result(property = "goodsMainTypeId",column = "goods_main_type_id"),
  @Result(property = "goodsMainTypeName",column = "goods_main_type_name"),
  @Result(property = "goodsMainTypePicture",column = "goods_main_type_picture")})
```

**selectAllGoodsMainType**

```sql
SELECT *
FROM goods_main_type
```


## 子类型

```java
@Results({
  @Result(property = "goodsSubTypeId",column = "goods_sub_type_id"),
  @Result(property = "goodsSubTypeName",column = "goods_sub_type_name"),
  @Result(property = "goodsSubTypePicture",column = "goods_sub_type_picture")})
```

**selectAllGoodsSubType**

```sql
SELECT *
FROM goods_sub_typee
```

**selectGoodsSubTypeByGoodsMainTypeId**

```sql
SELECT *
FROM goods_sub_type
WHERE goods_sub_type_id IN (
	SELECT goods_type_main_with_sub_relationship_sub_id
	FROM goods_type_main_with_sub_relationship r
	WHERE r.goods_type_main_with_sub_relationship_main_id = #{goodsMainTypeId}
);
```


## 参数

```java
@Results({
  @Result(property = "id",column = "id"),
  @Result(property = "goodsId",column = "goods_id"),
  @Result(property = "goodsParameter",column = "goods_parameter"),
  @Result(property = "goodsOptional",column = "goods_optional")})
```
**selectAllGoodsParameterByGoodsId**

```sql
SELECT *
FROM goods_parameter
WHERE goods_id = #{goodsId};
```

## 参数选项
```java
@Results({
  @Result(property = "id",column = "id"),
  @Result(property = "goodsParameterId",column = "goods_parameter_id"),
  @Result(property = "goodsOption",column = "goods_option"),
  @Result(property = "goodsPicture",column = "goods_picture")})
```

**selectAllGoodsParameterOptionByParameterId**

```sql
SELECT *
FROM goods_parameter_option
WHERE goods_parameter_id = #{goodsParameterId};
```


## 用户

```java
@Results({
  @Result(property = "userId",column = "user_id"),
  @Result(property = "goodsId",column = "user_name"),
  @Result(property = "goodsParameter",column = "user_nickname"),
  @Result(property = "goodsOptional",column = "user_password"),
  @Result(property = "goodsOptional",column = "user_picture"),
  @Result(property = "goodsOptional",column = "create_time")})
```


**selectUserByUserId**

```sql
SELECT u.user_id, u.user_name, u.user_nickname, u.user_password, u.user_picture
	, u.create_time
FROM users u
WHERE user_id = #{userId}
```

