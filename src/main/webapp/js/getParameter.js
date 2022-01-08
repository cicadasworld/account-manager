// 根据传递的参数name获取对应的值
function getParameter(name) {
  let regex = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  let value = location.search.substr(1).match(regex);
  if (value != null) return (value[2]);  return null;
}