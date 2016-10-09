package com.buddysearch.android.presentation.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;
import javax.inject.Singleton;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ihor on 10/9/16.
 */

@Scope
@Retention(RUNTIME)
@Singleton
public @interface AppScope {
}
