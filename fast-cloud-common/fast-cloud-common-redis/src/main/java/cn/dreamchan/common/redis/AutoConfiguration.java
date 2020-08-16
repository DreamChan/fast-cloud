package cn.dreamchan.common.redis;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author DreamChan
 */
@ComponentScan(value = {"cn.dreamchan.common.redis"})
public class AutoConfiguration {

}
