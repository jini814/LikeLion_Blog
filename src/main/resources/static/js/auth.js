//username 중복 확인
function checkUsername() {
    var username = document.getElementById("username").value;

    if (username === "") {
        document.getElementById("usernameError").textContent = "아이디를 입력해주세요.";
        return; // 공백일 경우 함수 종료
    }

    fetch("/api/users/check-username?username=" + username)
        .then(response => response.json())
        .then(data => {
            if (data) {
                document.getElementById("usernameError").textContent = "이미 사용 중인 아이디 입니다.";
            } else {
                document.getElementById("usernameError").textContent = "사용할 수 있는 아이디 입니다.";
                document.getElementById("username").readOnly = true;
            }
        })
        .catch(error => {
            document.getElementById("usernameError").textContent = "서버 오류 발생";
        });
}

// 이메일 형식 확인
function validateEmail(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}
//email 중복 확인
function checkEmail() {
    var email = document.getElementById("email").value;

    if (email === "") {
        document.getElementById("emailError").textContent = "이메일을 입력해주세요.";
        return; // 공백일 경우 함수 종료
    }
    if (!validateEmail(email)) {
        document.getElementById("emailError").textContent = "유효한 이메일 형식이 아닙니다.";
        return; // 유효하지 않은 이메일 형식일 경우 함수 종료
    }

    fetch("/api/users/check-email?email=" + email)
        .then(response => response.json())
        .then(data => {
            if (data) {
                document.getElementById("emailError").textContent = "이미 사용 중인 이메일 입니다.";
            } else {
                document.getElementById("emailError").textContent = "사용할 수 있는 이메일 입니다.";
                document.getElementById("email").readOnly = true;
            }
        })
        .catch(error => {
            document.getElementById("emailError").textContent = "서버 오류 발생";
        });
}
//비밀번호 일치 확인
document.addEventListener('DOMContentLoaded', function() {
    const passwordField = document.getElementById('password');
    const passwordCheckField = document.getElementById('passwordCheck');
    const passwordMatchError = document.getElementById('passwordMatchError');

    passwordCheckField.addEventListener('input', function() {
        const password = passwordField.value;
        const confirmPassword = passwordCheckField.value;

        if (password === confirmPassword) {
            passwordMatchError.textContent = '';
        } else {
            passwordMatchError.textContent = '비밀번호가 일치하지 않습니다.';
        }
    });
});

//로그아웃
function logOut() {
    fetch('/logout', {
        method: 'GET',
        credentials: 'same-origin'
    })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url; // 로그아웃 성공 시 리다이렉트
            } else {
                console.error('로그아웃 실패');
            }
        })
        .catch(error => console.error('로그아웃 중 오류 발생:', error));
}




