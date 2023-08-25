async function writeArticle(){
    let articleTitle = document.getElementById("articleTitleInput").value;
    let articleContent = document.getElementById("articleContentInput").value;
    let body = {
        title: articleTitle,
        content: articleContent
    }
    let res = await fetch("/community/article/write",{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + localStorage.getItem("accessToken")
        },
        body: JSON.stringify(body)
    });
}

async function getArticle(){
    let page = 1;
    let res = await fetch(`/community/article?page=${page}`,{
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },

    })
    let articleList = await res.json();
    console.log(articleList);
    return articleList.posts;
}

async function loadArticleList() {
    let articleList = await getArticle();
    let articleContainer = document.getElementById("articleContainer");
    let rowDiv = document.createElement("div");
    rowDiv.classList.add("row");

    for (let idx in articleList){
        let article = articleList[idx];
        let titleColDiv = document.createElement("div");
        let dateColDiv = document.createElement("div");
        let authorColDiv = document.createElement("div");
        titleColDiv.classList.add("col");
        dateColDiv.classList.add("col");
        authorColDiv.classList.add("col");
        titleColDiv.innerText = article.title;
        dateColDiv.innerText = article.createdAt;
        authorColDiv.innerText = article.author;
        rowDiv.append(titleColDiv, authorColDiv, dateColDiv);
    }
    articleContainer.append(rowDiv);
}

document.onload = loadArticleList();

document.getElementById("articleWriteButton").addEventListener("click", writeArticle);