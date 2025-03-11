[toc]
<#assign r = addReqTabHeader("name", "参数名称")>
<#assign r = addReqTabHeader("type", "类型")>
<#assign r = addReqTabHeader("description", "描述")>
<#assign r = addReqTabHeader("required", "是否必填")>
# ${title}
<#list tagItems as tagItem>
## ${tagItem.index}. ${tagItem.description}
  <#list tagItem.apiItems as apiItem>
### ${apiItem.index}. ${apiItem.description}
1. ${apiItem.apiMethods?join("/")} ${(apiItem.apiUrls?size > 1)?then("[", "")}`${apiItem.apiUrls?join("`, `")}`${(apiItem.apiUrls?size > 1)?then("]", "")}
<#assign reqArgTabLineList = _tool.generateMdTable(reqArgTabHeaders, apiItem.schemaFields)>

2. 请求参数

   > ${reqArgTabLineList?join("\r\n   > ")}
  </#list>
</#list>