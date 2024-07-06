package com.jfy.controller;


import com.jfy.feign.DiscussFeignService;
import com.jfy.feign.ProductFeignService;
import com.jfy.pojo.Discuss;
import com.jfy.pojo.Product;
import com.jfy.pojo.User;
import com.jfy.utils.AjaxResult;
import com.jfy.utils.PageResult;
import com.jfy.utils.TransFerPlace;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jfy
 * @since 2024-05-19
 */
@Controller
@RequestMapping("/prod")
public class ProductController {
    @Autowired
    private ProductFeignService productFeignService;
    @Autowired
    private DiscussFeignService discussFeignService;

    @RequestMapping("/toList")
    public String toList(){
        System.out.println("-----------toList-----------");
        return "prod/list";
    }

    @RequestMapping("/getPage")
    @ResponseBody
    public PageResult<Product> getPage(String pname, Integer page, Integer limit){
        System.out.println("-----------getPage-----------");
        System.out.println("pname = " + pname+",page = "+page+",limit = "+limit);

        if("".equals(pname)){
            pname = "2Af7348K..;sd";
        }

        //模糊分页和分页查询
        PageResult<Product> pageResult = productFeignService.getPage(pname,page, limit);
        return pageResult;

    }

    @PostMapping("/batchDel")
    @ResponseBody
    public AjaxResult batchDel(@RequestParam("prodIds[]") Integer prodIds[]){
        System.out.println("-----------batchDel-----------");
//        for (Integer prodId : prodIds) {
//            System.out.println("prodId = " + prodId);
//        }
        List<Integer> list = List.of(prodIds);
        boolean b = productFeignService.rmbyids(list);

        return b?AjaxResult.right():AjaxResult.error();
    }

    @RequestMapping("/doDel/{pid}")
    @ResponseBody
    public AjaxResult doDel(@PathVariable("pid") Integer pid){
        System.out.println("-----------doDel-----------");

        boolean b = productFeignService.removeById(pid);
        return b?AjaxResult.right():AjaxResult.error();
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        System.out.println("-----------toAdd---------");
        return "prod/add";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file, HttpSession session){
        System.out.println("-----------upload----------");
        System.out.println("file:"+file);

        String name = file.getOriginalFilename();
        System.out.println("name:"+name);

        String path=session.getServletContext().getRealPath("/img");
        System.out.println("path:"+path);

        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }

        UUID uuid = UUID.randomUUID();
        String realName = uuid+name;

        File file2 = new File(file1,realName);
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TransFerPlace.transfer(file2,"img");

        Map<String,Object>map=new HashMap<>();
        map.put("code",0);
        map.put("msg","文件上传成功");
        map.put("data","/img/"+realName);

        return map;
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    public AjaxResult doAdd(Product product){
        System.out.println("-----------doAdd---------");
        System.out.println("product:"+product);

        boolean flag = productFeignService.save(product);
        return flag?AjaxResult.right():AjaxResult.error();


    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Integer pid, HttpServletRequest request){
        System.out.println("-----------toEdit---------");
        System.out.println("id:"+pid);

        Product product = productFeignService.getById(pid);
        request.setAttribute("product",product);
        return "prod/edit";
    }

    @RequestMapping("/doEdit")
    @ResponseBody
    public AjaxResult doEdit(Product product){
        System.out.println("-----------doEdit---------");
        System.out.println("product:"+product);

        boolean flag = productFeignService.updateById(product);
        return flag?AjaxResult.right():AjaxResult.error();

    }

    @RequestMapping("/index.html")
    public String toIndex(@RequestParam(defaultValue = "1") Integer currentPage,HttpServletRequest request){
        System.out.println("-----------toIndex---------");
        System.out.println("currentPage:"+currentPage);

        List<Product> list =productFeignService.getProductByPage(currentPage);
        long count = productFeignService.count();
        request.setAttribute("list",list);
        request.setAttribute("pages",currentPage);
        request.setAttribute("totalCount",count);



        return "index";
    }

    @RequestMapping("/getProductById/{id}")
    public String getProductById(@PathVariable("id") Integer pid,HttpServletRequest request){
        System.out.println("-------------getProductById-----------");
        System.out.println("pid:"+pid);
        Product product = productFeignService.getProductById(pid);
        //更新评分
        List<User> _discussList = discussFeignService.getDiscussByPid(pid);

        //计算平均评分,保留一位小数
        BigDecimal sum = new BigDecimal(0);
        int count = 0;
        for (User user : _discussList) {
            List<Discuss> discussList = user.getDiscussList();

            for (Discuss discuss : discussList) {
                sum = sum.add(new BigDecimal(discuss.getRating()));
                count++;
            }


        }
        if (count == 0){
            count = 1;
        }
        BigDecimal avg = sum.divide(new BigDecimal(count),1,BigDecimal.ROUND_HALF_UP);
        product.setRating(avg);
        productFeignService.updateById(product);


        request.setAttribute("product",product);


        return "prod/info";

    }



}