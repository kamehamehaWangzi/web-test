package com.wode.webtest.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/mydb")
public class TestController {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/getUsers")
    public List<Map<String, Object>> getDbType(){
        String sql = "select * from t_user";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Entry<String, Object>> entries = map.entrySet( );
                if(entries != null) {
                    Iterator<Entry<String, Object>> iterator = entries.iterator( );
                    while(iterator.hasNext( )) {
                    Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
                    Object key = entry.getKey( );
                    Object value = entry.getValue();
                    System.out.println(key+":"+value);
                }
            }
        }
        return list;
    }
	
	@RequestMapping("/getUsers2")
    public String getUsers2(ModelMap model){
		List<Map<String, Object>> list = getDbType();
		model.put("object", JSONObject.toJSON(list));
		return "pages/userList";
    }
}

