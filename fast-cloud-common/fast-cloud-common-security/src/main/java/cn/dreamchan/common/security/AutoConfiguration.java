package cn.dreamchan.common.security;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author DreamChan
 */
@ComponentScan(value = {"cn.dreamchan.common.security"})
public class AutoConfiguration {

}
