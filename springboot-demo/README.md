# [GlobalResponseHandle](src/main/java/com/xc/study/config/GlobalResponseHandle.java)
    global response, 全局返回处理. 以GlobalResponse中的data封装返回结果.

# [GlobalExceptionHandler](src/main/java/com/xc/study/config/GlobalExceptionHandler.java)
    global exception, 全局异常处理. 以GlobalResponse中的code标记异常状态码, http status返回200.

# [FieldValidator](src/main/java/com/xc/study/config/FieldValidator.java), [FieldValidated](src/main/java/com/xc/study/annotation/FieldValidated.java)
    Realize parameter verification through custom annotations and enumeration classes.
    通过自定义注解及枚举类的方式实现参数校验. @Validated, @Valid.