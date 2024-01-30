document.addEventListener("DOMContentLoaded", () => {

});

// 로그아웃
document.getElementById("logOutId").addEventListener("click", function() {
    document.headerForm.action = "/logOut";
    document.headerForm.submit();
})

