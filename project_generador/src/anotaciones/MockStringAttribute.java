package anotaciones;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MockStringAttribute {
	int[] value();
}
