mybatis简介
-----------------------------
	MyBatis 本是apache的一个开源项目iBatis, 
	2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。
	2013年11月迁移到Github。

	iBATIS一词来源于“internet”和“abatis”的组合，是一个基于Java的持久层框架。
	iBATIS提供的持久层框架包括SQL Maps和Data Access Objects（DAO）

mybatis 官方网站
-----------------------------
	http://www.mybatis.org/mybatis-3/

	概念
	MyBatis 是支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架。
	MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
	MyBatis 可以对配置和原生Map使用简单的 XML 或注解，
	将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。

mybatis 下载地址
-----------------------------
	https://github.com/mybatis/mybatis-3 打开后点击 Download Latest 链接

	https://github.com/mybatis/mybatis-3/releases/tag/mybatis-3.4.1
	点击  Downloads:
	    mybatis-3.4.1.zip  下载


mybatis 3.4.1 入门实例
-----------------------------
1)建立java项目，添加jar文件
	mybatis-3.4.1.jar
	mysql-5.1.40-bin.jar
2)在src目录下建立mybatis-config.xml文件，为项目框架核心配置文件
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration
	  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	  "http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
		<typeAliases>
			<typeAlias alias="student" type="com.fz.mybatis.mapper.Student" />
		</typeAliases>
		<environments default="development">
			<environment id="development">
				<transactionManager type="JDBC" />
				<dataSource type="POOLED">
					<property name="driver" value="com.mysql.jdbc.Driver" />
					<property name="url"
						value="jdbc:mysql://localhost:3306/db?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=true" />
					<property name="username" value="root" />
					<property name="password" value="" />
				</dataSource>
			</environment>
		</environments>
		<mappers>
			<mapper resource="com/fz/mybatis/mapper/StudentMapper.xml" />
		</mappers>
	</configuration>
3)建立模型类 实体类
	package com.fz.mybatis.mapper;
	public class Student {
		private int id;
		private String name;
		private String address;
		/*生成getter setter 方法*/
	}

4)建立实例映射文件在实体类同一路径下 StudentMapper.xml文件
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
	  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.fz.mybatis.mapper.StudentMapper">
		<select id="aa" resultType="student">
			select * from student
		</select>
		
		<delete id="dd" parameterType="int">
			delete from student where id=#{id}
		</delete>
	</mapper>

5)编写demo程序直接测试
	//建立数据库会话工厂
	SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));

	//读取会话
	SqlSession s = sf.openSession();
	
	//实现删除操作
	s.delete("dd",6);
	
	//实现查询操作 返回List结果
	List<Student> sts = s.selectList("aa");
	for (Student st : sts) {
		System.out.println(st.getName());
	}
	
	s.commit();
mybatis 3.4.1 使用注解方式实现 CRUD操作
-----------------------------------------------------------------------
1、建立maven java 项目 在 pom.xml 文件加入如下依赖
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>5.1.40</version>
	</dependency>

	<dependency>
	    <groupId>junit</groupId>
	    <artifactId>junit</artifactId>
	    <version>4.12</version>
	</dependency>

	<dependency>
	    <groupId>org.mybatis</groupId>
	    <artifactId>mybatis</artifactId>
	    <version>3.4.1</version>
	</dependency>
	<dependency>
	    <groupId>org.projectlombok</groupId>
	    <artifactId>lombok</artifactId>
	    <version>1.16.12</version>
	</dependency>

2、打开项目src/main/resources 目录下建立 
	db.properties jdbc参数文件
		db.driver=com.mysql.jdbc.Driver
		db.url=jdbc:mysql://localhost:3306/db?useUnicode=true&characterEncoding=utf8&useSSL=true
		db.user=root
		db.password=


	conf.xml  mybatis 核心配置文件
		
