package cn.dreamchan.generate;

import cn.dreamchan.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成
 *
 * @author DreamChan
 */
public class GeneratorMain {

    //生成文件所在项目路径
    private static String baseProjectPath = "/Users/dreamchan/IdeaProjects/fast-cloud/fast-cloud-modules/fast-cloud-system/fast-cloud-system-service/";

//    private static String baseProjectPath = "D:\\IdeaProjects\\fast-boot-backend\\";

    //文件存放路径
    private static String srcJavaPath = "src/main/java";

    //基本包名
    private static String basePackage = "cn.dreamchan";

    //作者
    private static String authorName = "DreamChan";


    //数据库连接参数配置
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/fast-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    private static String username = "root";
    private static String password = "root";

    //是否覆盖文件
    private static Boolean fileOverride = true;

    //table前缀
    private static String prefix = "";

    //模块名称
    private static String modulesName = "system";

    //要生成的表名templatestemplates
    private static String[] tables = {
            "oauth_client_details",
    };


    public static void main(String[] args) {

        AutoGenerator autoGenerator = new AutoGenerator();

        autoGenerator.setDataSource(getDataSource());

        autoGenerator.setStrategy(getStrategy());

        autoGenerator.setGlobalConfig(getGlobalConfig());

        autoGenerator.setPackageInfo(getPackageInfo());

        // 指定模板引擎 默认是VelocityTemplateEngine ，需要引入相关引擎依赖
        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        autoGenerator.setTemplate(getTemplateConfig());

        autoGenerator.setCfg(getInjectionConfig());

        // 执行生成
        autoGenerator.execute();
    }


