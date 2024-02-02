async function apiPost(urlPath, body, headers = {}) {
    const url = urlPath;
    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            ...headers,
        },
        body: JSON.stringify(body),
    };
    const res = await fetch(url, options);
    const data = await res.json(); // .json() : HTTP 응답(Response)의 내용을 JSON 형식으로 파싱하여 JavaScript 객체로 변환한다.
    if (res.ok) {
        return data;
    } else {
        throw Error(data);
    }
}

const showNode = function (id, flag) {
    if (flag) {
        document.getElementById(id).style.display = "block";
    } else {
        document.getElementById(id).style.display = "none";
    }
}