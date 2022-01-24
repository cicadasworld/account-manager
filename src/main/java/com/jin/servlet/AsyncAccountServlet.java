package com.jin.servlet;

import com.alibaba.fastjson.JSON;
import com.jin.bean.entity.Account;
import com.jin.bean.response.ResponseBean;
import com.jin.bean.vo.AccountVO;
import com.jin.bean.vo.PageBean;
import com.jin.exception.BusinessException;
import com.jin.service.AccountService;
import com.jin.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 处理客户端的账户管理异步请求
 */
@WebServlet("/async/account")
public class AsyncAccountServlet extends BaseServlet {

    private AccountService accountService = new AccountServiceImpl();

    public void findAllAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<List<AccountVO>> responseBean = new ResponseBean<>(Boolean.TRUE);
        try {
            List<AccountVO> accountVOList = accountService.findAll();
            if (accountVOList != null) {
                responseBean.setData(accountVOList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("查询所有账户失败");
        }

        String responseJSONString = JSON.toJSONString(responseBean);
        response.getWriter().write(responseJSONString);
    }

    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<Boolean> responseBean = new ResponseBean<>(Boolean.TRUE);
        String errorMessage = null;
        Account account = null;

        // 客户端的请求参数是JSON
        if ("application/json".equals(request.getContentType())) {
            account = JSON.parseObject(request.getInputStream(), Account.class);
        } else {
            String name = request.getParameter("name");
            String balance = request.getParameter("balance");
            String status = request.getParameter("status");
            account = new Account();
            account.setName(name);
            account.setBalance(new BigDecimal(balance));
            account.setStatus(Integer.valueOf(status));
        }

        try {
            errorMessage = validate(account.getName(), account.getBalance().toString(), account.getStatus().toString());
            // 校验通过
            if (errorMessage == null) {
                boolean addResult = accountService.add(account);
                // 添加成功
                if (addResult) {
                    responseBean.setData(Boolean.TRUE);
                }
            } else {
                responseBean.setFlag(Boolean.FALSE);
                responseBean.setErrorMessage(errorMessage);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            // 账号已存在，返回添加账号页面并给出响应的提示信息
            errorMessage = e.getMessage();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage(errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("服务器异常");
        }

        String responseJSONString = JSON.toJSONString(responseBean);
        response.getWriter().write(responseJSONString);
    }

    private String validate(String name, String balance, String status) {
        String errorMessage = null;
        if (name == null || name.isEmpty()) {
            errorMessage = "账号名为空";
        } else if (balance == null || balance.isEmpty()) {
            errorMessage = "账号余额为空";
        } else if (new BigDecimal(balance).compareTo(BigDecimal.ZERO) == -1) {
            errorMessage = "账号余额为负数";
        } else if (status == null || status.isEmpty()) {
            errorMessage = "账号状态为空";
        }
        return errorMessage;
    }

    public void deleteAccountById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseBean<Boolean> responseBean = new ResponseBean<>(Boolean.TRUE);
        String id = request.getParameter("id");
        String errorMessage = null;
        if (id == null || id.isEmpty()) {
            errorMessage = "id不能为空";
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage(errorMessage);
        } else {
            try {
                boolean deleteResult = accountService.deleteById(Long.valueOf(id));
                if (deleteResult) {
                    responseBean.setData(Boolean.TRUE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage = "删除失败";
                responseBean.setFlag(Boolean.FALSE);
                responseBean.setErrorMessage(errorMessage);
            }
        }

        String responseJSONString = JSON.toJSONString(responseBean);
        response.getWriter().write(responseJSONString);
    }

    public void toUpdateAccountPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<Account> responseBean = new ResponseBean<>(Boolean.TRUE);
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            Account account = accountService.findById(id);
            if (account != null) {
                responseBean.setData(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("回显账户信息失败");
        }

        String responseJSONString = JSON.toJSONString(responseBean);
        response.getWriter().write(responseJSONString);
    }

    public void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<Boolean> responseBean = new ResponseBean<>(Boolean.TRUE);
        Account account = null;
        String errorMessage = null;
        if ("application/json".equals(request.getContentType())) {
            account = JSON.parseObject(request.getInputStream(), Account.class);
        } else {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String balance = request.getParameter("balance");
            String status = request.getParameter("status");
            account = new Account();
            account.setId(Long.valueOf(id));
            account.setName(name);
            account.setBalance(new BigDecimal(balance));
            account.setStatus(Integer.valueOf(status));
        }
        try {
            errorMessage = validate(account.getName(), account.getBalance().toString(), account.getStatus().toString());
            // 校验通过
            if (errorMessage == null) {
                boolean updateResult = accountService.update(account);
                // 修改成功
                if (updateResult) {
                    responseBean.setData(Boolean.TRUE);
                }
            } else {
                // 校验失败
                responseBean.setFlag(Boolean.FALSE);
                responseBean.setErrorMessage(errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("修改账户失败");
        }

        String responseJSONString = JSON.toJSONString(responseBean);
        response.getWriter().write(responseJSONString);
    }

    public void findAccountByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBean<PageBean<AccountVO>> responseBean = new ResponseBean<>(Boolean.TRUE);
        try {
            Long pageNo = Long.valueOf(request.getParameter("pageNo"));
            Long pageSize = Long.valueOf(request.getParameter("pageSize"));
            PageBean<AccountVO> pageBean = accountService.findByPage(pageNo, pageSize);
            if (pageBean != null && pageBean.getDataList() != null && !pageBean.getDataList().isEmpty()) {
                responseBean.setData(pageBean);
            } else {
                responseBean.setFlag(Boolean.FALSE);
                responseBean.setErrorMessage("没有查询到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setFlag(Boolean.FALSE);
            responseBean.setErrorMessage("分页查询账户异常");
        }

        String responseJSONString = JSON.toJSONString(responseBean);
        response.getWriter().write(responseJSONString);
    }
}