mybatis 3.4.1 one2one
--------------------------------------------------
建立表
	create table db_member(
	    id int unsigned auto_increment,
	    account varchar(20) not null,
	    pass varchar(32) not null,
	    name varchar(20),
	    aid int unsigned,
	    primary key(id)
	)engine=innodb charset=utf8;

	create table db_address(
	    aid int unsigned auto_increment,
	    aname varchar(20) not null,
	    aphone varchar(11) not null,
	    primary key(aid)
	)engine=innodb auto_increment=100 charset=utf8;

	drop table db_address;
	truncate db_address;

	insert into db_address values(null,'河南省郑州市','13014577032');
	insert into db_address values(null,'北京市朝阳区','11000242424');

	select * from db_address;

	insert into db_member values(null,'admin','123','李四',100);
	insert into db_member values(null,'aa','aa','赵六',101);
	insert into db_member(account,pass,name) values('abc','abc','王五');


	select * from db_member;

maven 依赖相关的jar包
  <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.1</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.40</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.2</version>
        </dependency>	


maven 项目资源builde
	<build>
		<finalName>${project.artifactId}</finalName>
		<sourceDirectory>src/main/java</sourceDirectory>
		<testSourceDirectory>src/test/java</testSourceDirectory>
		<resources>
		    <resource>
			<directory>src/main/java</directory>
			<includes>
			    <include>**/*.xml</include>
			</includes>
		    </resource>
		    <resource>
			<directory>src/main/resources</directory>
			<includes>
			    <include>**/*.xml</include>
			    <include>**/*.properties</include>
			</includes>
		    </resource>
		</resources>
	</build>

jdbc参数配置文件 src/db.properties 文件
	db.driver=com.mysql.jdbc.Driver
	db.url=jdbc:mysql://localhost:3306/db?useUnicode=true&characterEncoding=utf8&useSSL=true
	db.username=root
	db.password=
		

核心配置文件 src/conf.xml 文件
	<!DOCTYPE configuration
		PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
	    <properties resource="db.properties"/>

	    <typeAliases>
		<package name="com.fz.model"></package>
	    </typeAliases>
	    <environments default="development">
		<environment id="development">
		    <transactionManager type="JDBC"/>
		    <dataSource type="POOLED">
			<property name="driver" value="${db.driver}"/>
			<property name="url" value="${db.url}"/>
			<property name="username" value="${db.username}"/>
			<property name="password" value="${db.password}"/>
		    </dataSource>
		</environment>
	    </environments>
	    <mappers>
		<package name="com.fz.mapper"/>
	    </mappers>
	</configuration>	

实体类	com.fz.model.Address 类
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public class Address {
	    private int id;
	    private String name;
	    private String phone;
	}


	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public class Member {
	    private int id;
	    private String account;
	    private String pass;
	    private String name;
	    private Address address;
	}


接口及配置文件
	com.fz.mapper.MemberMapper 接口映射
	public interface MemberMapper {
	    public Member queryById(int id);
	    public List<Map<String,Object>> query();
	    public List<Map<String,Object>> queryAll();
	}
	

	com.fz.mapper.MemberMapper.xml 映射配置文件
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.fz.mapper.MemberMapper">

	    <resultMap id="mem" type="member">
		<id column="id" property="id"></id>
		<result property="account" column="account"></result>
		<result property="pass" column="pass"></result>
		<result property="name" column="name"></result>
		<association property="address" javaType="address">
		    <id property="id" column="aid"></id>
		    <result property="name" column="aname"/>
		    <result property="phone" column="aphone"/>
		</association>
	    </resultMap>

	    <resultMap id="m2" type="member">
		<id column="id" property="id"/>
		<result property="account" column="account"/>
		<result property="pass" column="pass"/>
		<result property="name" column="name"/>
		<result property="address.id" column="aid"/>
		<result property="address.name" column="aname"/>
		<result property="address.phone" column="aphone"/>
	    </resultMap>

	    <resultMap id="aaa" type="address">
		<result property="id" column="aid"/>
		<result property="name" column="aname"/>
		<result property="phone" column="aphone"/>
	    </resultMap>

	    <resultMap id="m3" type="member">
		<id column="id" property="id"/>
		<result property="account" column="account"/>
		<result property="pass" column="pass"/>
		<result property="name" column="name"/>
		<association property="address" column="aid" resultMap="aaa" />
	    </resultMap>

	    <resultMap id="m4" type="member">
		<id column="id" property="id"/>
		<result property="account" column="account" />
		<result property="name" column="name" />
		<result property="pass" column="pass" />
		<association property="address" column="aid" select="com.fz.mapper.AddressMapper.queryById" />
	    </resultMap>

	    <select id="queryById" parameterType="int" resultMap="m4">
		select * from db_member m,db_address a where m.id=#{id} and m.aid=a.aid
	    </select>

	    <select id="query" resultType="HashMap">
		select m.name,a.aname from db_member m,db_address a where m.aid=a.aid
	    </select>

	    <select id="queryAll" resultType="HashMap">
		select m.name,a.aname from db_member m left JOIN db_address a on m.aid = a.aid
	    </select>
	</mapper>


	com.fz.mapper.AddressMapper 接口映射
	public interface AddressMapper {
	    public Address queryById(int id);
	}

	com.fz.mapper.AddressMapper.xml 映射文件
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.fz.mapper.AddressMapper">
	    <resultMap id="aa" type="address">
		<result property="id" column="aid"/>
		<result property="name" column="aname"/>
		<result property="phone" column="aphone"/>
	    </resultMap>
	    <select id="queryById" parameterType="int" resultMap="aa">
		select * from db_address where aid = #{id}
	    </select>
	</mapper>


	
-- user 用户
create table db_user(
    id int unsigned auto_increment,
    name varchar(20),
    pass varchar(32),
    primary key(id)
)engine=innodb charset=utf8;
insert into db_user values(null,'admin','123');
insert into db_user values(null,'aa','aa');
insert into db_user values(null,'user','user');
select * from db_user;

-- role 角色
create table db_role(
    id int unsigned auto_increment,
    name varchar(20),
    uid int unsigned,
    primary key(id)
)engine=innodb charset=utf8;

drop table db_role;
insert into db_role values(null,'删除',1);
insert into db_role values(null,'编辑',1);
insert into db_role values(null,'修改',2);
insert into db_role values(null,'审核',1);
insert into db_role values(null,'打开',1);
insert into db_role values(null,'关闭',2);
insert into db_role values(null,'删除',2);
select * from
select * from db_role;

	@Data
	public class User {
	    private int id;
	    private String name;
	    private String pass;
	    private List<Role> roles;
	}

	public interface UserMapper {
	    public User findById(int id);
	}

	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.fz.mapper.UserMapper">
	    <resultMap id="ur" type="user">
		<id property="id" column="id"/>
		<result property="name" column="name"/>
		<result property="pass" column="pass"/>
		<collection property="roles" column="id" select="com.fz.mapper.RoleMapper.findByUserId"></collection>
	    </resultMap>

	    <select id="findById" parameterType="int" resultMap="ur">
		select * from db_user where id=#{id}
	    </select>
	</mapper>
@Data
public class Role {
    private int id;
    private String name;
}

public interface RoleMapper {
    public Role findByUserId(int id);
}

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.fz.mapper.RoleMapper">
    <resultMap id="rr" type="role">
        <id property="id" column="rid"/>
        <result property="name" column="rname"/>
    </resultMap>

    <select id="findByUserId" parameterType="int" resultType="role">
        select * from db_role where uid=#{id}
    </select>
</mapper>

mybatis 动态SQL
------------------------------------------
1、if
	映射接口 StudentMapper.java
		public List<Student> search(Map<String,Object> map);
	映射配置文件  StudentMapper.xml
	    <select id="search" resultType="student" parameterType="Map">
		select * from student where 1=1
		<if test="name!=null">
		    and name like #{name}
		</if>
		<if test="address!=null">
		    and address like #{address}
		</if>
	    </select>
	    
	测试代码
		SqlSession s = MyBatisUtil.getSqlSession();
		StudentMapper sm = s.getMapper(StudentMapper.class);
		//List<Student> sts = sm.search(null); 查询所有学生信息

		Map<String,Object> m = new HashMap<String,Object>();
		//m.put("name","%a%");
		//m.put("name","%李%");
		m.put("name","李%");
		m.put("address","%郑州%");
		List<Student> sts = sm.search(m);


		for(Student st : sts){
		    System.out.println(st.getName());
		}
		s.commit();
		s.close();

2、choose (when, otherwise)
	   <select id="search" resultType="student" parameterType="Map">
		select * from student where 1=1
		<choose>
		    <when test="name!=null">
			and name like #{name}
		    </when>
		    <when test="address!=null">
			and address like #{address}
		    </when>
		    <otherwise>
			and name like '李%'
		    </otherwise>
		</choose>
	    </select>

3、trim (where, set)
	<where> where 元素知道只有在一个以上的if条件有值的情况下才去插入“WHERE”子句。而且，若最后的内容是“AND”或“OR”开头的，where 元素也知道如何将他们去除
	<select id="search" resultType="student" parameterType="Map">
		select * from student
		<where>
		    <if test="name!=null">
			and name=#{name}
		    </if>
		    <if test="address!=null">
			and address like #{address}
		    </if>
		</where>
		order by id desc
	</select>

	trim 元素 prefix 前辍 会主动加上where   会主动清除and 或 or开头 
	<trim prefix="WHERE" prefixOverrides="AND |OR ">
            <if test="name!=null">
                name=#{name}
            </if>
            <if test="address!=null">
                and address like #{address}
            </if>
        </trim>


	<set>
		public int update(Map<String,Object> map);

		<update id="update" parameterType="Map">
			<!--update student set name=#{name},address=#{address} where id=#{id}-->
			update student
			<set>
			    <if test="name!=null">name=#{name},</if>
			    <if test="address!=null">address=#{address},</if>
			</set>
			where id=#{id}
		</update>
4、foreach
	动态 SQL 的另外一个常用的必要操作是需要对一个集合进行遍历，通常是在构建 IN 条件语句的时候
	
	<foreach item="item" index="index" collection="list" open="(" separator="," close=")">
	    #{item}
	</foreach>

	where id in (6,7,8,9,10)



	<update id="update" parameterType="Map">
		update student set name=#{name} where id in
		<foreach item="a" collection="ids" open="(" separator="," close=")">
		    #{a}
		</foreach>
	</update>


	<delete id="delete" parameterType="Map">
		delete from student
		<where>
		    <if test="ids!=null">
			id in
			<foreach item="did" collection="ids" open="(" separator="," close=")">
			    #{did}
			</foreach>
		    </if>
		    <if test="name!=null">
			and name = #{name}
		    </if>
		    <if test="id!=null">
			and id=#{id}
		    </if>
		</where>
	</delete>

	Map<String,Object> mm = new HashMap<String,Object>();
        //mm.put("name","李123");
        //mm.put("address","上123");
        //mm.put("id",1);

        mm.put("name","aaa");
        mm.put("ids",new int[]{1,3,5,6,7,8});
        sm.update(mm);
        s.commit();

日志框架
	log4j
	logback

mybatis logback 显示sql
--------------------------------------------
1、src/main/resources/conf.xml mybatis 配置文件
	加入

    <settings>
        <setting name="logPrefix" value="dao."/>
    </settings>

2、maven项目pom.xml文件加入logback依赖包
        <!-- logback -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.1.8</version>
        </dependency>

        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.1.8</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.22</version>
        </dependency>

3、logback默认配置文件在 src/main/resources/logback.xml 
<?xml version="1.0"?>
<configuration>
    <!-- ch.qos.logback.core.ConsoleAppender 控制台输出 -->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>[%-5level] %d{HH:mm:ss.SSS} [%thread] %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <!-- 日志级别 -->
    <root>
        <level value="error" />
        <appender-ref ref="console" />
    </root>
    <logger name="dao" level="DEBUG"/>
</configuration>

4、maven 读取资源文件配置文件 pom.xml
     <sourceDirectory>src/main/java</sourceDirectory>
        <testSourceDirectory>src/test/java</testSourceDirectory>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.xml</include>
                    <include>**/*.properties</include>
                </includes>
            </resource>
        </resources>
        
 MyBatis Generator
 ----------------------------------------------------------------
 	自动的反向生成  Mapper接口              Model类                    Mapper映射文件
 			com.fz.mapper.	        com.fz.entity.             com.fz.mapper.
 			StudentMapper.java      Student.java		   StudentMapper.xml
 
 
 1、在maven项目中 pom.xml 添加如下配置  mybatis-generator-maven-plugin
 <plugin>
 	<groupId>org.mybatis.generator</groupId>
 	<artifactId>mybatis-generator-maven-plugin</artifactId>
 	<version>1.3.5</version>
 	<configuration>
 	    <!-- 如果此行不配置，则生成器自动找 src/main/resources/generatorConfig.xml -->
 	    <configurationFile>src/main/resources/gc.xml</configurationFile>
 	    
 	    <verbose>true</verbose>
 	    <overwrite>true</overwrite>
 	</configuration>
 
 	<!-- 如果下边依赖数据库驱动不配置，则在src/main/resources/generatorConfig.xml 中配置如下 -->
 	<dependencies>
 	    <dependency>
 		<groupId>mysql</groupId>
 		<artifactId>mysql-connector-java</artifactId>
 		<version>5.1.40</version>
 	    </dependency>
 	</dependencies>
 </plugin>
 
 2、在src/main/resources/gc.xml 内容如下
 <?xml version="1.0" encoding="UTF-8"?>
 <!DOCTYPE generatorConfiguration
         PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
         "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
 
 <generatorConfiguration>
     <properties resource="db.properties"/>
     <context id="mysql" targetRuntime="MyBatis3">
 
         <commentGenerator>
             <property name="suppressDate" value="false"/>
             <property name="suppressAllComments" value="true"/>
         </commentGenerator>
 
         <jdbcConnection driverClass="${db.driver}" connectionURL="${db.url}" userId="${db.username}" password="${db.password}"/>
 
         <javaTypeResolver>
             <property name="forceBigDecimals" value="false"/>
         </javaTypeResolver>
 
         <javaModelGenerator targetPackage="com.mybatis.model" targetProject="src/main/java">
             <property name="enableSubPackages" value="true"/>
             <property name="trimStrings" value="true"/>
         </javaModelGenerator>
 
         <sqlMapGenerator targetPackage="com.mybatis.mapper" targetProject="src/main/java">
             <property name="enableSubPackages" value="true"/>
         </sqlMapGenerator>
 
         <javaClientGenerator type="XMLMAPPER" targetPackage="com.mybatis.mapper" targetProject="src/main/java">
             <property name="enableSubPackages" value="true"/>
         </javaClientGenerator>
         <table tableName="student" domainObjectName="Student"
                         enableCountByExample="false" enableUpdateByExample="false"
                         enableDeleteByExample="false" enableSelectByExample="false"
                         selectByExampleQueryId="false"/>
     </context>
 </generatorConfiguration>
 
 3、在idea工具打开maven 面板 插行插件
 
 
 
 maven项目 配置 mybatis-generator-maven-plugin
 -------------------------------------------------------------------------
 <plugin>
 	<groupId>org.mybatis.generator</groupId>
 	<artifactId>mybatis-generator-maven-plugin</artifactId>
 	<version>1.3.5</version>
 	<configuration>
 	    <!-- 如果此行不配置，则生成器自动找 src/main/resources/generatorConfig.xml -->
 	    <configurationFile>src/main/resources/gc.xml</configurationFile>
 	    
 	    <verbose>true</verbose>
 	    <overwrite>true</overwrite>
 	</configuration>
 
 	<!-- 如果下边依赖数据库驱动不配置，则在src/main/resources/generatorConfig.xml 中配置如下 -->
 	<dependencies>
 	    <dependency>
 		<groupId>mysql</groupId>
 		<artifactId>mysql-connector-java</artifactId>
 		<version>5.1.40</version>
 	    </dependency>
 	</dependencies>
 </plugin>
 
 
 <generatorConfiguration>
     <!-- 引入配置文件src/main/resources/db.properties -->
     <properties resource="db.properties"/>
     
     <!-- 如果生成器插件配置了数据库驱动，此行可以省略 -->
     <classPathEntry location="d:/mysql.jar"/>
 
     <context id="mysql" targetRuntime="MyBatis3">
     </context>
 </generatorConfiguration>
