package xyz.directplan.directlib.library;

import lombok.NonNull;

import java.lang.annotation.*;


/**
 * @author DirectPlan
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(MavenDependencies.class)
public @interface MavenDependency {

    @NonNull
    String groupId() default "";

    @NonNull
    String artifactId() default "";

    @NonNull
    String version() default "";

    String value() default "";
}
