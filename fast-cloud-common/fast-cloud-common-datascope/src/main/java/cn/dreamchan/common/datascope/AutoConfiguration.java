package cn.dreamchan.common.datascope;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author DreamChan
 */
@ComponentScan(value = {"cn.dreamchan.common.datascope"})
public class AutoConfiguration {

}
