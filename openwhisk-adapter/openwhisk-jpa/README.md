This is to create a custom openwhisk adapter, extending from Spring but addressing the current limitation.

This one uses the standard spring wrapper.  It then invokes the custom OpenwiskAdapterApp.  The final piece is the actual place of the function app.

The key part is in the function.properties to import said dependencies.

Some Commands
===============================

java -jar -Dthin.root=m2 runner.jar --thin.name=function --thin.dryrun

java -jar runner.jar --thin.name=function --function.name=getStudent --thin.root=m2 --spring.main.sources=com.myproject.cloud.CloudFunctionJpaApp


The problem is getting all the dep


## WORKS BUT...

--1) had to include-- 

2) had to create a bean

```

    // NOTE had to register this as getting an error
//	No qualifying bean of type 'org.springframework.cloud.function.context.catalog.FunctionInspector'
    // THIS IS A HACK FOR NOW
    @Bean
    @Primary
    FunctionInspector functionInspector(ConversionService conversionService, @Nullable CompositeMessageConverter messageConverter) {
        return new BeanFactoryAwareFunctionRegistry(conversionService, messageConverter);
//		return new InMemoryFunctionCatalog();
    }
```