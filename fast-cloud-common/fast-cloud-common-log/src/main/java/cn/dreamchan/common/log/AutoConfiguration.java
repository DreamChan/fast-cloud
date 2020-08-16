package cn.dreamchan.common.log;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author DreamChan
 */
@ComponentScan(value = {"cn.dreamchan.common.log"})
public class AutoConfiguration {

}
