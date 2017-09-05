 $(document).ready(function() {
        $("#checkAll").click(function() {
            $('input[name="checkbox"]').prop("checked", this.checked);
        });
        $("#delete").click(function() {
            var ckbs = $("input[name='checkbox']:checked");
            if (ckbs.size() == 0) {
                alert("要删除指定行，需选中要删除的行！");
                return;
            }
            ckbs.each(function() {
                $(this).parent().parent().remove();
            });
        });
    });

  function registerValid() {
        var username = $("#username").val();
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        var email = $("#email").val();
        if (username == "" || password == "" || confirmPassword == "" || email == "") {
            alert("字段不能为空！")
            return false;
        }
        if (username.length < 6 || username.length > 20) {
            // document.getElementById("username").setCustomValidity("用户名长度必须在6到20个字符之间");
            alert("用户名长度必须在6到20个字符之间");
            return false;

        } else if (password.length < 6 || password.length > 20) {
            // document.getElementById("password").setCustomValidity("密码长度必须在6到20个字符之间");
            alert("密码长度必须在6到20个字符之间")
            return false;
        } else if (confirmPassword.length < 6 || confirmPassword.length > 20) {
            // document.getElementById("confirmPassword").setCustomValidity("用户名长度必须在6到20个字符之间");
            alert("确认密码长度必须在6到20个字符之间");
            return false;
        } else if (password != confirmPassword) {
            // document.getElementById("confirmPassword").setCustomValidity("密码和确认密码必须相同！");
            alert("密码和确认密码必须相同！");
            return false;
        }
  

    }