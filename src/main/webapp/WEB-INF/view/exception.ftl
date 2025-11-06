<#include "base.ftl">

<#macro title>
     Детали ошибки
</#macro>

<#macro content>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card shadow-sm border-danger">
                    <div class="card-header bg-danger text-white">
                        <h2 class="h4 mb-0">Детали ошибки</h2>
                    </div>
                    <div class="card-body">
                        <p class="card-text"><strong>Request URI:</strong> <code>${uri!}</code></p>
                        <p class="card-text"><strong>Статусный код:</strong> <span class="badge bg-warning text-dark">${statusCode!}</span></p>
                        <#if message??>
                            <p class="card-text text-danger"><strong>Сообщение:</strong> ${message}</p>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>