一级
	会话
   SqlSession s = MyBatisUtil.getSqlSession();
        StudentMapper sm = s.getMapper(StudentMapper.class);
        //Student st = new Student();
        //st.setName("jack");
        //st.setAddress("郑州");
        //sm.insert(st);
        List<Student> a = sm.queryAll();
        //s.clearCache();  清除会话级别缓存
        List<Student> b = sm.queryAll();
        System.out.println(a==b);
        //s.clearCache();
        s.commit();
        s.close();

二级
	工厂

   conf.xml 总配置文件配置
     <settings>
        <setting name="logPrefix" value="dao."/>
        <setting name="cacheEnabled" value="true"/>  默认是true
    </settings>


    Mapper.xml文件
    <mapper namespace="com.mybatis.mapper.StudentMapper">
         <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true" />


	     <select id="queryAll" resultType="student" useCache="false">
      select * from student
    </select>

	SqlSessionFactory sf = MyBatisUtil.getSqlSessionFactory();
        SqlSession s = sf.openSession();
        StudentMapper sm = s.getMapper(StudentMapper.class);
        //Student st = new Student();
        //st.setName("jack");
        //st.setAddress("郑州");
        //sm.insert(st);
        List<Student> a = sm.queryAll();
        s.close();
        //s.clearCache();
        SqlSession ss = sf.openSession();
        System.out.println(ss==s);
        StudentMapper ssm = ss.getMapper(StudentMapper.class);
        List<Student> b = ssm.queryAll();
        System.out.println(a==b);
        //s.clearCache();


        ss.close();


mybatis 数据分页实现
------------------------------------------
  1、逻辑分页
	  StudentMapper.java
	  public List<Student> queryAll(RowBounds r);

	  StudentMapper.xml
	   <select id="queryAll" resultType="student">
		select * from db_student
	    </select>

	测试代码
	SqlSession s = MyBatisUtil.getSqlSession();
	StudentMapper sm = s.getMapper(StudentMapper.class);
	//RowBounds r = new RowBounds(0,2);1页
	//RowBounds r = new RowBounds(2,2);2页
	//RowBounds r = new RowBounds(4,2);3页

	List<Student> sts = sm.queryAll(r);
	for(Student st :sts){
	    System.out.println(st.getName());
	}

  2、物理分页显示
	   public List<Student> pp(Map m);

	    <select id="pp" resultType="student" parameterType="Map">
	      select * from db_student
	      <if test="start!=null and size!=null">
		  limit #{start},#{size}
	      </if>
	    </select>

		StudentMapper sm = s.getMapper(StudentMapper.class);
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("start",2);
		m.put("size",2);
		List<Student> sts = sm.pp(m);
		for(Student st :sts){
		    System.out.println(st.getName());
		}
