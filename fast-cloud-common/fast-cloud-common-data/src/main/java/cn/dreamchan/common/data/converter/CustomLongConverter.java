package cn.dreamchan.common.data.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

/**
 *  自定义JSON 类型转换
 *
 * @author DreamChan
 */
public class CustomLongConverter extends StdSerializer<Long> {
	public CustomLongConverter() {
		super(Long.class);
	}

	/**
	 * 前端 JS 对于超过 16 位的 20175678901234567 会变成：20175678901234570
	 */
	@SneakyThrows
	@Override
	public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) {
		if (value.toString().length() > 15) {
			gen.writeString(value.toString());
		} else {
			gen.writeNumber(value);
		}
	}
}
