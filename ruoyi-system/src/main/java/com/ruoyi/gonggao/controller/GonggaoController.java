//package com.ruoyi.gonggao.controller;
//
//import java.util.List;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.ruoyi.common.annotation.Log;
//import com.ruoyi.common.core.controller.BaseController;
//import com.ruoyi.common.core.domain.AjaxResult;
//import com.ruoyi.common.enums.BusinessType;
//import com.ruoyi.gonggao.domain.Gonggao;
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.common.core.page.TableDataInfo;
//
///**
// * 公告Controller
// *
// * @author deng
// * @date 2024-09-01
// */
//@RestController
//@RequestMapping("/gonggao/gonggao")
//public class GonggaoController extends BaseController {
//    @Autowired
//    private IGonggaoService gonggaoService;
//
//    /**
//     * 查询公告列表
//     */
//    @PreAuthorize("@ss.hasPermi('gonggao:gonggao:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(Gonggao gonggao) {
//        startPage();
//        List<Gonggao> list = gonggaoService.selectGonggaoList(gonggao);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出公告列表
//     */
//    @PreAuthorize("@ss.hasPermi('gonggao:gonggao:export')")
//    @Log(title = "公告", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, Gonggao gonggao) {
//        List<Gonggao> list = gonggaoService.selectGonggaoList(gonggao);
//        ExcelUtil<Gonggao> util = new ExcelUtil<Gonggao>(Gonggao.class);
//        util.exportExcel(response, list, "公告数据");
//    }
//
//    /**
//     * 获取公告详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('gonggao:gonggao:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id) {
//        return success(gonggaoService.selectGonggaoById(id));
//    }
//
//    /**
//     * 新增公告
//     */
//    @PreAuthorize("@ss.hasPermi('gonggao:gonggao:add')")
//    @Log(title = "公告", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody Gonggao gonggao) {
//        return toAjax(gonggaoService.insertGonggao(gonggao));
//    }
//
//    /**
//     * 修改公告
//     */
//    @PreAuthorize("@ss.hasPermi('gonggao:gonggao:edit')")
//    @Log(title = "公告", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody Gonggao gonggao) {
//        return toAjax(gonggaoService.updateGonggao(gonggao));
//    }
//
//    /**
//     * 删除公告
//     */
//    @PreAuthorize("@ss.hasPermi('gonggao:gonggao:remove')")
//    @Log(title = "公告", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids) {
//        return toAjax(gonggaoService.deleteGonggaoByIds(ids));
//    }
//}
