package com.borshch.demodb.constraint;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD) //Type(класс, интерфейс), Method, Field, Parameter, над кем применмо - обязательна
@Retention(RetentionPolicy.RUNTIME) //Source - место жизни аннотации Source - исчезающая при работе программы аннотация, она доступна только до запуска,
// например, @Override

// , Class - аннотация, недоступная при RunTime, доступна до и после запуска, то есть во время компилляции и после окончания работы

// Runtime - д.б. доступна во время исполнения программы - обязательна
@Documented //она будет видна в документации - необязательна
@Constraint(validatedBy = PhoneValidator.class) //взаимная ссылка

public @interface /*это интерфейс-аннотация*/ Phone {

    String message() default "телефон д.б. указан корректно в формате +7, далее (три цифры в круглых скобках), далее 7 цифр, без пробелов"; // это аргумент аннотации
 // если аргумент имеет написание имени value, то при расстановке аннотации "имя параметра = " можно пропустить

    Class<?> [] groups() default {}; // это 2 обязательных параметра для аннотации constraint
    Class<? extends Payload> [] payload() default {}; // это 2 обязательных параметра для аннотации constraint

}
