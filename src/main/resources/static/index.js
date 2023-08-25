function getQueryParam(param) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get(param);
}

function storeTokensFromQueryString() {
    const accessToken = getQueryParam('accessToken');
    const refreshToken = getQueryParam('refreshToken');

    if (accessToken) {
        localStorage.setItem('accessToken', accessToken);
    }

    if (refreshToken) {
        localStorage.setItem('refreshToken', refreshToken);
    }
}

async function getLoginUserInfo(){
    let res = await fetch("/user/me");
    return await res.json().email;
}

window.onload = storeTokensFromQueryString;
