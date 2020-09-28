
# SQL

```sql
-- 根据父分类名称查询相关的子分类列表
SELECT *
FROM goods_sub_type
WHERE goods_sub_type_id IN (
	SELECT goods_type_main_with_sub_relationship_sub_id
	FROM goods_type_main_with_sub_relationship r
	WHERE r.goods_type_main_with_sub_relationship_main_id = (
		SELECT goods_main_type_id
		FROM goods_main_type
		WHERE goods_main_type_name = '电脑办公'
	)
);

-- 根据父分类ID查询相关的子分类列表
SELECT *
FROM goods_sub_type
WHERE goods_sub_type_id IN (
	SELECT goods_type_main_with_sub_relationship_sub_id
	FROM goods_type_main_with_sub_relationship r
	WHERE r.goods_type_main_with_sub_relationship_main_id = 1
);

-- 根据子类型ID查询商品
SELECT *
FROM goods
WHERE goods_type_id = 130;

-- 根据父类型ID查询商品
SELECT *
FROM goods
WHERE goods_type_id IN (
	SELECT goods_type_main_with_sub_relationship_sub_id
	FROM goods_type_main_with_sub_relationship r
	WHERE r.goods_type_main_with_sub_relationship_main_id = 9
);

-- 父子类型关联
SELECT *
FROM goods_sub_type s
	JOIN goods_type_main_with_sub_relationship r ON r.goods_type_main_with_sub_relationship_id = s.goods_sub_type_id
	JOIN goods_main_type m ON r.goods_type_main_with_sub_relationship_main_id = m.goods_main_type_id;

-- 查询商品的同时查询父类型及子类型
SELECT g.goods_id, g.goods_name, s.goods_sub_type_name, m.goods_main_type_name
FROM goods g
	JOIN goods_sub_type s ON g.goods_type_id = goods_sub_type_id
	JOIN goods_type_main_with_sub_relationship r ON r.goods_type_main_with_sub_relationship_id = s.goods_sub_type_id
	JOIN goods_main_type m ON r.goods_type_main_with_sub_relationship_main_id = m.goods_main_type_id;

-- 全字段搜索
SELECT *
FROM goods
WHERE (goods_name LIKE '%大%'
	OR goods_sub_name LIKE '%大%'
	OR goods_describe LIKE '%大%');

-- 结合商品类型作为搜索关键字
SELECT g.goods_id, g.goods_name, g.goods_sub_name, g.goods_source, g.goods_picture
	, g.goods_describe, g.goods_type_id
	, (
		SELECT Concat(MAX(goods_price), '-', MIN(goods_price))
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
WHERE (g.goods_name LIKE '%女%'
	OR g.goods_sub_name LIKE '%女%'
	OR g.goods_describe LIKE '%女%'
	OR s.goods_sub_type_name LIKE '%女%'
	OR m.goods_main_type_name LIKE '%女%');

-- 查询商品的价格区间
SELECT MAX(goods_price) AS max, MIN(goods_price) AS min
FROM goods_data
WHERE goods_id = 1;

-- 查询商品的价格区间
SELECT Concat(MAX(goods_price), '-', MIN(goods_price)) AS price
FROM goods_data
WHERE goods_id = 1;

-- 查询商品库存
SELECT SUM(goods_inventory)
FROM goods_data
WHERE goods_id = 1;

-- 查询商品ID为1的白色S码的价格 {1:1,2:5}
SELECT *
FROM goods_data
WHERE goods_id = 1
	AND goods_configuration = '{1:1,2:5}';

-- 查询相关商品的参数
SELECT *
FROM goods_parameter p
	JOIN goods_parameter_option o ON p.id = o.goods_parameter_id
WHERE p.goods_id = 1;

-- 查询单个商品,结合价格区间,库存总数
SELECT goods_id, goods_name, goods_sub_name, goods_source, goods_picture
	, goods_describe, goods_type_id
	, (
		SELECT Concat(MAX(goods_price), '-', MIN(goods_price))
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_price
	, (
		SELECT SUM(goods_inventory)
		FROM goods_data
		WHERE goods_id = 1
	) AS goods_inventory
FROM goods
WHERE goods_id = 1;


-- 根据用户id查询购物车及相关的信息
SELECT s.shopping_id, s.users_id, s.goods_id, s.shoppingnum, g.goods_name
	, g.goods_sub_name, goods_picture, d.goods_configuration, d.goods_price
FROM shopping s
	JOIN goods g ON s.goods_id = g.goods_id
	JOIN goods_data d ON s.goods_data_id = d.id


SELECT u.user_id,u.user_name,u.user_nickname,u.user_password,u.user_picture,u.create_time
FROM users u;


SELECT t.user_track_id, t.goods_id,t.create_time
FROM users_track t
WHERE user_id = #{userId};

SELECT c.user_contact_id, c.user_phone, c.user_email, user_address, c.create_time
FROM users_contact c
WHERE user_id = #{userId};

SELECT o.order_id, o.goods_id, o.goods_data_id, o.order_status, o.user_contact
	, shopping_num
FROM orders o
WHERE o.user_id = #{userId};

select * from goods_data where id=#{id}
```

