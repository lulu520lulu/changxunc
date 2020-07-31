package com.c.hangxunc.bean.home;

import java.util.List;

public class ModulesListBean {

    private List<ModuleBean> modules;

    public List<ModuleBean> getModules() {
        return modules;
    }

    public void setModules(List<ModuleBean> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "HomeTopBean{" +
                "modules=" + modules +
                '}';
    }
}
