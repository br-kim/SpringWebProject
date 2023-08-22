async function writeArticle(){
    let articleTitle = document.getElementById("articleTitleInput").textContent;
    let articleContent = document.getElementById("articleContentInput").textContent;

    let res = await fetch("/community/write",{
        method: "POST",
        headers: {
            Authorization: "Bearer " + localStorage.getItem("accessToken")
        },
        body: {
            title: articleTitle,
            content: articleContent
        }
    });
}

document.getElementById("articleWriteButton").addEventListener()