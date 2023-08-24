async function writeArticle(){
    let articleTitle = document.getElementById("articleTitleInput").value;
    let articleContent = document.getElementById("articleContentInput").value;
    let body = {
        title: articleTitle,
        content: articleContent
    }
    let res = await fetch("/community/write",{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + localStorage.getItem("accessToken")
        },
        body: JSON.stringify(body)
    });
}

document.getElementById("articleWriteButton").addEventListener("click", writeArticle);