package com.zafer.base.page;

public class PageContext {
    private static final ThreadLocal<BasePage> PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public static BasePage getCurrentPage() {
        return PAGE_THREAD_LOCAL.get();
    }

    public static void setCurrentPage(BasePage page) {
        PAGE_THREAD_LOCAL.set(page);
    }
}
