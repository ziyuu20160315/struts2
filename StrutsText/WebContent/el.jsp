<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Expression Language</title>
</head>
<body>
<%
Map<String, String> map2 = new HashMap<String, String>();
pageContext.setAttribute("map2", map2);
map2.put("demo", "this is a demo");

String name = "demo";
pageContext.setAttribute("name", name);
%>
<h3>map2.get("demo"): <%=map2.get("demo")%></h3>
<h3>map2.get("demo"): ${map2.demo}</h3>
<h3>map2.get("demo"): ${map2["demo"]}</h3>
<h3>map2.get(demo): ${map2[demo]}</h3>
<h3>map2.get(name): <%=map2.get(name)%></h3>
<h3>map2.get(name): ${map2[name]}</h3>

<br><br><br>
<%
String data6 = "this is data6";
pageContext.setAttribute("data6", data6);

String data7 = "this is data7";
pageContext.setAttribute("data 7", data7);
%>
<h3>data6: ${data6}</h3>
<h3>data6: ${pageScope.data6}</h3>
<h3>data 7: ${pageScope["data 7"]}</h3>

<br><br><br>
<%
String[] array1 = new String[] {"AAAA", "BBBB", "CCCC"};
pageContext.setAttribute("array1", array1);
%>
<h3>array1[0]: ${array1["0"]}</h3>
<h3>array1[1]: ${array1['1']}</h3>
<h3>array1[2]: ${array1[2]}</h3>

<br><br><br>
<%
List<String> list1 = new ArrayList<String>();
pageContext.setAttribute("list1", list1);
list1.add("OOOO");
list1.add("XXXX");
list1.add("####");
%>
<h3>list1.get(0): ${list1["0"]}</h3>
<h3>list1.get(1): ${list1['1']}</h3>
<h3>list1.get(2): ${list1[2]}</h3>

<br><br><br>
<%
Map<String, String> map1 = new HashMap<String, String>();
pageContext.setAttribute("map1", map1);
map1.put("key", "value");
map1.put("123", "456");
%>
<h3>map1.get("key"): <%=map1.get("key")%></h3>
<h3>map1.get("key"): ${map1.key}</h3>
<h3>map1.get("key"): ${map1["key"]}</h3>
<h3>map1.get("123"): ${map1["123"]}</h3>

<br><br><br>
<%
teacher.CustomerBean bean3 = new teacher.CustomerBean();
pageContext.setAttribute("bean3", bean3);
bean3.setCustid("this is bean3");
%>
<h3>bean1.getCustid(): ${bean3.custid}</h3>
<h3>bean1.getCustid(): ${bean3["custid"]}</h3>
<h3>bean1.getCustid(): ${bean3['custid']}</h3>
<h3>bean1.getCustid(): ${bean3[custid]}</h3>

<br><br><br>
<%
String data4 = new String("this is data4");
String data5 = new String("this is data4");
pageContext.setAttribute("data4", data4);
pageContext.setAttribute("data5", data5);
%>
<h3>data4==data5: <%=data4==data5%></h3>
<h3>data4==data5: ${data4==data5}</h3>
<h3>data4 eq data5: ${data4 eq data5}</h3>
<h3>data4.equals(data5): <%=data4.equals(data5)%></h3>

<br><br><br>
<%
String data3 = null;
pageContext.setAttribute("data3", data3);

teacher.CustomerBean bean2 = null;
pageContext.setAttribute("bean2", bean2);
%>
<h3>data3: <%=data3%></h3>
<h3>data3: ${data3}</h3>
<h3>bean2.getCustid(): ${bean2.custid}</h3>

<br><br><br>
<%
teacher.CustomerBean bean1 = new teacher.CustomerBean();
pageContext.setAttribute("bean1", bean1);
bean1.setCustid("this is bean1");
%>
<h3>bean1.getCustid(): <%=((teacher.CustomerBean) pageContext.findAttribute("bean1")).getCustid()%></h3>
<h3>bean1.getCustid(): ${bean1.custid}</h3>

<br><br><br>
<%
String data1 = "this is data1";
pageContext.setAttribute("data2", data1);
%>
<h3>data1: <%=data1%></h3>
<h3>data1: ${data1}</h3>
<h3>data2: ${data2}</h3>
<h3>data2: <%=pageContext.findAttribute("data2")%></h3>

</body>
</html>