    /**
     * 注入自定义配置
     */
    private static InjectionConfig getInjectionConfig() {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            Map<String, Object> map = new HashMap<>();
            String basePack = "cn.dreamchan.common.core.base.";

            map.put("BaseEntity" , basePack + "BaseEntity");
            map.put("BaseController" ,basePack +  "BaseController");
            map.put("BasePageQueryParam" , basePack + "BasePageQueryParam");
            map.put("BaseService" , basePack + "BaseService");
            map.put("moduleName" ,  modulesName);
            map.put("modulesPack" , basePackage + "." + modulesName);
            map.put("stringUtils" , new StringUtils() {});
            this.setMap(map);
            }
        };

        //自定义文件输出位置
        List<FileOutConfig> fileOutList = new ArrayList<>();
        fileOutList.addAll(getBackendFile());
        fileOutList.addAll(getSqlFile());
        fileOutList.addAll(getFrontendFile());
        injectionConfig.setFileOutConfigList(fileOutList);

        return injectionConfig;
    }

    /**
     * 获取自定义 后台文件
     */
    private static List<FileOutConfig> getBackendFile() {
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();

        Map<String, String> backendFileMap = new HashMap<>();
        backendFileMap.put("Controller.java",       "/%s/controller");
        backendFileMap.put("Service.java",          "/%s/service");
        backendFileMap.put("ServiceImpl.java",      "/%s/service/impl");
        backendFileMap.put("Mapper.java",           "/%s/mapper");
        backendFileMap.put("Mapper.xml",            "/%s/mapper/xml");
        backendFileMap.put("Entity.java",           "/%s/pojo/entity");
        backendFileMap.put("EditParam.java",        "/%s/pojo/dto");
        backendFileMap.put("PageQueryParam.java",   "/%s/pojo/dto");
        backendFileMap.put("Vo.java",               "/%s/pojo/vo");
        backendFileMap.put("MapStruct.java",        "/%s/service/mapstruct");

        backendFileMap.forEach((filename, outPath) -> {
            FileOutConfig fileOutConfig = new FileOutConfig("/templates/back/" + filename + ".vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputFilePath(outPath, tableInfo.getEntityName() + filename);
                }
            };
            fileOutConfigList.add(fileOutConfig);
        });

        return fileOutConfigList;
    }

    /**
     * 获取菜单的sql 文件
     */
    private static List<FileOutConfig>  getSqlFile() {
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();

        Map<String, String> sqlFileMap = new HashMap<>();
        sqlFileMap.put("insertmenu.sql",       "/%s/sql");

        sqlFileMap.forEach((filename, outPath) -> {
            FileOutConfig fileOutConfig = new FileOutConfig("/templates/sql/" + filename + ".vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputFilePath(outPath,  tableInfo.getEntityName().toLowerCase() + "_menu.sql");
                }
            };
            fileOutConfigList.add(fileOutConfig);
        });

        return fileOutConfigList;
    }


    /**
     * 获取自定义 前台文件
     */
    private static List<FileOutConfig> getFrontendFile() {
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();

        Map<String, String> frontFileMap = new HashMap<>();
        frontFileMap.put("api.js",       "/%s/front");
        frontFileMap.put("index.vue",    "/%s/front");

        frontFileMap.forEach((filename, outPath) -> {
            FileOutConfig fileOutConfig = new FileOutConfig("/templates/front/" + filename + ".vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String tableName = tableInfo.getEntityName().toLowerCase();
                    String realFileName = filename;
                    if("api.js".equals(filename)){
                        realFileName = tableInfo.getEntityName().toLowerCase() + ".js";
                    }
                    return getOutputFilePath(outPath + File.separator + tableName,  realFileName);
                }
            };
            fileOutConfigList.add(fileOutConfig);
        });

        return fileOutConfigList;
    }

    /**
     * 获取后台文件生成路径
     */
    private static String getOutputFilePath(String path, String filename){
        path = String.format(path, modulesName);

        String basePath = baseProjectPath.replace("/", File.separator);
        String srcPath = srcJavaPath.replace("/", File.separator);
        String packPath = (basePackage + path).replace(".", File.separator);

        return basePath + srcPath + File.separator + packPath + File.separator + filename;
    }


    /**
     * 策略配置
     */
    private static StrategyConfig getStrategy() {
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("create_by",   FieldFill.INSERT));
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_by",   FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));

        return new StrategyConfig()
                .setCapitalMode(true) // 全局大写命名
                .setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setInclude(tables) // 需要生成的表
                .setRestControllerStyle(true)
                .setTableFillList(tableFillList)
                .setEntityLombokModel(true); //【实体】是否为lombok模型（默认 false

        //.setExclude(new String[]{"test"}) // 排除生成的表
        // 自定义实体父类
        // .setSuperEntityClass("com.baomidou.demo.TestEntity")
        // 自定义实体，公共字段
        //.setSuperEntityColumns(new String[]{"test_id"})
        //.setTableFillList(tableFillList)
        // 自定义 mapper 父类 默认BaseMapper
        //.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper")
        // 自定义 config 父类 默认IService
        // .setSuperServiceClass("com.baomidou.demo.TestService")
        // 自定义 config 实现类父类 默认ServiceImpl
        // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
        // 自定义 controller 父类
        //.setSuperControllerClass("com.kichun."+packageName+".controller.AbstractController")
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // .setEntityColumnConstant(true)
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // .setEntityBuilderModel(true)
        // Boolean类型字段是否移除is前缀处理
        // .setEntityBooleanColumnRemoveIsPref  ix(true)
        // .setRestControllerStyle(true)
        // .setControllerMappingHyphenStyle(true)
    }


    /**
     * 全局配置
     */
    private static GlobalConfig getGlobalConfig() {
        return new GlobalConfig()
                .setOutputDir(baseProjectPath + "/src/main/java" )//输出目录
                .setFileOverride(fileOverride)// 是否覆盖文件
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setOpen(false)//生成后打开文件夹
                .setAuthor(authorName)
                .setDateType(DateType.TIME_PACK)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper" )
                .setXmlName("%sMapper" )
                .setServiceName("%sService" )
                .setServiceImplName("%sServiceImpl" )
                .setControllerName("%sController" );
    }


    /**
     * 包配置
     */
    private static PackageConfig getPackageInfo() {
        return new PackageConfig()
                .setModuleName(modulesName)
                .setParent(basePackage)// 自定义包路径
                .setController("controller")// 这里是控制器包名，默认 web
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("mapper" );
    }

    /**
     * 模板配置
     */
    private static TemplateConfig getTemplateConfig() {
        return new TemplateConfig()
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                .setController(null)
                .setEntity(null)
                .setMapper(null)
                .setXml(null)
                .setService(null)
                .setServiceImpl(null);
    }

    /**
     * 数据库配置
     */
    private static DataSourceConfig getDataSource() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName(driverName)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password)
                .setTypeConvert(new MySqlTypeConvert());
    }

}
