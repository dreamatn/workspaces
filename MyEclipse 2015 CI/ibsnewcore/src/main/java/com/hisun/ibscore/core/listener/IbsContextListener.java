package com.hisun.ibscore.core.listener;

import com.hisun.ibscore.core.domain.IbsMemoryTree;
import com.hisun.ibscore.core.domain.IbsParm;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext;

@WebListener
public class IbsContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //加载ibsParmMap
        IbsMemoryTree   ibsMemoryTree   =   new IbsMemoryTree();
        HashMap<String,HashMap<String,Object>>  ibsMemoryTreeMap    =   new HashMap <>();

        HashMap<String,Object>  ibsParmMap  =   new HashMap <>();
        ibsParmMap.put("I0011660",new IbsParm("I0011660","I0011660","acNo,true;amt,true;"));
        ibsParmMap.put("I0011670",new IbsParm("I0011670","I0011660","acNo,true;amt,false;"));
        ibsParmMap.put("T0011660",new IbsParm("I0011660","I0011660","N;"));
        ibsParmMap.put("T0011670",new IbsParm("I0011670","I0011660","D;"));
        ibsMemoryTreeMap.put("ibsParm",ibsParmMap);

        ibsMemoryTree.setMemoryTree(ibsMemoryTreeMap);

        //加载到ServletContext
        sce.getServletContext().setAttribute("ibsMemoryTree",ibsMemoryTree);
        sce.getServletContext().setAttribute("ibsTemp","hisun");

        System.out.print("---debug : IbsContextListener contextInitialized  \n");
        System.out.print("---debug : IbsRequestResponseBodyMethodProcessor.resolveArgument ibsMemoryTree \n" +
                sce.getServletContext().getAttribute("ibsMemoryTree").toString()  +
                "\n");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.print("---debug : IbsContextListener contextDestroyed \n");

    }

}
