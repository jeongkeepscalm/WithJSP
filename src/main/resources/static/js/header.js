document.addEventListener("DOMContentLoaded", () => {

});

// 로그아웃
document.getElementById("logoutId").addEventListener("click", function() {
    document.headerForm.action = "/logout";
    document.headerForm.submit();
})



